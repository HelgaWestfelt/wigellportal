package com.sandstrom.wigellportal.modules.padel.controllers;

import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/padel/courts")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    // Administratörer kan lista alla padelbanor
    @GetMapping
    public List<Court> getAllCourts() {
        return courtService.getAllCourts();
    }

    // Administratörer kan lägga till nya padelbanor
    @PostMapping
    public Court createCourt(@RequestBody Court court) {
        return courtService.createCourt(court);
    }

    // Administratörer kan uppdatera befintliga padelbanor
    @PutMapping("/{id}")
    public Court updateCourt(@PathVariable int id, @RequestBody Court courtDetails) {
        return courtService.updateCourt(id, courtDetails);
    }

    // Administratörer kan ta bort en padelbana
    @DeleteMapping("/{id}")
    public void deleteCourt(@PathVariable int id) {
        courtService.deleteCourt(id);
    }
}
