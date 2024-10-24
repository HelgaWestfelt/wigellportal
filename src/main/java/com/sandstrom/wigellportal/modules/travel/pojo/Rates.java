package com.sandstrom.wigellportal.modules.travel.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class Rates {
    private final Map<String, Double> rateValues = new HashMap<>();
    @JsonAnySetter
    public void addRate(String currency, Double value) {
        rateValues.put(currency, value);
    }
    public Map<String, Double> getRateValues() {
        return rateValues;
    }
    public Double getRate(String currencyCode) {
        return rateValues.get(currencyCode);
    }

}

