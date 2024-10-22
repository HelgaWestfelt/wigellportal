package com.sandstrom.wigellportal.cinema.services;


import com.sandstrom.wigellportal.cinema.entities.CinemaMovie;

import java.util.List;

public interface CinemaMovieService {
    List<CinemaMovie> findAll();

    CinemaMovie findById(int id);

    CinemaMovie save(CinemaMovie movie);

    void deleteById(int id);
}
