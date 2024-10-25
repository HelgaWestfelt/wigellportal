package com.sandstrom.wigellportal.modules.padel.controllers;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerService;
import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.services.CourtService;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.modules.padel.services.PadelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/padel")
public class PadelAdminController {

    private final CourtService courtService;
    private final PadelBookingService bookingService;
    private final CustomerService customerService;

    @Autowired
    public PadelAdminController(CourtService courtService, PadelBookingService bookingService, CustomerService customerService) {
        this.courtService = courtService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    // Lista alla kunder
    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    // Lägg till en ny kund
    @PostMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Ta bort en kund
    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
    }

    // Uppdatera kundinformation
    @PutMapping("/customers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails) {
        return customerService.update(id, customerDetails);
    }

    // Administratörer kan lägga till nya padelbanor
    @PostMapping("/courts")
    @PreAuthorize("hasRole('ADMIN')")
    public Court createCourt(@RequestBody Court court) {
        return courtService.createCourt(court);
    }

    // Administratörer kan uppdatera befintliga padelbanor
    @PutMapping("/courts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Court updateCourt(@PathVariable int id, @RequestBody Court courtDetails) {
        return courtService.updateCourt(id, courtDetails);
    }

    // Administratörer kan ta bort en bokning
    @DeleteMapping("/bookings/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
    }
}
