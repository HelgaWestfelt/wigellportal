package com.sandstrom.wigellportal.address;

import com.sandstrom.wigellportal.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByStreetAndZipCodeAndCity(String street, String zipCode, String city);

}
