package com.westfelt.wigellmcrental.services;


import com.westfelt.wigellmcrental.entities.CinemaVenue;

import java.util.List;

public interface CinemaVenueService {

    List<CinemaVenue> findAll();

    CinemaVenue findById(int id);

    CinemaVenue save(CinemaVenue venue);

    void deleteById(int id);
}
