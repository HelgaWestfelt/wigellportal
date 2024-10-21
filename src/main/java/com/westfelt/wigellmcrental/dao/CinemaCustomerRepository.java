package com.westfelt.wigellmcrental.dao;

import com.westfelt.wigellmcrental.entities.CinemaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaCustomerRepository extends JpaRepository<CinemaCustomer, Integer> {
}
