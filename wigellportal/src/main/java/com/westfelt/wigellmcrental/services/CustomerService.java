package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(int id);
    Customer save(Customer customer);
    void deleteById(int id);
    Customer updateCustomer(int id, Customer customer);

}
