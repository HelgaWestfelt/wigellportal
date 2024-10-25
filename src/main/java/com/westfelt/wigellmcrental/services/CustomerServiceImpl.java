package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.dao.AddressRepository;
import com.westfelt.wigellmcrental.dao.CustomerRepository;
import com.westfelt.wigellmcrental.dao.McBookingRepository;
import com.westfelt.wigellmcrental.entities.Address;
import com.westfelt.wigellmcrental.entities.Customer;
import com.westfelt.wigellmcrental.entities.McBooking;
import com.westfelt.wigellmcrental.entities.Motorcycle;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private McBookingRepository mcBookingRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRep, AddressRepository addressRep, McBookingRepository mcBookRep){
        customerRepository = customerRep;
        addressRepository = addressRep;
        mcBookingRepository = mcBookRep;
    }

    @Override
    public List<Customer> findAll() {
        logger.info("Listed all customers");
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> cus = customerRepository.findById(id);
        Customer customer = null;
        if(cus.isPresent()){
            customer = cus.get();
        } else {
            throw new RuntimeException("Customer with id: " + id + " could not be found");
        }
        logger.info("Found customer with id: {}", id);
        return customer;
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        // Koppla tillfälligt bort bokningar för att undvika cykliska referenser
        List<McBooking> tempBookings = customer.getMcBookings();
        customer.setMcBookings(null);

        // Spara kunden först utan bokningar för att undvika StackOverflowError
        Customer savedCustomer = customerRepository.save(customer);

        // Återställ bokningar efter att kunden är sparad
        if (tempBookings != null) {
            for (McBooking booking : tempBookings) {
                booking.setCustomer(savedCustomer);  // Koppla om till den sparade kunden
                mcBookingRepository.save(booking);  // Spara varje bokning med den uppdaterade kunden
            }
            logger.info("Saved customer with booking: {}", savedCustomer);
        } else {
            logger.info("Saved customer: {}", savedCustomer);
        }

        savedCustomer.setMcBookings(tempBookings);

        return savedCustomer;
    }




    @Transactional
    @Override
    public void deleteById(int id) {
        logger.info("Admin deleted customer with id: {}", id);
        customerRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Customer updateCustomer(int id, Customer updatedCustomer){
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setUsername(updatedCustomer.getUsername());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setRole(updatedCustomer.getRole());
            customer.setAddress(updatedCustomer.getAddress());

            logger.info("Admin updated customer: {}", updatedCustomer);

            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Motorcycle with id: " + id + " could not be found"));

    }

}


