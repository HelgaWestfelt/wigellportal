package com.westfelt.wigellmcrental.services;


import com.westfelt.wigellmcrental.entities.CinemaMovie;

import java.util.List;

public interface CinemaMovieService {
    List<CinemaMovie> findAll();

    CinemaMovie findById(int id);

    CinemaMovie save(CinemaMovie movie);

    void deleteById(int id);
}
