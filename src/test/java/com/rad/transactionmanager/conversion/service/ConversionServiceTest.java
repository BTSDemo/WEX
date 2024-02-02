package com.rad.transactionmanager.conversion.service;

import com.rad.transactionmanager.conversion.service.ConversionService;
import com.rad.transactionmanager.conversion.utils.ConversionDetail;
import com.rad.transactionmanager.conversion.utils.NoConversionRateException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConversionServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private final ConversionService conversionService = new ConversionServiceImpl(); // Replace with your actual implementation

    @Test
    public void testNoConversionRateException() {
        // Arrange
        LocalDate transactionDate = LocalDate.now(); // Set a date within the last 6 months
        BigDecimal purchaseAmount = BigDecimal.valueOf(100.0);
        String targetCurrency = "NonExistentCurrency"; // Assuming this currency won't have a conversion rate

        // Act
        exceptionRule.expect(NoConversionRateException.class);

        // Assert
        ConversionDetail conversionDetail = conversionService.convertToCurrency(transactionDate, purchaseAmount, targetCurrency);
    }
}
