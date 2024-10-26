package com.sandstrom.wigellportal.modules.travel.controllers;

import com.sandstrom.wigellportal.modules.travel.dto.TravelBookingDTO;
import com.sandstrom.wigellportal.modules.travel.dto.TripDTO;
import com.sandstrom.wigellportal.modules.travel.entities.TravelBooking;
import com.sandstrom.wigellportal.modules.travel.services.travelbooking.TravelBookingService;
import com.sandstrom.wigellportal.modules.travel.services.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/travel")
//@RequestMapping(path="/travel")
public class TravelCustomerController {
    private final TripService tripService;
    private final TravelBookingService travelBookingService;

    @Autowired
    public TravelCustomerController (TripService tripService, TravelBookingService travelBookingService) {
        this.tripService = tripService;
        this.travelBookingService = travelBookingService;
    }

    //Listning av resmål
    @GetMapping("/trips")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> getTrips() {
        List<TripDTO> trips = tripService.getAllTrips();

        StringBuilder responseMessage = new StringBuilder("Resemål:\n\n");

        for (TripDTO trip : trips) {
            responseMessage.append(trip.toString()).append("\n\n\n");
        }

        return ResponseEntity.ok(responseMessage.toString());
    }

    //Bokning av resa
    @PostMapping("/trips")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> bookTrip(@RequestBody TravelBooking booking) {
        TravelBooking travelBooking = travelBookingService.save(booking);
        TravelBookingDTO travelBookingDTO = travelBookingService.createTravelBookingDTO(travelBooking);
        return ResponseEntity.ok("Tack för din bokning!\n\n" + travelBookingDTO);
    }

    //Uppdatering av resa

    @PutMapping("/trips/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateTrip(@PathVariable int id, @RequestBody TravelBooking updatedBooking) {
        TravelBooking updatedTravelBooking = travelBookingService.update(id, updatedBooking);
        TravelBookingDTO updatedTravelBookingDTO = travelBookingService.createTravelBookingDTO(updatedTravelBooking);

        return ResponseEntity.ok("Din bokning är uppdaterad!\n\n" + updatedTravelBookingDTO);
    }

    //Visa bokningar

    @GetMapping("/trips/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getBookings(@PathVariable int id) {
        List<TravelBookingDTO> bookings = travelBookingService.getBookingsByCustomerId(id);

        if (bookings.isEmpty()) {
            return ResponseEntity.ok("Du har inga bokningar.");
        }else{
            StringBuilder responseMessage = new StringBuilder("Dina bokningar:\n\n");

            for (TravelBookingDTO booking : bookings) {
                responseMessage.append(booking.toString()).append("\n\n");
            }

            return ResponseEntity.ok(responseMessage.toString());
        }
    }

}
