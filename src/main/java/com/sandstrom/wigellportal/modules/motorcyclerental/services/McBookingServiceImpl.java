package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.currency.CurrencyResponse;
import com.westfelt.wigellmcrental.dao.CustomerRepository;
import com.westfelt.wigellmcrental.dao.McBookingRepository;
import com.westfelt.wigellmcrental.dao.McRepository;
import com.westfelt.wigellmcrental.entities.Customer;
import com.westfelt.wigellmcrental.entities.McBooking;
import com.westfelt.wigellmcrental.entities.Motorcycle;
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

        logger.info("Found mcBooking: {}", mcBooking);
        return mcBooking;
    }

    @Transactional
    @Override
    public McBooking save(McBooking booking) {
        logger.info("Customer made a new booking: {}", booking);

        Customer customer = booking.getCustomer();

        // Hantera kunden (samma som tidigare)
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

        // Hämta och sätt motorcyklarna
        List<Motorcycle> motorcycles = new ArrayList<>();
        for (Motorcycle mc : booking.getMotorcycles()) {
            Motorcycle managedMc = mcRepository.findById(mc.getId())
                    .orElseThrow(() -> new RuntimeException("Motorcycle with id: " + mc.getId() + " could not be found"));
            motorcycles.add(managedMc);

        }
        booking.setMotorcycles(motorcycles);

        McBooking savedBooking = mcBookingRepository.save(booking);

        BigDecimal priceInGBP = calculatePriceInGBP(savedBooking.getPrice());
        savedBooking.setPriceInGBP(priceInGBP);

        return savedBooking;

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
            mcBooking.setPriceInGBP(updatedMcBooking.getPriceInGBP());

            if (updatedMcBooking.getCustomer() != null && updatedMcBooking.getCustomer().getId() != 0) {
                Customer customer = customerRepository.findById(updatedMcBooking.getCustomer().getId())
                        .orElseThrow(() -> new RuntimeException("Customer with id: " + updatedMcBooking.getCustomer().getId() + " could not be found"));
                mcBooking.setCustomer(customer);
            }

            // Hantera motorcyklarna
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

            return mcBookingRepository.save(mcBooking);
        }).orElseThrow(() -> new RuntimeException("Booking with id: " + id + " could not be found"));

    }

    @Transactional
    @Override
    public Map<String, List<McBooking>> findAllBookings(int customerId) {
        LocalDate currentDate = LocalDate.now();

        List<McBooking> activeBookings = mcBookingRepository.findByCustomerIdAndEndDateAfter(customerId, currentDate);
        List<McBooking> pastBookings = mcBookingRepository.findByCustomerIdAndEndDateBefore(customerId, currentDate);

        // Lägger in båda listorna i en Map för att strukturera responsen
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

        return BigDecimal.ZERO; // Returnera 0 om växelkursen inte kan hämtas
    }
}


