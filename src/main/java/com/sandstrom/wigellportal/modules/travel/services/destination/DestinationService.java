package com.sandstrom.wigellportal.modules.travel.services.destination;

import com.sandstrom.wigellportal.modules.travel.entities.Destination;
import com.sandstrom.wigellportal.modules.travel.repositories.DestinationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService implements DestinationServiceInterface {
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public Optional<Destination> findById(int id) {
        return destinationRepository.findById(id);
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination findDestination(Destination destination) {
        //Ändra så att den kontrollerar om destinationen som skickas in stämmer med destinationen på id:et?
        if (destination.getId() != null) {
            return destinationRepository.findById(destination.getId()).orElse(null);
        } else {
            return destinationRepository.findByCityAndCountry(destination.getCity(), destination.getCountry()).orElse(null);
        }
    }

    @Override
    @Transactional
    public void save(Destination destination) {
        destinationRepository.save(destination);
    }

//    @Override
//    public void update(int id, Destination updatedDestination) {
//        //Lägg till funktionalitet för att hämta ett befintligt objekt.
//        destinationRepository.save(updatedDestination);
//    }
//
//    @Override
//    public void delete(int id) {
//        destinationRepository.deleteById(id);
//    }

}