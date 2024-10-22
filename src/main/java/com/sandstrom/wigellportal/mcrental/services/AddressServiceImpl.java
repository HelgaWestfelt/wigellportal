package com.sandstrom.wigellportal.mcrental.services;

import com.sandstrom.wigellportal.mcrental.dao.AddressRepository;
import com.sandstrom.wigellportal.mcrental.entities.Address;
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
    public Address findById(int id) {
        Optional<Address> adr = addressRepository.findById(id);
        Address address = null;
        if(adr.isPresent()){
            address = adr.get();
        } else {
            throw new RuntimeException("Address with id: " + id + " could not be found");
        }
        return address;
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
