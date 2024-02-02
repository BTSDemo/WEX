package com.rad.transactionmanager.conversion.service;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.rad.transactionmanager.conversion.utils.ConversionDetail;

public interface ConversionService {

	ConversionDetail convertToCurrency(LocalDate transactionDate, BigDecimal purchaseAmount, String targetCurrency);
}
