package com.sandstrom.wigellportal.modules.padel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Locale;

@Service
public class PadelCurrencyConversionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${forex.api.key}")
    private String apiKey;

    @Value("${forex.api.url}")
    private String apiUrl;

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Conversion amount must be greater than zero.");
        }

        try {
            String formattedAmount = String.format(Locale.ENGLISH, "%.2f", amount);
            String url = String.format("%s/convert?from=%s&to=%s&amount=%s&access_key=%s",
                    apiUrl, fromCurrency, toCurrency, formattedAmount, apiKey);

            System.out.println("API URL: " + url);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            System.out.println("API Response: " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            boolean success = jsonNode.path("success").asBoolean();
            if (!success) {
                throw new RuntimeException("API request failed, check your parameters. Error: " + jsonNode.path("error").path("info").asText());
            }

            double result = jsonNode.path("result").asDouble();
            System.out.println("Conversion Result: " + result);
            return result;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error while fetching conversion rate from Forex API", e);
        } catch (Exception e) {
            throw new RuntimeException("General error occurred while fetching conversion rate", e);
        }
    }

    public double convertToEuro(double sekAmount) {
        return convertCurrency("SEK", "EUR", sekAmount);
    }

    public double convertToSek(double eurAmount) {
        return convertCurrency("EUR", "SEK", eurAmount);
    }
}
