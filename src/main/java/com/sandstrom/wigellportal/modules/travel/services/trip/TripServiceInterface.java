package com.sandstrom.wigellportal.modules.travel.services.trip;


import com.sandstrom.wigellportal.modules.travel.dto.TripDTO;
import com.sandstrom.wigellportal.modules.travel.entities.Trip;

import java.util.List;
import java.util.Optional;

public interface TripServiceInterface {
    Optional<Trip> findById(int id);
    Trip findTrip(Trip trip);
    Trip findTripAdmin(Trip trip);
    List<TripDTO> getAllTrips();
    TripDTO createTripDTO(Trip trip);
    Trip save(Trip trip);
    Trip update(int id, Trip updatedTrip);
    void delete(int id);

}
