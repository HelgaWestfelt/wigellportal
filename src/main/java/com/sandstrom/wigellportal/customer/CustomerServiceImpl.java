package com.sandstrom.wigellportal.customer;

import com.sandstrom.wigellportal.address.Address;
import com.sandstrom.wigellportal.address.AddressService;
import com.sandstrom.wigellportal.modules.travel.dto.CustomerDTO;
import com.sandstrom.wigellportal.modules.travel.entities.Trip;
import com.sandstrom.wigellportal.modules.travel.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRep, AddressService addressService, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRep;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<Customer> findAll(){
        logger.info("All registered customers have been listed.");
        return customerRepository.findAll();
    }
    @Override
    public Customer findById(int id){
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return customer.get();
        }
        else{
            throw new EntityNotFoundException("Kund med id " + id + " kunde inte hittas.");
        }
    }
    @Override
    public CustomerDTO createCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }
    @Override
    @Transactional
    public Customer save (Customer customer){
        try {
            Address existingAddress = addressService.findByAddress(customer.getAddress());
            if (existingAddress != null) {
                customer.setAddress(existingAddress);
            } else {
                Address savedAddress = addressService.save(customer.getAddress());
                customer.setAddress(savedAddress);
            }

            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customer.setEnabled(true);

            customerRepository.save(customer);
            logger.info("Admin created customer with id {}.", customer.getId());

            return customer;

        }catch (Exception e) {
            throw new IllegalArgumentException("Något gick fel. Kontrollera inmatning av indata.");
        }
    }
    @Override
    @Transactional
    public Customer update(int id, Customer updatedCustomer) {
        Customer existingCustomer = findById(id);

            if (updatedCustomer.getFirstName() != null) {
                existingCustomer.setFirstName(updatedCustomer.getFirstName());
            }
            if (updatedCustomer.getLastName() != null) {
                existingCustomer.setLastName(updatedCustomer.getLastName());
            }
            if (updatedCustomer.getDateOfBirth() != null) {
                existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
            }
            if (updatedCustomer.getEmail() != null) {
                existingCustomer.setEmail(updatedCustomer.getEmail());
            }
            if (updatedCustomer.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            }

            Address existingAddress = addressService.findByAddress(updatedCustomer.getAddress());
            if (existingAddress != null) {
                existingCustomer.setAddress(existingAddress);
            } else {
                Address savedAddress = addressService.save(updatedCustomer.getAddress());
                existingCustomer.setAddress(savedAddress);
            }

            customerRepository.save(existingCustomer);
            logger.info("Admin updated customer with id {}.", existingCustomer.getId());
            return existingCustomer;

    }

    @Override
    @Transactional
    public void deleteById(int id){
        Customer customer = findById(id);

        customer.setActive(false);
        customerRepository.save(customer);
        logger.info("Admin marked customer with id {} as inactive.", id);

    }
}
