package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerRepository;
import com.sandstrom.wigellportal.modules.padel.dao.CourtRepository;
import com.sandstrom.wigellportal.modules.padel.dao.PadelBookingRepository;
import com.sandstrom.wigellportal.modules.padel.entities.Court;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PadelBookingServiceImpl implements PadelBookingService {

    private static final Logger logger = LogManager.getLogger(PadelBookingServiceImpl.class);

    private final PadelBookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final CourtRepository courtRepository;
    private final PadelCurrencyConversionService currencyConversionService;

    @Autowired
    public PadelBookingServiceImpl(PadelBookingRepository bookingRepository,
                                   CustomerRepository customerRepository,
                                   CourtRepository courtRepository,
                                   PadelCurrencyConversionService currencyConversionService) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.courtRepository = courtRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public List<PadelBooking> getAllBookings() {
        logger.info("Hämtar alla bokningar");
        return bookingRepository.findAll();
    }

    @Override
    public List<PadelBooking> getBookingsByUsername(String username) {
        logger.info("Hämtar bokningar för användare {}", username);
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kund med användarnamn " + username + " hittades inte"));
        return bookingRepository.findByCustomer(customer);
    }

    @Override
    public PadelBooking getBookingById(int id) {
        logger.info("Hämtar bokning med ID {}", id);
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bokning med id " + id + " hittades inte"));
    }

    @Override
    public PadelBooking getBookingByCustomerAndId(String username, int id) {
        logger.info("Hämtar bokning med ID {} för användare {}", id, username);
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kund med användarnamn " + username + " hittades inte"));
        return bookingRepository.findByCustomerAndId(customer, id)
                .orElseThrow(() -> new RuntimeException("Bokning med id " + id + " hittades inte för kunden"));
    }

    @Override
    public PadelBooking createBookingForCustomer(PadelBooking booking, String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kund med användarnamn " + username + " hittades inte"));
        booking.setCustomer(customer);

        int courtId = booking.getCourt().getId();
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Bana med id " + courtId + " hittades inte"));

        if (isTimeBooked(court, booking.getTime())) {
            throw new RuntimeException("Tiden är redan bokad på denna bana.");
        }

        booking.setCourt(court);

        int totalPriceInSek = court.getPricePerHour();
        booking.setTotalPrice(totalPriceInSek);

        if (booking.getCurrency() == null || booking.getCurrency().isEmpty()) {
            booking.setCurrency("SEK");
        }

        if ("SEK".equalsIgnoreCase(booking.getCurrency()) && totalPriceInSek > 0) {
            double totalPriceInEur = currencyConversionService.convertToEuro(totalPriceInSek);
            booking.setTotalPriceEur(totalPriceInEur);
        }

        court.getAvailableTimes().put(booking.getTime(), false);

        PadelBooking savedBooking = bookingRepository.save(booking);
        logger.info("Kund {} bokade en tid på bana {} för tid {} och datum {}",
                username, courtId, booking.getTime(), booking.getDate());

        return savedBooking;
    }

    @Override
    public PadelBooking updateCustomerBooking(int id, PadelBooking bookingDetails, String username) {
        PadelBooking existingBooking = getBookingByCustomerAndId(username, id);

        int courtId = bookingDetails.getCourt().getId();
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new RuntimeException("Bana med id " + courtId + " hittades inte"));

        if (isTimeBooked(court, bookingDetails.getTime()) && !existingBooking.getTime().equals(bookingDetails.getTime())) {
            throw new RuntimeException("Tiden är redan bokad på denna bana.");
        }

        court.getAvailableTimes().put(existingBooking.getTime(), true);

        existingBooking.setCourt(court);
        existingBooking.setDate(bookingDetails.getDate());
        existingBooking.setTime(bookingDetails.getTime());
        existingBooking.setPlayersCount(bookingDetails.getPlayersCount());

        int totalPriceInSek = court.getPricePerHour();
        existingBooking.setTotalPrice(totalPriceInSek);

        if ("SEK".equalsIgnoreCase(existingBooking.getCurrency()) && totalPriceInSek > 0) {
            double totalPriceInEur = currencyConversionService.convertToEuro(totalPriceInSek);
            existingBooking.setTotalPriceEur(totalPriceInEur);
        }

        court.getAvailableTimes().put(existingBooking.getTime(), false);

        PadelBooking updatedBooking = bookingRepository.save(existingBooking);
        logger.info("Kund {} uppdaterade bokningen med ID {}. Ny tid: {}, ny bana: {}, nytt datum: {}",
                username, id, bookingDetails.getTime(), courtId, bookingDetails.getDate());
        return updatedBooking;
    }

    @Override
    public void deleteBooking(int id) {
        PadelBooking booking = getBookingById(id);

        Court court = booking.getCourt();
        court.getAvailableTimes().put(booking.getTime(), true);

        bookingRepository.delete(booking);
        logger.info("Admin tog bort bokningen med ID {}", id);
    }

    @Override
    public boolean isTimeBooked(Court court, LocalTime time) {
        boolean booked = bookingRepository.findByCourtAndTime(court, time).isPresent();
        logger.info("Kontrollerar om tid {} för bana {} är bokad: {}", time, court.getId(), booked);
        return booked;
    }

    @Override
    public List<String> getAvailableTimes() {
        logger.info("Kund listar alla lediga tider");
        List<Court> allCourts = courtRepository.findAll();

        return allCourts.stream()
                .flatMap(court -> court.getAvailableTimes().entrySet().stream()
                        .filter(Map.Entry::getValue) // Endast lediga tider
                        .map(entry -> "Court: " + court.getName() + ", Time: " + entry.getKey().toString()))
                .collect(Collectors.toList());
    }
}
