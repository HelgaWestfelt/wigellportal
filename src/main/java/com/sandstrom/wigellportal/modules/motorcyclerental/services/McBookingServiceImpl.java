package com.sandstrom.wigellportal.modules.motorcyclerental.services;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerRepository;
import com.sandstrom.wigellportal.modules.motorcyclerental.dao.McBookingRepository;
import com.sandstrom.wigellportal.modules.motorcyclerental.dao.McRepository;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Motorcycle;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class McBookingServiceImpl implements McBookingService {

    private static final Logger logger = LoggerFactory.getLogger(McBookingServiceImpl.class);

    private McBookingRepository mcBookingRepository;
    private CustomerRepository customerRepository;
    private McRepository mcRepository;
    private CurrencyService currencyService;

    @Autowired
    public McBookingServiceImpl(McBookingRepository mcBookRep, CustomerRepository custRep, McRepository mcRep, CurrencyServiceImpl currServ) {
        mcBookingRepository = mcBookRep;
        customerRepository = custRep;
        mcRepository = mcRep;
        currencyService = currServ;
    }

    @Override
    public List<McBooking> findAll() {
        logger.info("Listed all mc bookings: {}", mcBookingRepository.findAll());
        updateMotorcycleAvailability();
        return mcBookingRepository.findAll();
    }

    @Override
    public McBooking findById(int id) {
        updateMotorcycleAvailability();

        Optional<McBooking> mcBook = mcBookingRepository.findById(id);
        McBooking mcBooking = null;
        if(mcBook.isPresent()){
            mcBooking = mcBook.get();
        } else {
            throw new RuntimeException("Booking with id " + id + " could not be found");
        }

        logger.info("Found mcBooking: {}", mcBooking);
        return mcBooking;
    }

    @Transactional
    @Override
    public McBooking save(McBooking booking) {
        logger.info("Customer made a new booking: {}", booking);

        Customer customer = booking.getCustomer();


        if (customer != null) {
            if (customer.getId() == 0) {
                customer = customerRepository.save(customer);
            } else {
                customer = customerRepository.findById(customer.getId())
                        .orElseThrow(() -> new RuntimeException("Customer could not be found"));
            }
            booking.setCustomer(customer);
        } else {
            throw new RuntimeException("Booking need a Customer");
        }

        List<Motorcycle> motorcycles = new ArrayList<>();
        for (Motorcycle mc : booking.getMotorcycles()) {
            Motorcycle managedMc = mcRepository.findById(mc.getId())
                    .orElseThrow(() -> new RuntimeException("Motorcycle with id: " + mc.getId() + " could not be found"));

            managedMc.setAvailability(false);
            mcRepository.save(managedMc);

            motorcycles.add(managedMc);
        }
        booking.setMotorcycles(motorcycles);

        BigDecimal priceInGBP = calculatePriceInGBP(booking.getPrice());
        booking.setPriceInGBP(priceInGBP);

        McBooking savedBooking = mcBookingRepository.save(booking);

        return savedBooking;

    }


    @Transactional
    @Override
    public String deleteById(int id) {
        mcBookingRepository.deleteById(id);
        logger.info("Admin deleted booking with id: {}", id);
        updateMotorcycleAvailability();
        return "Booking with id " + id + " is deleted";
    }

    @Transactional
    @Override
    public McBooking updateMcBooking(int id, McBooking updatedMcBooking){

        updateMotorcycleAvailability();

        return mcBookingRepository.findById(id).map(mcBooking -> {
            mcBooking.setStartDate(updatedMcBooking.getStartDate());
            mcBooking.setEndDate(updatedMcBooking.getEndDate());
            mcBooking.setPrice(updatedMcBooking.getPrice());
            mcBooking.setPriceInGBP(updatedMcBooking.getPriceInGBP());

            if (updatedMcBooking.getCustomer() != null && updatedMcBooking.getCustomer().getId() != 0) {
                Customer customer = customerRepository.findById(updatedMcBooking.getCustomer().getId())
                        .orElseThrow(() -> new RuntimeException("Customer with id: " + updatedMcBooking.getCustomer().getId() + " could not be found"));
                mcBooking.setCustomer(customer);
            }

            if (updatedMcBooking.getMotorcycles() != null) {
                List<Motorcycle> motorcycles = new ArrayList<>();
                for (Motorcycle mc : updatedMcBooking.getMotorcycles()) {
                    Motorcycle motorcycle = mcRepository.findById(mc.getId())
                            .orElseThrow(() -> new RuntimeException("Motorcycle with id: " + mc.getId() + " could not be found"));
                    motorcycles.add(motorcycle);
                }
                mcBooking.setMotorcycles(motorcycles);
            }
            logger.info("Customer updated booking: {}", mcBooking);

            BigDecimal priceInGBP = calculatePriceInGBP(mcBooking.getPrice());
            mcBooking.setPriceInGBP(priceInGBP);

            updateMotorcycleAvailability();

            return mcBookingRepository.save(mcBooking);
        }).orElseThrow(() -> new RuntimeException("Booking with id: " + id + " could not be found"));

    }

    @Transactional
    @Override
    public Map<String, List<McBooking>> findAllBookings(int customerId) {
        LocalDate currentDate = LocalDate.now();

        List<McBooking> activeBookings = mcBookingRepository.findByCustomerIdAndEndDateAfter(customerId, currentDate);
        List<McBooking> pastBookings = mcBookingRepository.findByCustomerIdAndEndDateBefore(customerId, currentDate);

        Map<String, List<McBooking>> mcBookings = new HashMap<>();
        mcBookings.put("activeBookings", activeBookings);
        mcBookings.put("pastBookings", pastBookings);

        logger.info("Customer listed all bookings: {}", mcBookings);

        return mcBookings;
    }

    private BigDecimal calculatePriceInGBP(BigDecimal priceInSEK) {
        CurrencyResponse exchangeRate = currencyService.getCurrencyToGBP();

        if (exchangeRate != null && exchangeRate.getRates() != null) {
            BigDecimal gbpRate = exchangeRate.getRate("GBP");
            BigDecimal sekRate = exchangeRate.getRate("SEK");
            if (gbpRate != null && sekRate != null) {
                BigDecimal priceInGBP = priceInSEK.divide(sekRate, 4, BigDecimal.ROUND_HALF_UP).multiply(gbpRate);
                priceInGBP = priceInGBP.setScale(2, BigDecimal.ROUND_HALF_UP);
                logger.info("Price in SEK " + priceInSEK + " was converted to GBP " + priceInGBP + ".");
                return priceInGBP;
            }
        }

        return BigDecimal.ZERO;
    }

    private void updateMotorcycleAvailability() {
        LocalDate currentDate = LocalDate.now();

        List<McBooking> pastBookings = mcBookingRepository.findByEndDateBefore(currentDate);

        for (McBooking booking : pastBookings) {
            for (Motorcycle mc : booking.getMotorcycles()) {

                if (!mc.isAvailability()) {

                    boolean isStillBooked = mcBookingRepository.existsByMotorcyclesIdAndEndDateAfter(mc.getId(), currentDate);

                    if (!isStillBooked) {

                        mc.setAvailability(true);
                        mcRepository.save(mc);
                    }
                }
            }
        }
    }


}


