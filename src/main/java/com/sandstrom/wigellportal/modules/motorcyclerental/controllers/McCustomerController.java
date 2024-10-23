package com.sandstrom.wigellportal.modules.motorcyclerental.controllers;

import com.sandstrom.wigellportal.customer.CustomerService;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Motorcycle;
import com.sandstrom.wigellportal.modules.motorcyclerental.services.McBookingService;
import com.sandstrom.wigellportal.modules.motorcyclerental.services.McService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class McCustomerController {

    private CustomerService customerService;
    private McBookingService mcBookingService;
    private McService mcService;

    @Autowired
    public McCustomerController(CustomerService cServ, McBookingService mcBookingServ,
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


    @GetMapping("/mcBooking/{id}")
    public List<McBooking> getBookings(@PathVariable int id) {
        return mcBookingService.findBookingByCustomerId(id);
    }

}
