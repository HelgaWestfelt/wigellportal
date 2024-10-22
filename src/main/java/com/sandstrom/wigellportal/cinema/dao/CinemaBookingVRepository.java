package com.sandstrom.wigellportal.cinema.dao;


import com.sandstrom.wigellportal.cinema.entities.CinemaBookingVenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaBookingVRepository extends JpaRepository<CinemaBookingVenue, Integer> {

    List<CinemaBookingVenue> findByCinemaCustomerId(int customerId);
}


