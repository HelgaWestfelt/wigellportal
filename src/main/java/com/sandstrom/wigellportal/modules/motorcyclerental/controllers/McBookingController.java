package com.sandstrom.wigellportal.modules.motorcyclerental.controllers;

import com.sandstrom.wigellportal.modules.motorcyclerental.services.McBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class McBookingController {

    private McBookingService mcBookingService;

    @Autowired
    public McBookingController(McBookingService mcBookingServ){
        mcBookingService = mcBookingServ;
    }




}
