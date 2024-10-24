//package com.sandstrom.wigellportal.mcrental.services;
//
//import com.sandstrom.wigellportal.address.AddressRepository;
//import com.sandstrom.wigellportal.customer.CustomerRepository;
//import com.sandstrom.wigellportal.mcrental.dao.McBookingRepository;
//import com.sandstrom.wigellportal.customer.Customer;
//import com.sandstrom.wigellportal.mcrental.entities.McBooking;
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CustomerServiceImpl implements CustomerService{
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
//
//    private CustomerRepository customerRepository;
//    private AddressRepository addressRepository;
//    private McBookingRepository mcBookingRepository;
//
//    @Autowired
//    public CustomerServiceImpl(CustomerRepository customerRep, AddressRepository addressRep, McBookingRepository mcBookRep){
//        customerRepository = customerRep;
//        addressRepository = addressRep;
//        mcBookingRepository = mcBookRep;
//    }
//
//    @Override
//    public List<Customer> findAll() {
//        return customerRepository.findAll();
//    }
//
//    @Override
//    public Customer findById(int id) {
//        Optional<Customer> cus = customerRepository.findById(id);
//        Customer customer = null;
//        if(cus.isPresent()){
//            customer = cus.get();
//        } else {
//            throw new RuntimeException("Customer with id: " + id + " could not be found");
//        }
//        return customer;
//    }
//
///*    @Transactional
//    @Override
//    public Customer save(Customer customer) {
//        if (customer.getAddress() != null && customer.getAddress().getId() == 0) {
//            // Om adressen är ny, spara den först
//            Address savedAddress = addressRepository.save(customer.getAddress());
//            customer.setAddress(savedAddress);
//        } else if (customer.getAddress() != null && customer.getAddress().getId() > 0) {
//            // Om adressen är befintlig, hämta den
//            Optional<Address> existingAddress = addressRepository.findById(customer.getAddress().getId());
//            existingAddress.ifPresent(customer::setAddress);
//        }
//
//        // Spara kunden med adressen
//        return customerRepository.save(customer);
//    } */
//
//    @Transactional
//    @Override
//    public Customer save(Customer customer) {
//        // Koppla tillfälligt bort bokningar för att undvika cykliska referenser
//        List<McBooking> tempBookings = customer.getMcBookings();
//        customer.setMcBookings(null);
//
//        // Spara kunden först utan bokningar för att undvika StackOverflowError
//        Customer savedCustomer = customerRepository.save(customer);
//
//        // Återställ bokningar efter att kunden är sparad
//        if (tempBookings != null) {
//            for (McBooking booking : tempBookings) {
//                booking.setCustomer(savedCustomer);  // Koppla om till den sparade kunden
//                mcBookingRepository.save(booking);  // Spara varje bokning med den uppdaterade kunden
//            }
//            logger.info("Saved customer with booking: {}", savedCustomer);
//        } else {
//            logger.info("Saved customer: {}", savedCustomer);
//        }
//
//        // Återställ kundens bokningar
//        savedCustomer.setMcBookings(tempBookings);
//
//        return savedCustomer;
//    }
//
//
//
//
//    @Transactional
//    @Override
//    public void deleteById(int id) {
//        logger.info("Admin deleted customer with id: {}", id);
//        customerRepository.deleteById(id);
//    }
//
//
//    @Transactional
//    @Override
//    public Customer updateCustomer(int id, Customer updatedCustomer){
//        return customerRepository.findById(id).map(customer -> {
//            customer.setFirstName(updatedCustomer.getFirstName());
//            customer.setLastName(updatedCustomer.getLastName());
//            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
//            customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
//            customer.setEmail(updatedCustomer.getEmail());
//            customer.setUsername(updatedCustomer.getUsername());
//            customer.setPassword(updatedCustomer.getPassword());
//            customer.setRole(updatedCustomer.getRole());
//            customer.setAddress(updatedCustomer.getAddress());
//
//            logger.info("Admin updated customer: {}", updatedCustomer);
//
//            return customerRepository.save(customer);
//        }).orElseThrow(() -> new RuntimeException("Motorcycle with id: " + id + " could not be found"));
//
//    }
//
//}
//
//
