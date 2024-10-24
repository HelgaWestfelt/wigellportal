package com.sandstrom.wigellportal.modules.padel.controllers;

import com.sandstrom.wigellportal.modules.padel.entities.PadelCustomer; // Ändrat från Customer till PadelCustomer
import com.sandstrom.wigellportal.modules.padel.services.PadelCustomerService; // Ändrat från CustomerService till PadelCustomerService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/padel/customers")
public class PadelCustomerController {

    private final PadelCustomerService customerService; // Ändrat från CustomerService till PadelCustomerService

    @Autowired
    public PadelCustomerController(PadelCustomerService customerService) {
        this.customerService = customerService;
    }

    // Administratörer kan lista alla kunder
    @GetMapping
    public List<PadelCustomer> getAllCustomers() { // Ändrat från Customer till PadelCustomer
        return customerService.getAllCustomers();
    }

    // Administratörer kan lägga till nya kunder
    @PostMapping
    public PadelCustomer createCustomer(@RequestBody PadelCustomer customer) { // Ändrat från Customer till PadelCustomer
        return customerService.createCustomer(customer);
    }

    // Administratörer kan uppdatera befintliga kunder
    @PutMapping("/{id}")
    public PadelCustomer updateCustomer(@PathVariable int id, @RequestBody PadelCustomer customerDetails) { // Ändrat från Customer till PadelCustomer
        return customerService.updateCustomer(id, customerDetails);
    }

    // Administratörer kan ta bort kunder
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
