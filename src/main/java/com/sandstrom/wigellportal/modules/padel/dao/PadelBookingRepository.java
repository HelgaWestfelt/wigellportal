package com.sandstrom.wigellportal.modules.padel.dao;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface PadelBookingRepository extends JpaRepository<PadelBooking, Integer> {

    List<PadelBooking> findByCustomer(Customer customer);

    Optional<PadelBooking> findByCustomerAndId(Customer customer, int id);

    Optional<PadelBooking> findByCourtAndTime(Court court, LocalTime time);
}
