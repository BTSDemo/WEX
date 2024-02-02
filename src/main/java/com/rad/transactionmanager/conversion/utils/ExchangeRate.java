package com.rad.transactionmanager.conversion.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRate {

    @JsonProperty("country_currency_desc")
    private String countryCurrencyDesc;

    @JsonProperty("exchange_rate")
    private BigDecimal exchangeRate;

    @JsonProperty("record_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    // Constructors
    public ExchangeRate() {
        // Default constructor
    }

    public ExchangeRate(String countryCurrencyDesc, BigDecimal exchangeRate, LocalDate recordDate) {
        this.countryCurrencyDesc = countryCurrencyDesc;
        this.exchangeRate = exchangeRate;
        this.recordDate = recordDate;
    }

    // Getters and Setters
    public String getCountryCurrencyDesc() {
        return countryCurrencyDesc;
    }

    public void setCountryCurrencyDesc(String countryCurrencyDesc) {
        this.countryCurrencyDesc = countryCurrencyDesc;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
    
    @Override
    public String toString() {
        return "ExchangeRate{" +
                "countryCurrencyDesc='" + countryCurrencyDesc + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", recordDate='" + recordDate + '\'' +
                '}';
    }
}

