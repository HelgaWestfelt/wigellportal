package com.sandstrom.wigellportal.modules.cinema.services;


import com.sandstrom.wigellportal.modules.cinema.entities.CinemaMovie;

import java.util.List;

public interface CinemaMovieService {
    List<CinemaMovie> findAll();

    CinemaMovie findById(int id);

    CinemaMovie save(CinemaMovie movie);

    CinemaMovie updateMovie(int id, CinemaMovie updatedMovie);

    void deleteById(int id);
}
