package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.McBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface McBookingRepository extends JpaRepository<McBooking, Integer> {

    // Hämta aktiva bokningar (slutdatum i framtiden)
    List<McBooking> findByEndDateAfter(LocalDate currentDate);

    // Hämta tidigare bokningar (slutdatum i det förflutna)
    List<McBooking> findByEndDateBefore(LocalDate currentDate);

}
