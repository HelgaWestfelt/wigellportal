package com.sandstrom.wigellportal.modules.travel.repositories;

import com.sandstrom.wigellportal.modules.travel.entities.TravelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TravelBookingRepository extends JpaRepository<TravelBooking, Integer> {
    List<TravelBooking> findTravelBookingByCustomerId(int id);
}
