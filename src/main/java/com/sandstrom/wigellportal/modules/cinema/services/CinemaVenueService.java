package com.sandstrom.wigellportal.modules.cinema.services;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaVenue;

import java.util.List;

public interface CinemaVenueService {

    List<CinemaVenue> findAll();

    CinemaVenue findById(int id);

    CinemaVenue save(CinemaVenue venue);

    void deleteById(int id);
}
