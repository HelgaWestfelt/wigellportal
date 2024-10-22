package com.sandstrom.wigellportal.cinema.services;


import com.sandstrom.wigellportal.cinema.entities.CinemaBookingVenue;

import java.util.List;

public interface CinemaBookingVService {

    public List<CinemaBookingVenue> findAllBookingsByCustomerId(int customerId);

    CinemaBookingVenue findById(int id);

    CinemaBookingVenue save(CinemaBookingVenue bookingCVenue);

    void deleteById(int id);



}
