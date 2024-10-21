package com.westfelt.wigellmcrental.services;



import com.westfelt.wigellmcrental.dao.CinemaVenueRepository;
import com.westfelt.wigellmcrental.entities.CinemaVenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaVenueServiceImpl implements CinemaVenueService {

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
        return venueRepository.save(venue);
    }

    @Override
    public void deleteById(int id){

        venueRepository.deleteById(id);
    }
}

