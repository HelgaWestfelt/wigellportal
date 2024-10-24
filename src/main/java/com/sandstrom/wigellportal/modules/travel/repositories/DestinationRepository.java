package com.sandstrom.wigellportal.modules.travel.repositories;

import com.sandstrom.wigellportal.modules.travel.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Optional<Destination> findByCityAndCountry(String city, String country);

}