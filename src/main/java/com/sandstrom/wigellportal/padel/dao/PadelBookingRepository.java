package com.sandstrom.wigellportal.padel.dao;

import com.sandstrom.wigellportal.padel.entities.Court;
import com.sandstrom.wigellportal.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.padel.entities.PadelCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface PadelBookingRepository extends JpaRepository<PadelBooking, Integer> {

    List<PadelBooking> findByCustomer(PadelCustomer customer);

    Optional<PadelBooking> findByCustomerAndId(PadelCustomer customer, int id);

    Optional<PadelBooking> findByCourtAndTime(Court court, LocalTime time);
}
