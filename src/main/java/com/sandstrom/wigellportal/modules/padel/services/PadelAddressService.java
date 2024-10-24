package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.modules.padel.entities.PadelAddress;

import java.util.List;
import java.util.Optional;

public interface PadelAddressService {
    List<PadelAddress> getAllAddresses();
    Optional<PadelAddress> getAddressById(int id);
    PadelAddress createAddress(PadelAddress address);
    PadelAddress updateAddress(int id, PadelAddress addressDetails);
    void deleteAddress(int id);
}
