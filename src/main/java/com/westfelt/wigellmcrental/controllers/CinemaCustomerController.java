package com.westfelt.wigellmcrental.controllers;



import com.westfelt.wigellmcrental.entities.CinemaCustomer;
import com.westfelt.wigellmcrental.services.CinemaCustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaCustomerController {

    private CinemaCustomerService customerService;

    public CinemaCustomerController(CinemaCustomerService customerSer){
        customerService = customerSer;
    }

    @GetMapping("/v1/cinema/customers")
    public List<CinemaCustomer>findAll(){
        return customerService.findAll();
    }

    @GetMapping("/v1/cinema/customers/{id}")
    public CinemaCustomer getCustomer (@PathVariable int id) {
        CinemaCustomer customer = customerService.findById(id);
        if (customer == null) {
            throw new RuntimeException("Customer with id " + id + " was not found.");
        }
        return customer;
    }

    @PostMapping("/v1/cinema/customers")
    public CinemaCustomer addCustomer(@RequestBody CinemaCustomer c) {
        c.setId(0);
        return customerService.save(c);
    }

    @DeleteMapping ("v1/cinema/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
        CinemaCustomer customer = customerService.findById(id);
        customerService.deleteById(id);
        return "Customer with id : " + id + " is deleted.";
    }


    @PutMapping("/v1/cinema/customers/{id}")
    public CinemaCustomer updateCustomer(@PathVariable int id, @RequestBody CinemaCustomer c) {
        // Hämta den befintliga kunden
        CinemaCustomer existingCustomer = customerService.findById(id);

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
