package com.rad.transactionmanager.conversion.service;

import java.time.LocalDate;

import com.rad.transactionmanager.conversion.utils.ExchangeRate;

public interface ExternalExchangeRateService {
    ExchangeRate getExchangeRate(String targetCurrency, LocalDate date);
}

