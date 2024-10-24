package com.sandstrom.wigellportal.padel.dao;

import com.sandstrom.wigellportal.padel.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Integer> {
}
