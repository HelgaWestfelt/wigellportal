package com.sandstrom.wigellportal.address;

import jakarta.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class AddressServiceImpl implements AddressService{

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRep){
        addressRepository = addressRep;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address findByAddress(Address address) {
        if (address.getId() != null) {
            Optional<Address> existingAddress = findById(address.getId());

            if (existingAddress.isPresent()) {
                return existingAddress.get();
            } else {
                return null;
            }
        }else {
            Optional<Address> existingAddress = addressRepository.findByStreetAndZipCodeAndCity(address.getStreet(), address.getZipCode(), address.getCity());

            if (existingAddress.isPresent()) {
                return existingAddress.get();
            } else {
                return null;
            }
        }
    }

    @Transactional
    @Override
    public Address save(Address address) {
        logger.info("Saved address: {}", address);
        return addressRepository.save(address);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        logger.info("Deleted address with id: {}", id);
        addressRepository.deleteById(id);
    }
}
