package com.westfelt.wigellmcrental.dao;


import com.westfelt.wigellmcrental.entities.CinemaMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaMovieRepository extends JpaRepository<CinemaMovie, Integer> {
}
