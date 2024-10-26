package com.sandstrom.wigellportal.modules.travel.services.trip;

import com.sandstrom.wigellportal.modules.travel.dto.TripDTO;
import com.sandstrom.wigellportal.modules.travel.entities.Destination;
import com.sandstrom.wigellportal.modules.travel.entities.Trip;
import com.sandstrom.wigellportal.modules.travel.repositories.TripRepository;
import com.sandstrom.wigellportal.modules.travel.services.currencyconversion.CurrencyConversionService;
import com.sandstrom.wigellportal.modules.travel.services.destination.DestinationService;
import com.sandstrom.wigellportal.modules.travel.services.destination.DestinationServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripService implements TripServiceInterface {
    private final TripRepository tripRepository;
    private final DestinationServiceInterface destinationService;
    private final CurrencyConversionService currencyConversionService;
    private final Logger logger = LogManager.getLogger(TripService.class);
    @Autowired
    public TripService (TripRepository tripRepository, DestinationServiceInterface destinationService, CurrencyConversionService currencyConversionService) {
        this.tripRepository = tripRepository;
        this.destinationService = destinationService;
        this.currencyConversionService = currencyConversionService;
    }
    @Override
    public Optional<Trip> findById(int id) {
        return tripRepository.findById(id);
    }
    @Override
    public Trip findTrip(Trip trip) {
        if (trip.getId() != null) {
            return tripRepository.findById(trip.getId()).orElse(null);
        } else if (trip.getDestination() != null && trip.getHotel() != null) {
            return tripRepository.findByDestinationAndHotel(trip.getDestination().getCity(),
                    trip.getDestination().getCountry(), trip.getHotel()).orElse(null);
        }
        return null;
    }
    @Override
    public Trip findTripAdmin(Trip trip) {
        if (trip.getId() != null) {
            return tripRepository.findById(trip.getId()).orElse(null);
        } else if (trip.getDestination() != null && trip.getHotel() != null) {
            return tripRepository.findByDestinationHotelAndPrice(
                    trip.getDestination().getCity(),
                    trip.getDestination().getCountry(),
                    trip.getHotel(),
                    trip.getWeeklyPrice()
            ).orElse(null);
        }
        return null;
    }
    @Override
    public List<TripDTO> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDTO> tripsDTO = new ArrayList<>();

        for (Trip trip : trips) {
            tripsDTO.add(createTripDTO(trip));
        }
        return tripsDTO;
    }
    @Override
    public TripDTO createTripDTO (Trip trip) {
        return new TripDTO(
                trip.getId(),
                trip.getDestination(),
                trip.getHotel(),
                trip.getWeeklyPrice(),
                currencyConversionService.convertSEKtoPLN(trip.getWeeklyPrice())
        );
    }
    @Override
    @Transactional
    public Trip save(Trip trip) {
        Trip existingTrip = findTripAdmin(trip);

        if (existingTrip != null) {
            throw new IllegalArgumentException("Resan finns redan.");
        } else {
            Destination existingDestination = destinationService.findDestination(trip.getDestination());

            if (existingDestination != null) {
                trip.setDestination(existingDestination);
            } else {
                destinationService.save(trip.getDestination());
                logger.info("Admin created destination with id {}.", trip.getDestination().getId());
            }
            tripRepository.save(trip);
            logger.info("Admin created trip with id {}.", trip.getId());
            return trip;
        }
    }
    @Override
    @Transactional
    public Trip update(int id, Trip updatedTrip) {
        Optional<Trip> existingTripOptional = tripRepository.findById(id);
        Trip existingTrip = null;

        if (existingTripOptional.isPresent()) {
            existingTrip = existingTripOptional.get();

            if (updatedTrip.getHotel() != null) {
                existingTrip.setHotel(updatedTrip.getHotel());
            }

            if (updatedTrip.getWeeklyPrice() != null) {
                existingTrip.setWeeklyPrice(updatedTrip.getWeeklyPrice());
            }

            if (updatedTrip.getDestination() != null) {
                Destination destination = destinationService.findDestination(updatedTrip.getDestination());

                if (destination != null) {
                    existingTrip.setDestination(destination);
                } else {
                    throw new EntityNotFoundException("Destinationen hittades inte.");
                }
            }
            tripRepository.save(existingTrip);
            logger.info("Admin updated trip with id {}.", existingTrip.getId());
        } else {
            throw new EntityNotFoundException("Resa med ID " + id + " hittades inte.");
        }

        return existingTrip;
    }
    @Override
    @Transactional
    public void delete(int id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);

        if (optionalTrip.isPresent()) {
            tripRepository.deleteById(id);
            logger.info("Admin deleted trip with id {}.", id);
        } else {
            throw new EntityNotFoundException("Resa med id " + id + " finns inte.");
        }
    }

}
