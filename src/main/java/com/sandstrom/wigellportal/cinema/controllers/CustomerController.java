package com.sandstrom.wigellportal.cinema.controllers;

import com.sandstrom.wigellportal.mcrental.entities.McBooking;
import com.sandstrom.wigellportal.mcrental.entities.Motorcycle;
import com.sandstrom.wigellportal.mcrental.services.CustomerService;
import com.sandstrom.wigellportal.mcrental.services.McBookingService;
import com.sandstrom.wigellportal.mcrental.services.McService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerService customerService;
    private McBookingService mcBookingService;
    private McService mcService;

    @Autowired
    public CustomerController(CustomerService cServ, McBookingService mcBookingServ,
                              McService mcServ){
        customerService = cServ;
        mcBookingService = mcBookingServ;
        mcService = mcServ;
    }

    @GetMapping("/bikes")
    public List<Motorcycle> findAll(){
        return mcService.findAvailableBikes();
    }

    @PostMapping("/book/bikes")
    public McBooking bookMc(@RequestBody McBooking mcBook){
        System.out.println(mcBook.getCustomer());
        mcBook.setId(0);
        McBooking mcBooking = mcBookingService.save(mcBook);
        return mcBooking;
    }

    @PutMapping("/update/mcBooking{id}")
    public ResponseEntity<McBooking> updateBooking(@PathVariable int id, McBooking mcBook){
        McBooking updated = mcBookingService.updateMcBooking(id, mcBook);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/mcBookings")
    public Map<String, List<McBooking>> getAllBookings() {
        return mcBookingService.findAllBookings();
    }





}
