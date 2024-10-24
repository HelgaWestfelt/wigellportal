package com.sandstrom.wigellportal.padel.services;

import com.sandstrom.wigellportal.padel.entities.Court;
import com.sandstrom.wigellportal.padel.dao.CourtRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtServiceImpl implements CourtService {

    private static final Logger logger = LogManager.getLogger(CourtServiceImpl.class);

    private final CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> getAllCourts() {
        logger.info("Hämtar alla padelbanor");
        return courtRepository.findAll();
    }

    @Override
    public Court getCourtById(int id) {
        logger.info("Hämtar padelbana med ID {}", id);
        return courtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Padelbana med id " + id + " hittades inte"));
    }

    @Override
    public Court createCourt(Court court) {
        Court savedCourt = courtRepository.save(court);
        logger.info("Admin skapade en ny padelbana med namn '{}' och ID {}", court.getName(), savedCourt.getId());
        return savedCourt;
    }

    @Override
    public Court updateCourt(int id, Court courtDetails) {
        Court existingCourt = courtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Padelbana med id " + id + " hittades inte"));

        existingCourt.setName(courtDetails.getName());
        existingCourt.setLocation(courtDetails.getLocation());
        existingCourt.setPricePerHour(courtDetails.getPricePerHour());
        existingCourt.setDescription(courtDetails.getDescription());

        Court updatedCourt = courtRepository.save(existingCourt);
        logger.info("Admin uppdaterade padelbana med ID {}. Ny information: namn '{}', plats '{}', pris per timme '{}'",
                id, courtDetails.getName(), courtDetails.getLocation(), courtDetails.getPricePerHour());
        return updatedCourt;
    }

    @Override
    public void deleteCourt(int id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Padelbana med id " + id + " hittades inte"));

        courtRepository.delete(court);
        logger.info("Admin tog bort padelbana med ID {}", id);
    }
}
