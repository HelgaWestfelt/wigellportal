package com.sandstrom.wigellportal.modules.motorcyclerental.dao;

import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface McRepository extends JpaRepository<Motorcycle,Integer> {

    List<Motorcycle> findByAvailabilityTrue();

}
