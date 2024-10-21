package com.westfelt.wigellmcrental.services;


import com.westfelt.wigellmcrental.entities.CinemaBookingVenue;

import java.util.List;

public interface CinemaBookingVService {

    public List<CinemaBookingVenue> findAllBookingsByCustomerId(int customerId);

    CinemaBookingVenue findById(int id);

    CinemaBookingVenue save(CinemaBookingVenue bookingCVenue);

    void deleteById(int id);



}
