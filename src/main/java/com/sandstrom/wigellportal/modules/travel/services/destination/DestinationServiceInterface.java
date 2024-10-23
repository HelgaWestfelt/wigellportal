package com.sandstrom.wigellportal.modules.travel.services.destination;

import com.sandstrom.wigellportal.modules.travel.entities.Destination;

import java.util.List;
import java.util.Optional;

public interface DestinationServiceInterface {
    Optional<Destination> findById(int id);

    List<Destination> findAll();

    Destination findDestination(Destination destination);

    void save(Destination destination);

//    void delete(int id);

//    void update(int id, Destination updatedDestination);

}