package com.westfelt.wigellmcrental.controllers;

import com.westfelt.wigellmcrental.services.McBookingService;
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
