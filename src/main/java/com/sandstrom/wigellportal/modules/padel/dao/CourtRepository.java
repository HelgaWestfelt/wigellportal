package com.sandstrom.wigellportal.modules.padel.dao;

import com.sandstrom.wigellportal.modules.padel.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Integer> {
}
