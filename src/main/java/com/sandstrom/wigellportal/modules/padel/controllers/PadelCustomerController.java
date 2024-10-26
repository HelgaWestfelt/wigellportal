package com.sandstrom.wigellportal.modules.padel.controllers;

import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.modules.padel.services.PadelBookingService;
import com.sandstrom.wigellportal.modules.padel.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/padel/bookings")
public class PadelCustomerController {

    private final PadelBookingService bookingService;
    private final CourtService courtService;

    @Autowired
    public PadelCustomerController(PadelBookingService bookingService, CourtService courtService) {
        this.bookingService = bookingService;
        this.courtService = courtService;
    }

    // Skapa en bokning
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createBooking(@RequestBody PadelBooking booking) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try {
            PadelBooking newBooking = bookingService.createBookingForCustomer(booking, username);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Se en specifik bokning (endast för användare)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBookingById(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try {
            return ResponseEntity.ok(bookingService.getBookingByCustomerAndId(username, id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Du har inte rättigheter att komma åt denna bokning.");
        }
    }

    // Uppdatera bokning (endast för användare)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateBooking(@PathVariable int id, @RequestBody PadelBooking bookingDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try {
            PadelBooking updatedBooking = bookingService.updateCustomerBooking(id, bookingDetails, username);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Visa alla bokningar för admin, och lediga tider för user
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBookingsOrAvailableTimes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            // Admin ser alla bokningar
            List<PadelBooking> allBookings = bookingService.getAllBookings();
            List<String> bookingDetails = allBookings.stream()
                    .map(booking -> String.format("Court: %s, Date: %s, Time: %s, Booked by: %s",
                            booking.getCourt().getName(),
                            booking.getDate(),
                            booking.getTime(),
                            booking.getCustomer().getUsername()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(bookingDetails);
        } else {
            // User ser alla lediga tider (utan ytterligare filtrering)
            List<String> availableTimes = bookingService.getAvailableTimes();
            return ResponseEntity.ok(availableTimes);
        }
    }
}
