package com.sandstrom.wigellportal.modules.cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class CinemaExchangeRateResponse {
    private boolean success;
    private String base;
    private String date;

    @JsonProperty("rates")
    private Map<String, BigDecimal> rates;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, BigDecimal> getRates() { // Ändra namn för att matcha fältet
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public BigDecimal getRate(String currency) {
        return rates != null ? rates.get(currency) : null;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "success=" + success +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
