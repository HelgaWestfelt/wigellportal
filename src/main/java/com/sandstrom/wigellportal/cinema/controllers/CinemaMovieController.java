package com.sandstrom.wigellportal.cinema.controllers;




import com.sandstrom.wigellportal.cinema.entities.CinemaMovie;
import com.sandstrom.wigellportal.cinema.services.CinemaMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class CinemaMovieController {
    private CinemaMovieService movieService;

    public CinemaMovieController(CinemaMovieService movieSer){
        movieService = movieSer;
    }

    @GetMapping("/v1/movies")
    public List<CinemaMovie> findAll(){
        return movieService.findAll();
    }

    @PostMapping("/v1/movies")
    public CinemaMovie addMovie(@RequestBody CinemaMovie m) {
        m.setId(0);
        return movieService.save(m);
    }

    @DeleteMapping ("v1/movies/{id}")
    public String deleteMovie(@PathVariable int id){
        CinemaMovie movie = movieService.findById(id);
        movieService.deleteById(id);
        return "Movie with id " + id + " is deleted.";
    }
    @PutMapping("/v1/movies/{id}")
    public CinemaMovie updateMovie(@PathVariable int id, @RequestBody CinemaMovie m) {
        CinemaMovie existingMovie = movieService.findById(id);

        existingMovie.setTitle(m.getTitle());
        existingMovie.setLength(m.getLength());
        existingMovie.setGenre(m.getGenre());
        existingMovie.setAgeLimit(m.getAgeLimit());

        return movieService.save(existingMovie);
    }

}
