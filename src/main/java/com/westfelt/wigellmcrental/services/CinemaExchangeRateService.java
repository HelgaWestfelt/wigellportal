package com.westfelt.wigellmcrental.services;


import com.westfelt.wigellmcrental.models.CinemaExchangeRateResponse;
import org.springframework.stereotype.Service;

@Service

public interface CinemaExchangeRateService {
    CinemaExchangeRateResponse getExchangeRateToUSD();
}

