package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.entities.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();
    Address findById(int id);
    Address save(Address address);
    void deleteById(int id);

}
