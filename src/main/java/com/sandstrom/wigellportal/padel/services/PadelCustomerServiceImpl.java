package com.sandstrom.wigellportal.padel.services;

import com.sandstrom.wigellportal.padel.entities.PadelAddress;
import com.sandstrom.wigellportal.padel.entities.PadelCustomer;
import com.sandstrom.wigellportal.mcrental.entities.Role;
import com.sandstrom.wigellportal.padel.dao.PadelAddressRepository;
import com.sandstrom.wigellportal.padel.dao.PadelCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

@Service
public class PadelCustomerServiceImpl implements PadelCustomerService {

    private static final Logger logger = LogManager.getLogger(PadelCustomerServiceImpl.class);

    private final PadelCustomerRepository customerRepository;
    private final PadelAddressRepository addressRepository;

    @Autowired
    public PadelCustomerServiceImpl(PadelCustomerRepository customerRepository, PadelAddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<PadelCustomer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public PadelCustomer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kund med id " + id + " hittades inte"));
    }

    @Override
    @Transactional
    public PadelCustomer createCustomer(PadelCustomer customer) {
        // Spara adressen först om den finns
        PadelAddress address = customer.getAddress();
        if (address != null && address.getId() == 0) {
            // Spara adressen och uppdatera kunden med den sparade adressen
            address = addressRepository.save(address);
            customer.setAddress(address);
        }

        // Tilldela en standardroll om den är null
        if (customer.getRole() == null) {
            customer.setRole(Role.ROLE_USER); // Tilldela standardrollen "USER"
        }

        // Spara kunden
        PadelCustomer savedCustomer = customerRepository.save(customer);

        // Logga skapandet av kunden
        logger.info("En ny kund skapades: {}", savedCustomer);

        return savedCustomer;
    }

    @Override
    @Transactional
    public PadelCustomer updateCustomer(int id, PadelCustomer customerDetails) {
        PadelCustomer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kund med id " + id + " hittades inte"));

        // Uppdatera kundens information
        existingCustomer.setFirstName(customerDetails.getFirstName());
        existingCustomer.setLastName(customerDetails.getLastName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());

        // Uppdatera adress om den finns
        if (customerDetails.getAddress() != null) {
            PadelAddress address = customerDetails.getAddress();
            if (address.getId() == 0) {
                // Om adressen är ny, spara den
                address = addressRepository.save(address);
            }
            existingCustomer.setAddress(address);
        }

        // Uppdatera roll om det behövs
        if (customerDetails.getRole() != null) {
            existingCustomer.setRole(customerDetails.getRole());
        }

        PadelCustomer updatedCustomer = customerRepository.save(existingCustomer);

        // Logga uppdateringen av kunden
        logger.info("Kund med id {} uppdaterades: {}", id, updatedCustomer);

        return updatedCustomer;
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        PadelCustomer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kund med id " + id + " hittades inte"));

        customerRepository.delete(customer);

        // Logga borttagningen av kunden
        logger.info("Kund med id {} togs bort: {}", id, customer);
    }
}
