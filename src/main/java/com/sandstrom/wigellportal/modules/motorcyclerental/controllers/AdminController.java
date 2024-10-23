package com.sandstrom.wigellportal.modules.motorcyclerental.controllers;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerService;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Motorcycle;
import com.sandstrom.wigellportal.modules.motorcyclerental.services.McBookingService;
import com.sandstrom.wigellportal.modules.motorcyclerental.services.McService;
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
        Customer updated = customerService.update(id, customer);
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
