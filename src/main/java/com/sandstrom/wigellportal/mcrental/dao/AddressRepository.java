package com.sandstrom.wigellportal.mcrental.dao;

import com.sandstrom.wigellportal.mcrental.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
