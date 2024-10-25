package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;

import java.time.LocalTime;
import java.util.List;

public interface PadelBookingService {

    List<PadelBooking> getAllBookings(); // Admin kan se alla bokningar

    List<PadelBooking> getBookingsByUsername(String username); // Kunder kan se sina egna bokningar

    PadelBooking getBookingById(int id);

    PadelBooking getBookingByCustomerAndId(String username, int id); // Kunder kan hämta sina bokningar via ID

    PadelBooking createBookingForCustomer(PadelBooking booking, String username); // Kunder kan boka tid

    PadelBooking updateCustomerBooking(int id, PadelBooking booking, String username); // Kunder kan uppdatera sin egen bokning

    void deleteBooking(int id); // Admin kan ta bort bokningar

    boolean isTimeBooked(Court court, LocalTime time); // Kontrollera om en tid är bokad på en specifik bana
    List<String> getAvailableTimes();
}
