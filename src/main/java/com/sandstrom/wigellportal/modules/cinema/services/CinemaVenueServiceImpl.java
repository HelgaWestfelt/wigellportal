package com.sandstrom.wigellportal.modules.cinema.services;



import com.sandstrom.wigellportal.modules.cinema.dao.CinemaVenueRepository;
import com.sandstrom.wigellportal.modules.cinema.entities.CinemaVenue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaVenueServiceImpl implements CinemaVenueService {
    private static final Logger logger = LoggerFactory.getLogger(CinemaMovieServiceImpl.class);
    private CinemaVenueRepository venueRepository;

    @Autowired
    public CinemaVenueServiceImpl(CinemaVenueRepository venueRep){
        venueRepository = venueRep;
    }


    @Override
    public List<CinemaVenue> findAll(){
        return venueRepository.findAll();
    }


    @Override
    public CinemaVenue findById(int id){
        Optional<CinemaVenue> v = venueRepository.findById(id);
        CinemaVenue venue = null;
        if(v.isPresent()){
            venue = v.get();
        }
        else{
            throw new RuntimeException("Venue with id  " + id + " was not found.");
        }
        return venue;
    }

    @Override
    public CinemaVenue save (CinemaVenue venue){
    logger.info("New venue " + venue.getId() + " was added by admin");
        return venueRepository.save(venue);
    }

    @Override
    public CinemaVenue updateVenue(int id, CinemaVenue updatedVenue) {
        CinemaVenue existingVenue = findById(id);
        existingVenue.setName(updatedVenue.getName());
        existingVenue.setMaxNoOfGuests(updatedVenue.getMaxNoOfGuests());
        existingVenue.setFacilities(updatedVenue.getFacilities());
        logger.info("Venue with id " + id + " was updated by admin.");
        return venueRepository.save(existingVenue);
    }

    @Override
    public void deleteById(int id){

        venueRepository.deleteById(id);
    }
}

