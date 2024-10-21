package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.CinemaVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaVenueRepository extends JpaRepository<CinemaVenue, Integer> {
}