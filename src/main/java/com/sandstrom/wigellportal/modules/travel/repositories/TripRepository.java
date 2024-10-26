package com.sandstrom.wigellportal.modules.travel.repositories;

import com.sandstrom.wigellportal.modules.travel.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    @Query("SELECT t FROM Trip t WHERE t.destination.city = :city AND t.destination.country = :country AND t.hotel = :hotel")
    Optional<Trip> findByDestinationAndHotel(@Param("city") String city, @Param("country") String country, @Param("hotel") String hotel);

    @Query("SELECT t FROM Trip t WHERE t.destination.city = :city AND t.destination.country = :country AND t.hotel = :hotel AND t.weeklyPrice = :weeklyPrice")
    Optional<Trip> findByDestinationHotelAndPrice(@Param("city") String city, @Param("country") String country, @Param("hotel") String hotel, @Param("weeklyPrice") Double weeklyPrice);

    List<Trip> findByActive(boolean active);
}