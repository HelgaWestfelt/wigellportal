package com.sandstrom.wigellportal.padel.dao;

import com.sandstrom.wigellportal.padel.entities.PadelAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PadelAddressRepository extends JpaRepository<PadelAddress, Integer> {
}
