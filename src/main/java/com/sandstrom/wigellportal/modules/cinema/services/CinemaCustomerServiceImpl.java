//package com.sandstrom.wigellportal.cinema.services;
//
//
//
//import com.sandstrom.wigellportal.customer.Customer;
//import com.sandstrom.wigellportal.customer.CustomerRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CinemaCustomerServiceImpl implements CinemaCustomerService {
//
//
//    private PasswordEncoder passwordEncoder;
//    private static final Logger logger = LoggerFactory.getLogger(CinemaCustomerServiceImpl.class);
//    private CustomerRepository customerRepository;
//
//
//    @Autowired
//    public CinemaCustomerServiceImpl(CustomerRepository customerRep, PasswordEncoder passwordEncoder){
//        this.customerRepository = customerRep;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public List<Customer> findAll(){
//        logger.info("All registered customers have been listed");
//        return customerRepository.findAll();
//    }
//
//    @Override
//    public Customer findById(int id){
//        Optional<Customer> c = customerRepository.findById(id);
//        Customer customer = null;
//        if(c.isPresent()){
//            customer = c.get();
//        }
//        else{
//            throw new RuntimeException("Customer with id " + id + " could not be found.");
//        }
//        logger.info("Customer with id " + " was listed.");
//        return customer;
//    }
////    @Override
////    public Customer save (Customer customer){
////        String encodedPassword = passwordEncoder.encode(customer.getPassword());
////        customer.setPassword(encodedPassword);
////        return customerRepository.save(customer);
////    }
//
//    @Override
//    public Customer save (Customer customer){
//        String encodedPassword = passwordEncoder.encode(customer.getPassword());
//        customer.setPassword(encodedPassword);
//        logger.info("New customer was saved.");
//        return customerRepository.save(customer);
//    }
//
//
//    @Override
//    public void deleteById(int id){
//        logger.info("Customer with id " + id + " was deleted.");
//        customerRepository.deleteById(id);
//    }
//
//
//
//
//}
