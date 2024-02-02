package com.rad.transactionmanager.conversion.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("data")
    private ExchangeRate[] data;

    public ExchangeRate[] getData() {
        return data;
    }

    public void setData(ExchangeRate[] data) {
        this.data = data;
    }
}