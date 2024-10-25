package com.sandstrom.wigellportal.modules.motorcyclerental.services;

import com.sandstrom.wigellportal.modules.motorcyclerental.services.CurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Value("23652b69428cbf1974fa96e77e32ef7b")
    private String apiKey;

    private static final String API_URL_TEMPLATE = "http://api.exchangeratesapi.io/v1/latest?access_key=%s";


    @Override
    public CurrencyResponse getCurrencyToGBP() {
        String apiUrl = String.format(API_URL_TEMPLATE, apiKey, "SEK");

        RestTemplate restTemplate = new RestTemplate();
        CurrencyResponse currencyResponse = restTemplate.getForObject(apiUrl, CurrencyResponse.class);

        return currencyResponse;
    }
}
