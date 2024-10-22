package com.sandstrom.wigellportal.cinema.services;

import com.sandstrom.wigellportal.cinema.entities.CinemaCustomer;

import java.util.List;

public interface CinemaCustomerService {

    List<CinemaCustomer> findAll();

    CinemaCustomer findById(int id);

    CinemaCustomer save(CinemaCustomer customer);

    void deleteById(int id);

}
