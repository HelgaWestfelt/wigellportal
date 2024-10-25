package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.McBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface McBookingRepository extends JpaRepository<McBooking, Integer> {

    // Hämta aktiva bokningar för en kund
    List<McBooking> findByCustomerIdAndEndDateAfter(int customerId, LocalDate date);

    // Hämta tidigare bokningar för en kund
    List<McBooking> findByCustomerIdAndEndDateBefore(int customerId, LocalDate date);

}
