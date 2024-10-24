package com.sandstrom.wigellportal.padel.controllers;

import com.sandstrom.wigellportal.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.padel.services.PadelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/padel/admin")
public class PadelAdminController {

    private final PadelBookingService bookingService;

    @Autowired
    public PadelAdminController(PadelBookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Administratörer kan lista alla bokningar
    @GetMapping("/bookings")
    public List<PadelBooking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Administratörer kan ta bort en bokning
    @DeleteMapping("/bookings/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
    }
}
