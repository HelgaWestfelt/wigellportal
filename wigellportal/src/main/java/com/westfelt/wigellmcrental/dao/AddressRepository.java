package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
