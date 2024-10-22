package com.sandstrom.wigellportal.cinema.services;


import com.sandstrom.wigellportal.cinema.models.CinemaExchangeRateResponse;
import org.springframework.stereotype.Service;

@Service

public interface CinemaExchangeRateService {
    CinemaExchangeRateResponse getExchangeRateToUSD();
}

