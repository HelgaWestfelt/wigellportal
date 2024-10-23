package com.sandstrom.wigellportal.address;

import com.sandstrom.wigellportal.address.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();
    Address findByAddress(Address address);
    Optional<Address> findById(int id);
    Address save(Address address);
    void deleteById(int id);

}
