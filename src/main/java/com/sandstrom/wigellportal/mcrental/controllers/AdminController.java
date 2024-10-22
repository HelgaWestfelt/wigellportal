package com.sandstrom.wigellportal.mcrental.controllers;

import com.sandstrom.wigellportal.mcrental.entities.Customer;
import com.sandstrom.wigellportal.mcrental.entities.McBooking;
import com.sandstrom.wigellportal.mcrental.entities.Motorcycle;

import com.sandstrom.wigellportal.mcrental.services.CustomerService;
import com.sandstrom.wigellportal.mcrental.services.McBookingService;
import com.sandstrom.wigellportal.mcrental.services.McService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private CustomerService customerService;
    private McService mcService;

    private McBookingService mcBookingService;

    @Autowired
    public AdminController(CustomerService cServ, McService mcServ, McBookingService mcBookServ){
        customerService = cServ;
        mcService = mcServ;
        mcBookingService = mcBookServ;
    }

    @GetMapping("/customers")
    public List<Customer> findAllC(){
        return customerService.findAll();
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer cus){
        Customer customer = customerService.save(cus);
        System.out.println(customer.toString());
        return customer;
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        Customer updated = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
       Customer customer = customerService.findById(id);
        if(customer == null){
            throw new RuntimeException("Customer with id " + id + " could not be found");
        }
        customerService.deleteById(id);
        return "Customer with id " + id + " is deleted";
    }

    @GetMapping("/list/bikes")
    public List<Motorcycle> findAllB(){
        return mcService.findAll();
    }

    @PostMapping("/add/bikes")
    public Motorcycle addMc(@RequestBody Motorcycle mc){
        System.out.println(mc.getBrand());
        Motorcycle motorcycle = mcService.save(mc);
        return motorcycle;
    }

    @PutMapping("/update/bikes/{id}")
    public ResponseEntity<Motorcycle> updateMc(@PathVariable int id, @RequestBody Motorcycle mc){
        Motorcycle updated = mcService.updateMc(id, mc);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/bookings/{id}")
    public String deleteBooking(@PathVariable int id){
        McBooking mcBooking = mcBookingService.findById(id);
        if (mcBooking == null){
            throw new RuntimeException("Booking with id " + id + " could not be found");
        }
        return "Booking with id " + id + " is deleted";
    }

}
