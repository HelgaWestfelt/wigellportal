package com.sandstrom.wigellportal.modules.travel.services.trip;


import com.sandstrom.wigellportal.modules.travel.entities.Trip;

import java.util.List;
import java.util.Optional;

public interface TripServiceInterface {

    Optional<Trip> findById(int id);

    Trip findTrip(Trip trip);

    Trip findTripAdmin(Trip trip);
    List<Trip> findAll();
    Trip save(Trip trip);
    void delete(int id);
    Trip update(int id, Trip updatedTrip);

}
