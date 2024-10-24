package com.sandstrom.wigellportal.modules.padel.dao;

import com.sandstrom.wigellportal.modules.padel.entities.PadelAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PadelAddressRepository extends JpaRepository<PadelAddress, Integer> {
}
