package com.sandstrom.wigellportal.mcrental.dao;

import com.sandstrom.wigellportal.mcrental.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
