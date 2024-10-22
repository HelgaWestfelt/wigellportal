package com.sandstrom.wigellportal.mcrental.services;

import com.sandstrom.wigellportal.mcrental.dao.McRepository;
import com.sandstrom.wigellportal.mcrental.entities.Motorcycle;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class McServiceImpl implements McService{

    private static final Logger logger = LoggerFactory.getLogger(McServiceImpl.class);

    private McRepository mcRepository;

    @Autowired
    public McServiceImpl(McRepository mcRep){
        mcRepository = mcRep;
    }

    @Override
    public List<Motorcycle> findAll() {
        return mcRepository.findAll();
    }

    public List<Motorcycle> findAvailableBikes() {
        return mcRepository.findByAvailabilityTrue();
    }

    @Override
    public Motorcycle findById(int id) {
        Optional<Motorcycle> mc = mcRepository.findById(id);
        Motorcycle motorcycle = null;
        if (mc.isPresent()){
            motorcycle = mc.get();
        } else {
            throw new RuntimeException("Motorcycle with id: " + id + " could not be found");
        }
        return motorcycle;
    }

    @Transactional
    @Override
    public Motorcycle save(Motorcycle motorcycle) {
        logger.info("Admin added a motorcycle: {}", motorcycle);
        return mcRepository.save(motorcycle);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        logger.info("Admin deleted motorcycle with id: {}", id);
        mcRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Motorcycle updateMc(int id, Motorcycle updatedMotorcycle){
       return mcRepository.findById(id).map(motorcycle -> {
            motorcycle.setBrand(updatedMotorcycle.getBrand());
            motorcycle.setModel(updatedMotorcycle.getModel());
            motorcycle.setRegistrationNumber(updatedMotorcycle.getRegistrationNumber());
            motorcycle.setPricePerDay(updatedMotorcycle.getPricePerDay());
            motorcycle.setAvailability(updatedMotorcycle.isAvailability());

            logger.info("Admin updated motorcycle: {}", updatedMotorcycle);

            return mcRepository.save(motorcycle);
        }).orElseThrow(() -> new RuntimeException("Motorcycle with id: " + id + " could not be found"));
    }

}
