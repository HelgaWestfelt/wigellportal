package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface McRepository extends JpaRepository<Motorcycle,Integer> {

    List<Motorcycle> findByAvailabilityTrue();

}
