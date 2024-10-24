package com.sandstrom.wigellportal.modules.cinema.dao;

import com.sandstrom.wigellportal.modules.cinema.entities.CinemaVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaVenueRepository extends JpaRepository<CinemaVenue, Integer> {
}