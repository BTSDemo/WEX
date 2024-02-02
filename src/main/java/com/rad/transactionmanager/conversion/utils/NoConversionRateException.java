package com.rad.transactionmanager.conversion.utils;

public class NoConversionRateException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoConversionRateException(String message) {
        super(message);
    }

    public NoConversionRateException(String message, Throwable cause) {
        super(message, cause);
    }
}
