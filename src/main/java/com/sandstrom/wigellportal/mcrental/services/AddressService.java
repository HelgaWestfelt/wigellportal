package com.sandstrom.wigellportal.mcrental.services;

import com.sandstrom.wigellportal.mcrental.entities.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();
    Address findById(int id);
    Address save(Address address);
    void deleteById(int id);

}
