package com.rad.transactionmanager.conversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rad.transactionmanager.conversion.utils.ApiResponse;
import com.rad.transactionmanager.conversion.utils.ConversionDetail;
import com.rad.transactionmanager.conversion.utils.ConversionException;
import com.rad.transactionmanager.conversion.utils.ExchangeRate;
import com.rad.transactionmanager.conversion.utils.NoConversionRateException;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Override
    public ConversionDetail convertToCurrency(LocalDate transactionDate, BigDecimal purchaseAmount, String targetCurrency) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sixMonthsAgo = formatter.format(transactionDate.minusMonths(6));

        String EXCHANGE_RATE_API_URL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange"
                + "?fields=country_currency_desc,exchange_rate,record_date"
                + "&filter=country_currency_desc:in:" + targetCurrency + ",record_date:gte:" + sixMonthsAgo;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(objectMapper);

        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(messageConverter));

        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(
                EXCHANGE_RATE_API_URL, ApiResponse.class);

        BigDecimal conversionRate = BigDecimal.valueOf(0);
        BigDecimal convertedAmount = BigDecimal.valueOf(0);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ApiResponse apiResponse = responseEntity.getBody();
            
            if (apiResponse != null && apiResponse.getData() != null) {
                ExchangeRate[] exchangeRates = apiResponse.getData();

                // Filter rates within the last 6 months
                List<ExchangeRate> filteredData = Arrays.stream(exchangeRates)
                        .filter(rate -> !rate.getRecordDate().isBefore(transactionDate.minusMonths(6)))
                        .collect(Collectors.toList());

                if (filteredData.isEmpty()) {
                    // No conversion rate available within the last 6 months
                    throw new NoConversionRateException("No conversion rate available within the last 6 months.");
                }

                // Find the maximum record date within the last 6 months
                LocalDate maxRecordDate = filteredData.stream()
                        .map(ExchangeRate::getRecordDate)
                        .max(LocalDate::compareTo)
                        .orElseThrow(() -> new ConversionException("Error calculating max record date."));

                // Find the conversion rate corresponding to the maximum record date
                ExchangeRate selectedRate = filteredData.stream()
                        .filter(rate -> rate.getRecordDate().isEqual(maxRecordDate))
                        .findFirst()
                        .orElseThrow(() -> new ConversionException("Error finding selected rate."));

                conversionRate = selectedRate.getExchangeRate();
            }
        }

        convertedAmount = purchaseAmount.multiply(conversionRate).setScale(2, RoundingMode.HALF_UP);
        ConversionDetail conversionDetails = new ConversionDetail(conversionRate, convertedAmount);
        return conversionDetails;
    }

}