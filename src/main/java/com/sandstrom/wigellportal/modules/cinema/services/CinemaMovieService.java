package com.sandstrom.wigellportal.modules.cinema.services;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaMovie;

import java.util.List;

public interface CinemaMovieService {
    List<CinemaMovie> findAll();

    CinemaMovie findById(int id);

    CinemaMovie save(CinemaMovie movie);

    void deleteById(int id);
}
