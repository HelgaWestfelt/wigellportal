package com.sandstrom.wigellportal.modules.travel.controllers;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerService;
import com.sandstrom.wigellportal.modules.travel.dto.TripDTO;
import com.sandstrom.wigellportal.modules.travel.entities.Trip;
import com.sandstrom.wigellportal.modules.travel.services.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/travel")
//@RequestMapping(path="/travel")
public class TravelAdminController {
    private final CustomerService customerService;
    private final TripService tripService;

    @Autowired
    public TravelAdminController (CustomerService customerService, TripService tripService) {
        this.customerService = customerService;
        this.tripService = tripService;
    }

    //Hantering av kunder

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> listCustomers() {
        //logger.info("Admin retrieved all customers");
        List<Customer> customers = customerService.findAll();

        StringBuilder responseMessage = new StringBuilder("Kundregister:\n\n");

        for (Customer customer : customers) {
            responseMessage.append(customer.toString()).append("\n\n\n");
        }

        return ResponseEntity.ok(responseMessage.toString());
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.ok("Ny kund har skapats med id: " + savedCustomer.getId() + ".\n\n" + savedCustomer);
    }

    @PutMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Customer savedCustomer = customerService.update(id, updatedCustomer);
        return ResponseEntity.ok("Kund med id: " + id + " har uppdaterats.\n\n" + savedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Kund med id: " + id + " har tagits bort.");
    }

    //Hantering av resor

    @PostMapping("/destinations")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> addTrip(@RequestBody Trip trip) {
        Trip savedTrip = tripService.save(trip);
        TripDTO tripDTO = tripService.createTripDTO(savedTrip);
        return ResponseEntity.ok("Ny resa har skapats med id: " + tripDTO.getId() + ".\n\n" + tripDTO);
    }

    @PutMapping("/destinations/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> updateTrip(@PathVariable int id, @RequestBody Trip updatedTrip) {
        Trip trip = tripService.update(id, updatedTrip);
        TripDTO tripDTO = tripService.createTripDTO(trip);
        return ResponseEntity.ok("Resa med id: " + id + " har uppdaterats.\n\n" + tripDTO);
    }

    @DeleteMapping("/destinations/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Endast ADMIN kan nå denna endpoint
    public ResponseEntity<String> deleteTrip(@PathVariable int id) {
        tripService.delete(id);
        return ResponseEntity.ok("Resa med id: " + id + " har tagits bort.");
    }
}
