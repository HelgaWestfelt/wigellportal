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

    // Kunder och admin kan boka en tid
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

    // Kunder kan se sina bokningar
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

    // Kunder kan uppdatera sina egna bokningar
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

// Kunder och admin kan se alla tider. Admin ser både lediga och bokade tider, User ser bara tiden och banan.
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getAvailableTimes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Kontrollera om användaren är admin
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        List<String> availableTimes;

        if (isAdmin) {
            // Admin ser både banan, tiden och om den är ledig eller inte
            availableTimes = courtService.getAllCourts().stream()
                    .flatMap(court -> court.getAvailableTimes().entrySet().stream()
                            .map(entry -> String.format("Court: %s, Time: %s, Available: %s",
                                    court.getName(),
                                    entry.getKey(),
                                    entry.getValue() ? "true" : "false")))
                    .collect(Collectors.toList());
        } else {
            // User ser endast banan och tiden, utan information om tillgänglighet
            availableTimes = courtService.getAllCourts().stream()
                    .flatMap(court -> court.getAvailableTimes().entrySet().stream()
                            .map(entry -> String.format("Court: %s, Time: %s",
                                    court.getName(),
                                    entry.getKey())))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(availableTimes);
    }


}
