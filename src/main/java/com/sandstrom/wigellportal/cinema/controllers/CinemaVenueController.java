package com.sandstrom.wigellportal.cinema.controllers;



import com.sandstrom.wigellportal.cinema.entities.CinemaVenue;
import com.sandstrom.wigellportal.cinema.services.CinemaVenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaVenueController {


    private CinemaVenueService venueService;

    public CinemaVenueController(CinemaVenueService venueSer){
        venueService = venueSer;
    }
    @GetMapping("/v1/cinema/rooms")
    public List<CinemaVenue> findAll(){
        return venueService.findAll();
    }

    @GetMapping("/v1/cinema/rooms/{id}")
    public CinemaVenue getVenue (@PathVariable int id) {
        CinemaVenue venue = venueService.findById(id);
        return venue;
    }

    @PutMapping("/v1/cinema/rooms/{id}")
    public CinemaVenue venue(@PathVariable int id, @RequestBody CinemaVenue v) {
        CinemaVenue existingVenue = venueService.findById(id);

        existingVenue.setName(v.getName());
        existingVenue.setMaxNoOfGuests(v.getMaxNoOfGuests());
        existingVenue.setFacilities(v.getFacilities());

        return venueService.save(existingVenue);
    }
}
