package com.sandstrom.wigellportal.padel.services;

import com.sandstrom.wigellportal.padel.entities.PadelCustomer;
import java.util.List;

public interface PadelCustomerService {

    List<PadelCustomer> getAllCustomers();

    PadelCustomer getCustomerById(int id);

    PadelCustomer createCustomer(PadelCustomer customer);

    PadelCustomer updateCustomer(int id, PadelCustomer customerDetails);

    void deleteCustomer(int id);
}
