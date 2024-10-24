package com.sandstrom.wigellportal.customer;

import com.sandstrom.wigellportal.modules.travel.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    Customer update(int id, Customer customer);

    void deleteById(int id);

    CustomerDTO createCustomerDTO(Customer customer);

}
