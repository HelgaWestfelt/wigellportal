package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;

import java.time.LocalTime;
import java.util.List;

public interface PadelBookingService {

    List<PadelBooking> getAllBookings();

    List<PadelBooking> getBookingsByUsername(String username);

    PadelBooking getBookingById(int id);

    PadelBooking getBookingByCustomerAndId(String username, int id);

    PadelBooking createBookingForCustomer(PadelBooking booking, String username);

    PadelBooking updateCustomerBooking(int id, PadelBooking booking, String username);

    void deleteBooking(int id);

    boolean isTimeBooked(Court court, LocalTime time);
    List<String> getAvailableTimes();
}
