package com.sandstrom.wigellportal.cinema.dao;

import com.sandstrom.wigellportal.cinema.entities.CinemaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaCustomerRepository extends JpaRepository<CinemaCustomer, Integer> {
}
