package com.sandstrom.wigellportal.modules.motorcyclerental.services;

import com.sandstrom.wigellportal.modules.motorcyclerental.dao.McBookingRepository;
import com.sandstrom.wigellportal.modules.motorcyclerental.dao.McRepository;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Motorcycle;
import com.sandstrom.wigellportal.modules.motorcyclerental.services.CurrencyResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class McServiceImpl implements McService{

    private static final Logger logger = LoggerFactory.getLogger(McServiceImpl.class);

    private McRepository mcRepository;
    private CurrencyService currencyService;
    private McBookingRepository mcBookingRepository;

    @Autowired
    public McServiceImpl(McRepository mcRep, CurrencyService currServ, McBookingRepository mcBookRep){
        mcRepository = mcRep;
        currencyService = currServ;
        mcBookingRepository = mcBookRep;
    }

    @Override
    public List<Motorcycle> findAll() {
        updateMotorcycleAvailability();
        logger.info("Listed all motorcycles");
        return mcRepository.findAll();
    }

    public List<Motorcycle> findAvailableBikes() {
        updateMotorcycleAvailability();
        List<Motorcycle> motorcycles = mcRepository.findByAvailabilityTrue();
        logger.info("Listed avaliable motorcycles: {}", motorcycles);

        return motorcycles;
    }

    @Override
    public Motorcycle findById(int id) {
        updateMotorcycleAvailability();
        Optional<Motorcycle> mc = mcRepository.findById(id);
        Motorcycle motorcycle = null;
        if (mc.isPresent()){
            motorcycle = mc.get();
        } else {
            throw new RuntimeException("Motorcycle with id: " + id + " could not be found");
        }
        logger.info("Found motorcycle: " + motorcycle);
        return motorcycle;
    }

    @Transactional
    @Override
    public Motorcycle save(Motorcycle motorcycle) {
        logger.info("Admin added a motorcycle: {}", motorcycle);

        updateMotorcycleAvailability();

        BigDecimal pricePerDayInGBP = calculatePriceInGBP(motorcycle.getPricePerDay());
        motorcycle.setPricePerDayInGBP(pricePerDayInGBP);

        return mcRepository.save(motorcycle);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        updateMotorcycleAvailability();
        logger.info("Admin deleted motorcycle with id: {}", id);
        mcRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Motorcycle updateMc(int id, Motorcycle updatedMotorcycle){
        updateMotorcycleAvailability();

       return mcRepository.findById(id).map(motorcycle -> {
            motorcycle.setBrand(updatedMotorcycle.getBrand());
            motorcycle.setModel(updatedMotorcycle.getModel());
            motorcycle.setRegistrationNumber(updatedMotorcycle.getRegistrationNumber());
            motorcycle.setPricePerDay(updatedMotorcycle.getPricePerDay());
            motorcycle.setAvailability(updatedMotorcycle.isAvailability());

            logger.info("Admin updated motorcycle: {}", updatedMotorcycle);

           BigDecimal pricePerDayInGBP = calculatePriceInGBP(motorcycle.getPricePerDay());
           motorcycle.setPricePerDayInGBP(pricePerDayInGBP);

           updateMotorcycleAvailability();

           return mcRepository.save(motorcycle);
        }).orElseThrow(() -> new RuntimeException("Motorcycle with id: " + id + " could not be found"));
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
