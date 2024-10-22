package com.sandstrom.wigellportal.cinema.controllers;




import com.sandstrom.wigellportal.cinema.entities.CinemaBookingVenue;
import com.sandstrom.wigellportal.cinema.services.CinemaBookingVService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class CinemaBookingVController {

    private CinemaBookingVService bookingCVenueService;

    public CinemaBookingVController(CinemaBookingVService bookingCVenueService2){
        bookingCVenueService = bookingCVenueService2;
    }


    @GetMapping("/v1/cinvenbookings/customer/{customerId}")
    public List<CinemaBookingVenue> getBookingsByCustomerId(@PathVariable int customerId) {
        return bookingCVenueService.findAllBookingsByCustomerId(customerId);
    }

    @PostMapping("/v1/cinvenbookings")
    public CinemaBookingVenue addBookingCVenue(@RequestBody CinemaBookingVenue b) {
        b.setId(0);
        return bookingCVenueService.save(b);
    }

    @PutMapping("/v1/cinvenbookings/{id}")
    public CinemaBookingVenue updateBookingCVenue(@PathVariable int id, @RequestBody CinemaBookingVenue b) {
        // Hämta den befintliga kunden
        CinemaBookingVenue existingBookingCVenue = bookingCVenueService.findById(id);

        // Uppdatera fälten i den befintliga kunden
        existingBookingCVenue.setNrOfGuests(b.getNrOfGuests());
        existingBookingCVenue.setCinemaVenue(b.getCinemaVenue());
        existingBookingCVenue.setEntertainment(b.getEntertainment());
        existingBookingCVenue.setTotalPriceSEK(b.getTotalPriceSEK());
        existingBookingCVenue.setTimeForEvent(b.getTimeForEvent());


        return bookingCVenueService.save(existingBookingCVenue);
    }
}



