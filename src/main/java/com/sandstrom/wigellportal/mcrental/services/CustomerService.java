package com.sandstrom.wigellportal.mcrental.services;

import com.sandstrom.wigellportal.mcrental.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(int id);
    Customer save(Customer customer);
    void deleteById(int id);
    Customer updateCustomer(int id, Customer customer);

}
