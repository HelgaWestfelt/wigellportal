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
public class CurrencyConversionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${forex.api.key}")
    private String apiKey;

    @Value("${forex.api.url}")
    private String apiUrl;

    // Allmän metod för att konvertera valutor
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            // Bygg URL med API Access Key och korrekt formaterad mängd
            String formattedAmount = String.format(Locale.ENGLISH, "%.2f", amount);
            String url = String.format("%s/convert?from=%s&to=%s&amount=%s&access_key=%s",
                    apiUrl, fromCurrency, toCurrency, formattedAmount, apiKey);

            System.out.println("API URL: " + url); // Logga URL:en som används

            // Gör GET-anropet med RestTemplate
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Logga hela responsen för felsökning
            System.out.println("API Response: " + response.getBody());

            // Använd ObjectMapper för att läsa API-responsen
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Kontrollera att API-svaret är framgångsrikt
            boolean success = jsonNode.path("success").asBoolean();
            if (!success) {
                throw new RuntimeException("API request failed, check your parameters. Error: " + jsonNode.path("error").path("info").asText());
            }

            // Extrahera konverteringsresultatet från API-svaret
            double result = jsonNode.path("result").asDouble();
            System.out.println("Conversion Result: " + result); // Logga konverteringsresultatet
            return result;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error while fetching conversion rate from Forex API", e);
        } catch (Exception e) {
            throw new RuntimeException("General error occurred while fetching conversion rate", e);
        }
    }

    // Metod för att konvertera SEK till EUR
    public double convertToEuro(double sekAmount) {
        return convertCurrency("SEK", "EUR", sekAmount);
    }

    // Metod för att konvertera EUR till SEK
    public double convertToSek(double eurAmount) {
        return convertCurrency("EUR", "SEK", eurAmount);
    }
}
