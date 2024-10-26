package com.sandstrom.wigellportal.modules.cinema.controllers;
;
import com.sandstrom.wigellportal.modules.cinema.entities.CinemaVenue;
import com.sandstrom.wigellportal.modules.cinema.services.CinemaVenueService;
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
        return venueService.findById(id);
    }

    @PutMapping("/v1/cinema/rooms/{id}")
    public CinemaVenue updateVenue(@PathVariable int id, @RequestBody CinemaVenue v) {
        return venueService.updateVenue(id, v);
    }
}
