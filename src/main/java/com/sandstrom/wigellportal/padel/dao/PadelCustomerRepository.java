package com.sandstrom.wigellportal.padel.dao;

import com.sandstrom.wigellportal.padel.entities.PadelCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PadelCustomerRepository extends JpaRepository<PadelCustomer, Integer> {

    // Hitta kund baserat på användarnamn
    Optional<PadelCustomer> findByUsername(String username);
}
