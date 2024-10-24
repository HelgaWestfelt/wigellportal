package com.sandstrom.wigellportal.modules.motorcyclerental.services;

import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;

import java.util.List;
import java.util.Map;

public interface McBookingService {

    List<McBooking> findAll();
    McBooking findById(int id);
    McBooking save(McBooking booking);
    String deleteById(int id);
    McBooking updateMcBooking(int id, McBooking mcBooking);
    Map<String, List<McBooking>> findAllBookings();
    List<McBooking> findBookingByCustomerId(int id);
}
