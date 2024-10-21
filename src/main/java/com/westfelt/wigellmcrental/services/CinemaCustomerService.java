package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.entities.CinemaCustomer;

import java.util.List;

public interface CinemaCustomerService {

    List<CinemaCustomer> findAll();

    CinemaCustomer findById(int id);

    CinemaCustomer save(CinemaCustomer customer);

    void deleteById(int id);

}
