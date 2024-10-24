package com.sandstrom.wigellportal.modules.cinema.controllers;



import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaCustomerController {

    private final CustomerService customerService;

    public CinemaCustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/v1/cinema/customers")
    public List<Customer>findAll(){
        return customerService.findAll();
    }

    @GetMapping("/v1/cinema/customers/{id}")
    public Customer getCustomer (@PathVariable int id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new RuntimeException("Customer with id " + id + " was not found.");
        }
        return customer;
    }

    @PostMapping("/v1/cinema/customers")
    public Customer addCustomer(@RequestBody Customer c) {
        c.setId(0);
        return customerService.save(c);
    }

    @DeleteMapping ("v1/cinema/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
        Customer customer = customerService.findById(id);
        customerService.deleteById(id);
        return "Customer with id : " + id + " is deleted.";
    }


    @PutMapping("/v1/cinema/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer c) {
        // Hämta den befintliga kunden
        Customer existingCustomer = customerService.findById(id);

        // Uppdatera fälten i den befintliga kunden
        existingCustomer.setFirstName(c.getFirstName());
        existingCustomer.setLastName(c.getLastName());
        existingCustomer.setPhoneNumber(c.getPhoneNumber());
        existingCustomer.setDateOfBirth(c.getDateOfBirth());
        existingCustomer.setEmail(c.getEmail());
        existingCustomer.setUsername(c.getUsername());
        existingCustomer.setPassword(c.getPassword());
        existingCustomer.setRole(c.getRole());
        //adress

        // Spara den uppdaterade kunden
        return customerService.save(existingCustomer);
    }

}
