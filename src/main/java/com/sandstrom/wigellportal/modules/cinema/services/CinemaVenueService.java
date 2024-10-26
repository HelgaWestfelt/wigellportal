package com.sandstrom.wigellportal.modules.cinema.services;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaVenue;

import java.util.List;

public interface CinemaVenueService {

    List<CinemaVenue> findAll();

    CinemaVenue findById(int id);

    CinemaVenue save(CinemaVenue venue);

    public CinemaVenue updateVenue(int id, CinemaVenue updatedVenue);
    void deleteById(int id);
}
