package com.sandstrom.wigellportal.cinema.dao;


import com.sandstrom.wigellportal.cinema.entities.CinemaMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaMovieRepository extends JpaRepository<CinemaMovie, Integer> {
}
