package com.sandstrom.wigellportal.padel.controllers;

import com.sandstrom.wigellportal.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.padel.entities.Court;
import com.sandstrom.wigellportal.padel.services.PadelBookingService;
import com.sandstrom.wigellportal.padel.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/padel/bookings")
public class PadelBookingController {

    private final PadelBookingService bookingService;
    private final CourtService courtService;

    @Autowired
    public PadelBookingController(PadelBookingService bookingService, CourtService courtService) {
        this.bookingService = bookingService;
        this.courtService = courtService;
    }

    // GET /api/v1/padel/bookings - Lista alla tider för admin och lediga tider för kunder
    @GetMapping
    public ResponseEntity<?> getAllBookingsOrAvailableTimes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            // Om användaren är administratör, returnera alla tider (både lediga och bokade)
            List<Court> allCourts = courtService.getAllCourts();
            List<String> allTimes = allCourts.stream()
                    .flatMap(court -> court.getAvailableTimes().entrySet().stream()
                            .map(entry -> "Court: " + court.getName() + ", Time: " + entry.getKey().toString() + ", Available: " + entry.getValue()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(allTimes);
        } else {
            // Om användaren är en kund, returnera endast lediga tider
            List<Court> allCourts = courtService.getAllCourts();
            List<String> availableTimes = allCourts.stream()
                    .flatMap(court -> court.getAvailableTimes().entrySet().stream()
                            .filter(Map.Entry::getValue) // Endast lediga tider
                            .map(entry -> "Court: " + court.getName() + ", Time: " + entry.getKey().toString()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(availableTimes);
        }
    }

    // GET /api/v1/padel/bookings/{id} - Se bokning baserat på roll (admin eller kund)
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            if (auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
                return ResponseEntity.ok(bookingService.getBookingById(id));  // Admin kan se alla bokningar
            } else {
                return ResponseEntity.ok(bookingService.getBookingByCustomerAndId(username, id));  // Kund kan bara se sin egen bokning
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Du har inte rättigheter att komma åt denna bokning.");
        }
    }

    // POST /api/v1/padel/bookings - Kunder kan boka tider
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody PadelBooking booking) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try {
            PadelBooking newBooking = bookingService.createBookingForCustomer(booking, username);
            // Uppdatera tillgängliga tider på banan
            Court court = booking.getCourt(); // Använd bokningens court
            court.getAvailableTimes().put(booking.getTime(), false);  // Markera tiden som bokad
            courtService.updateCourt(court.getId(), court);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/v1/padel/bookings/{id} - Kunder kan uppdatera sina egna bokningar
    @PutMapping("/{id}")
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

    // DELETE /api/v1/padel/bookings/{id} - Administratörer kan ta bort bokningar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        try {
            PadelBooking bookingToDelete = bookingService.getBookingById(id);
            bookingService.deleteBooking(id);
            // Uppdatera tillgängliga tider när bokningen tas bort
            Court court = bookingToDelete.getCourt();
            court.getAvailableTimes().put(bookingToDelete.getTime(), true); // Markera tiden som ledig
            courtService.updateCourt(court.getId(), court);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
