package com.sandstrom.wigellportal.mcrental.dao;

import com.sandstrom.wigellportal.mcrental.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface McRepository extends JpaRepository<Motorcycle,Integer> {

    List<Motorcycle> findByAvailabilityTrue();

}
