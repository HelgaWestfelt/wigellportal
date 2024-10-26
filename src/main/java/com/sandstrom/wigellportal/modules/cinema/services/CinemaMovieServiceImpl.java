package com.sandstrom.wigellportal.modules.cinema.services;



import com.sandstrom.wigellportal.modules.cinema.dao.CinemaMovieRepository;
import com.sandstrom.wigellportal.modules.cinema.entities.CinemaMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaMovieServiceImpl implements CinemaMovieService {

    private static final Logger logger = LoggerFactory.getLogger(CinemaMovieServiceImpl.class);

    private CinemaMovieRepository movieRepository;

    @Autowired
    public CinemaMovieServiceImpl(CinemaMovieRepository movieRep){
        movieRepository = movieRep;
    }

    @Override
    public List<CinemaMovie> findAll() {
        logger.info("All movies were listed.");
        return movieRepository.findAll();
    }

    @Override
    public CinemaMovie findById(int id) {
        Optional<CinemaMovie> m = movieRepository.findById(id);
        CinemaMovie movie = null;
        if (m.isPresent()) {
            movie = m.get();
        } else {
            throw new RuntimeException("Movie with " + id + " could not be found.");
        }
        return movie;
    }

    @Override
    public CinemaMovie save (CinemaMovie movie){
        String savedMovie = movieRepository.save(movie).getTitle();
        logger.info("Movie " + savedMovie+ " was added by admin.");
        return movieRepository.save(movie);
    }

    @Override
    public CinemaMovie updateMovie(int id, CinemaMovie updatedMovie) {
        CinemaMovie existingMovie = findById(id);

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setLength(updatedMovie.getLength());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setAgeLimit(updatedMovie.getAgeLimit());

        logger.info("Movie with id " + id + " was updated by admin.");
        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteById(int id) {

        Optional <CinemaMovie> optionalMovie = movieRepository.findById(id);

        if(optionalMovie.isPresent()){
            CinemaMovie movieToDelete = optionalMovie.get();
            movieRepository.deleteById(id);
            logger.info("Movie " + movieToDelete.getTitle() + " was deleted by admin." );
        }
        else {
            logger.warn("Attempted to delete movie with id " + id + " but it was not found.");
        }
    }
}
