package com.sandstrom.wigellportal.modules.travel.services.currencyconversion;

import com.sandstrom.wigellportal.modules.travel.pojo.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {
    private final RestTemplate restTemplate;

    @Value("${currency.api.url}")
    private String apiUrl;

    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public double convertSEKtoPLN(double amountInSEK) {
        // Hämta växelkurserna
        ExchangeRateResponse rates = getExchangeRates();

        // Hämta växelkurserna
        double sekToEur = 1 / rates.getRates().getRate("SEK"); // SEK till EUR
        double eurToPln = rates.getRates().getRate("PLN");     // EUR till PLN

        // Konvertera SEK till EUR
        double amountInEUR = amountInSEK * sekToEur; // SEK till EUR

        // Konvertera EUR till PLN
        return amountInEUR * eurToPln; // EUR till PLN
    }

    public ExchangeRateResponse getExchangeRates() {
        String url = "https://api.exchangeratesapi.io/v1/latest?access_key=75c9eeefa8f725aeff157328e227ea48&base=EUR&symbols=SEK,PLN";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ExchangeRateResponse.class);
    }
}
