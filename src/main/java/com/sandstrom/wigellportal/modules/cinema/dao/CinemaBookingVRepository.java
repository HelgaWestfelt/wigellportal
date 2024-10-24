package com.sandstrom.wigellportal.modules.cinema.dao;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaBookingVenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaBookingVRepository extends JpaRepository<CinemaBookingVenue, Integer> {

    List<CinemaBookingVenue> findByCustomerId(int customerId);
}


