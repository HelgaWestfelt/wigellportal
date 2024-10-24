package com.sandstrom.wigellportal.modules.cinema.dao;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaMovieRepository extends JpaRepository<CinemaMovie, Integer> {
}
