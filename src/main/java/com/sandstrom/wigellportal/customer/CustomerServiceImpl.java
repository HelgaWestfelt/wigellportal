package com.sandstrom.wigellportal.customer;

import com.sandstrom.wigellportal.address.Address;
import com.sandstrom.wigellportal.address.AddressService;
import com.sandstrom.wigellportal.modules.travel.dto.CustomerDTO;
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

    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private CustomerRepository customerRepository;

    private final AddressService addressService;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRep, AddressService addressService, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRep;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Customer> findAll(){
        logger.info("All registered customers have been listed");
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id){
        Optional<Customer> c = customerRepository.findById(id);
        Customer customer = null;
        if(c.isPresent()){
            customer = c.get();
        }
        else{
            throw new RuntimeException("Customer with id " + id + " could not be found.");
        }
        logger.info("Customer with id " + " was listed.");
        return customer;
    }

    @Override
    @Transactional
    public Customer save (Customer customer){
        Address existingAddress = addressService.findByAddress(customer.getAddress());
        if (existingAddress != null) {
            customer.setAddress(existingAddress);
        } else {
            Address savedAddress = addressService.save(customer.getAddress());
            customer.setAddress(savedAddress);
        }

        customerRepository.save(customer);
        logger.info("Admin created customer with id {}.", customer.getId());

        return customer;
    }

    @Override
    @Transactional
    public Customer update(int id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isEmpty()) {
            //throw new EntityNotFoundException("Kund med ID " + id + " hittades inte.");
        }

        Customer existingCustomer = existingCustomerOptional.get();
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
    public void deleteById(int id){
        logger.info("Customer with id " + id + " was deleted.");
        customerRepository.deleteById(id);
    }

    public CustomerDTO createCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName()
        );
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


}
