package com.westfelt.wigellmcrental.dao;


import com.westfelt.wigellmcrental.entities.CinemaBookingVenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaBookingVRepository extends JpaRepository<CinemaBookingVenue, Integer> {

    List<CinemaBookingVenue> findByCustomerId(int customerId);
}


