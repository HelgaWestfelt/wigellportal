package com.sandstrom.wigellportal.modules.motorcyclerental.dao;

import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface McBookingRepository extends JpaRepository<McBooking, Integer> {

    //Hämta aktiva bokningar för en kund
    List<McBooking> findByCustomerIdAndEndDateAfter(int customerId, LocalDate date);

    //Hämta inaktiva bokningar för en kund
    List<McBooking> findByCustomerIdAndEndDateBefore(int customerId, LocalDate date);

    //Hämtar alla bokningar vars slutdatum är före det angivna datumet
    List<McBooking> findByEndDateBefore(LocalDate date);

    //Kollar om det finns en bokning för en specifik motorcykel som fortfarande är aktiv (endDate efter angivet datum)
    boolean existsByMotorcyclesIdAndEndDateAfter(int motorcycleId, LocalDate date);

    List<McBooking> findByCustomerId(int id);

}
