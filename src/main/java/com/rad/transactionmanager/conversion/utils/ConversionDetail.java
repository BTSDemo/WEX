package com.rad.transactionmanager.conversion.utils;

import java.math.BigDecimal;

public class ConversionDetail {

	private BigDecimal conversionRate;
    
    private BigDecimal convertedAmount;
    
    
	
    public ConversionDetail(BigDecimal conversionRate, BigDecimal convertedAmount) {
		super();
		this.conversionRate = conversionRate;
		this.convertedAmount = convertedAmount;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(BigDecimal convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

    
}
