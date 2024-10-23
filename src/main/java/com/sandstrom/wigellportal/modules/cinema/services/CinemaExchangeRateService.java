package com.sandstrom.wigellportal.modules.cinema.services;


import com.sandstrom.wigellportal.modules.cinema.models.CinemaExchangeRateResponse;
import org.springframework.stereotype.Service;

@Service

public interface CinemaExchangeRateService {
    CinemaExchangeRateResponse getExchangeRateToUSD();
}

