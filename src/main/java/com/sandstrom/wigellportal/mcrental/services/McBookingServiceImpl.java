package com.sandstrom.wigellportal.mcrental.services;

import com.sandstrom.wigellportal.mcrental.dao.McBookingRepository;
import com.sandstrom.wigellportal.mcrental.entities.McBooking;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class McBookingServiceImpl implements McBookingService {

    private static final Logger logger = LoggerFactory.getLogger(McBookingServiceImpl.class);

    private McBookingRepository mcBookingRepository;

    @Autowired
    public McBookingServiceImpl(McBookingRepository mcBookRep){
        mcBookingRepository = mcBookRep;
    }

    @Override
    public List<McBooking> findAll() {
        return mcBookingRepository.findAll();
    }

    @Override
    public McBooking findById(int id) {
        Optional<McBooking> mcBook = mcBookingRepository.findById(id);
        McBooking mcBooking = null;
        if(mcBook.isPresent()){
            mcBooking = mcBook.get();
        } else {
            throw new RuntimeException("Booking with id " + id + " could not be found");
        }
        return mcBooking;
    }

    @Transactional
    @Override
    public McBooking save(McBooking booking) {
        logger.info("Customer made a new booking: {}", booking);
        return mcBookingRepository.save(booking);
    }


    @Transactional
    @Override
    public String deleteById(int id) {
        mcBookingRepository.deleteById(id);
        logger.info("Admin deleted booking with id: {}", id);
        return "Booking with id " + id + " is deleted";
    }

    @Transactional
    @Override
    public McBooking updateMcBooking(int id, McBooking updatedMcBooking){
        return mcBookingRepository.findById(id).map(mcBooking -> {
            mcBooking.setStartDate(updatedMcBooking.getStartDate());
            mcBooking.setEndDate(updatedMcBooking.getEndDate());
            mcBooking.setPrice(updatedMcBooking.getPrice());
            mcBooking.setCustomer(updatedMcBooking.getCustomer());
            mcBooking.setMotorcycles(updatedMcBooking.getMotorcycles());
            return mcBookingRepository.save(mcBooking);
        }).orElseThrow(() -> new RuntimeException("Booking with id: " + id + " could not be found"));
    }

    @Transactional
    @Override
    // Hämta både aktiva och tidigare bokningar
    public Map<String, List<McBooking>> findAllBookings() {
        LocalDate currentDate = LocalDate.now();
        List<McBooking> activeBookings = mcBookingRepository.findByEndDateAfter(currentDate);
        List<McBooking> pastBookings = mcBookingRepository.findByEndDateBefore(currentDate);

        // Lägger in båda listorna i en Map för att strukturera responsen
        Map<String, List<McBooking>> mcBookings = new HashMap<>();
        mcBookings.put("activeBookings", activeBookings);
        mcBookings.put("pastBookings", pastBookings);

        return mcBookings;
    }

}
