package com.sandstrom.wigellportal.modules.travel.services.travelbooking;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerService;
import com.sandstrom.wigellportal.modules.travel.dto.TravelBookingDTO;
import com.sandstrom.wigellportal.modules.travel.entities.TravelBooking;
import com.sandstrom.wigellportal.modules.travel.entities.Trip;
import com.sandstrom.wigellportal.modules.travel.exceptions.EntityNotFoundException;
import com.sandstrom.wigellportal.modules.travel.repositories.TravelBookingRepository;
import com.sandstrom.wigellportal.modules.travel.services.currencyconversion.CurrencyConversionService;
import com.sandstrom.wigellportal.modules.travel.services.trip.TripServiceInterface;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelBookingService implements TravelBookingServiceInterface {
    private final TravelBookingRepository travelBookingRepository;
    private final TripServiceInterface tripService;
    private final CustomerService customerService;
    private final CurrencyConversionService currencyConversionService;
    private final Logger logger = LogManager.getLogger(TravelBookingService.class);

    @Autowired
    public TravelBookingService (TravelBookingRepository travelBookingRepository, TripServiceInterface tripService, CustomerService customerService, CurrencyConversionService currencyConversionService) {
        this.travelBookingRepository = travelBookingRepository;
        this.tripService = tripService;
        this.customerService = customerService;
        this.currencyConversionService = currencyConversionService;
    }
    @Override
    public Optional<TravelBooking> findById(int id) {
        return travelBookingRepository.findById(id);
    }
    @Override
    @Transactional
    public TravelBooking save(TravelBooking travelBooking) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Kontrollera om kunden är angiven
        if (travelBooking.getCustomer() == null || travelBooking.getCustomer().getId() == null) {
            throw new EntityNotFoundException("Ingen kund angiven för bokningen.");
        }

        Customer customer = customerService.findById(travelBooking.getCustomer().getId());

        if (customer != null) {
            travelBooking.setCustomer(customer);
        } else {
            throw new EntityNotFoundException("Kunden kunde inte hittas.");
        }

        // Kontrollera om trip är null innan vi försöker hämta den
        if (travelBooking.getTrip() == null || travelBooking.getTrip().getId() == null) {
            throw new EntityNotFoundException("Ingen resa angiven för bokningen.");
        } else {
            Trip trip = tripService.findTrip(travelBooking.getTrip());

            if (trip != null) {
                travelBooking.setTrip(trip);

                // Räknar ut totalpriset
                travelBooking.setTotalPriceSEK(travelBooking.getTrip().getWeeklyPrice() * travelBooking.getNumberOfWeeks());
                travelBooking.setTotalPricePLN(currencyConversionService.convertSEKtoPLN(travelBooking.getTotalPriceSEK()));

                // Räknar ut returnDate
                travelBooking.setReturnDate(travelBooking.getTravelDate().plusWeeks(travelBooking.getNumberOfWeeks()));

                LocalDateTime currentDateTime = LocalDateTime.now();
                travelBooking.setBookingDate(currentDateTime.format(formatter));

                // Spara bokningen
                travelBookingRepository.save(travelBooking);
                logger.info("Customer created booking with id {}.", travelBooking.getId());
            } else {
                throw new EntityNotFoundException("Resan hittades inte.");
            }
        }
        return travelBooking;
    }
    @Override
    public List<TravelBookingDTO> getBookingsByCustomerId (int id) {
        customerService.findById(id);

        List<TravelBooking> bookings = travelBookingRepository.findTravelBookingByCustomerId(id);
        List<TravelBookingDTO> bookingsDTO = new ArrayList<>();

        for(TravelBooking booking : bookings) {
            bookingsDTO.add(createTravelBookingDTO(booking));
        }
        return bookingsDTO;

    }
    @Override
    public TravelBookingDTO createTravelBookingDTO (TravelBooking travelBooking) {

        return new TravelBookingDTO(
                travelBooking.getId(),
                travelBooking.getBookingDate(),
                travelBooking.getTravelDate(),
                travelBooking.getReturnDate(),
                travelBooking.getNumberOfWeeks(),
                travelBooking.getTotalPriceSEK(),
                travelBooking.getTotalPricePLN(),
                tripService.createTripDTO(travelBooking.getTrip()),
                customerService.createCustomerDTO(travelBooking.getCustomer())
        );
    }
    @Override
    @Transactional
    public TravelBooking update(int id, TravelBooking updatedTravelBooking) {
        Optional<TravelBooking> existingTravelBookingOptional = travelBookingRepository.findById(id);

        if (existingTravelBookingOptional.isEmpty()) {
            throw new EntityNotFoundException("Bokning med id " + id + " hittades inte.");
        } else {
            TravelBooking existingTravelBooking = existingTravelBookingOptional.get();

            if (updatedTravelBooking.getTravelDate() != null) {
                existingTravelBooking.setTravelDate(updatedTravelBooking.getTravelDate());
            }

            if (updatedTravelBooking.getNumberOfWeeks() != null) {
                existingTravelBooking.setNumberOfWeeks(updatedTravelBooking.getNumberOfWeeks());
            }

            existingTravelBooking.setReturnDate(existingTravelBooking.getTravelDate().plusWeeks(existingTravelBooking.getNumberOfWeeks()));

            if (updatedTravelBooking.getTrip() != null) {
                Trip trip = tripService.findTrip(updatedTravelBooking.getTrip());

                if (trip != null) {
                    existingTravelBooking.setTrip(trip);
                    existingTravelBooking.setTotalPriceSEK(existingTravelBooking.getTrip().getWeeklyPrice() * existingTravelBooking.getNumberOfWeeks());
                    existingTravelBooking.setTotalPricePLN(currencyConversionService.convertSEKtoPLN(existingTravelBooking.getTotalPriceSEK()));
                } else {
                    throw new EntityNotFoundException("Resan kunde inte hittas.");
                }
            }
            travelBookingRepository.save(existingTravelBooking);
            logger.info("Customer updated booking with id {}.", existingTravelBooking.getId());

            return existingTravelBooking;
        }
    }
    @Override
    @Transactional
    public void delete(int id) {
        Optional<TravelBooking> optionalTravelBooking = travelBookingRepository.findById(id);

        if (optionalTravelBooking.isPresent()) {
            travelBookingRepository.deleteById(id);
            logger.info("Customer deleted trip with id {}.", id);
        } else {
            throw new EntityNotFoundException("Bokning med id " + id + " finns inte.");
        }
    }
}
