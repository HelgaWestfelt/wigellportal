package com.westfelt.wigellmcrental.services;

import com.westfelt.wigellmcrental.currency.CurrencyResponse;
import com.westfelt.wigellmcrental.dao.McRepository;
import com.westfelt.wigellmcrental.entities.Motorcycle;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class McServiceImpl implements McService{

    private static final Logger logger = LoggerFactory.getLogger(McServiceImpl.class);

    private McRepository mcRepository;
    private CurrencyService currencyService;

    @Autowired
    public McServiceImpl(McRepository mcRep, CurrencyService currServ){
        mcRepository = mcRep;
        currencyService = currServ;
    }

    @Override
    public List<Motorcycle> findAll() {
        return mcRepository.findAll();
    }

    public List<Motorcycle> findAvailableBikes() {
        List<Motorcycle> motorcycles = mcRepository.findByAvailabilityTrue();

        return motorcycles;
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

        BigDecimal pricePerDayInGBP = calculatePriceInGBP(motorcycle.getPricePerDay());
        motorcycle.setPricePerDayInGBP(pricePerDayInGBP);

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

           BigDecimal pricePerDayInGBP = calculatePriceInGBP(motorcycle.getPricePerDay());
           motorcycle.setPricePerDayInGBP(pricePerDayInGBP);

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

        return BigDecimal.ZERO; // Returnera 0 om växelkursen inte kan hämtas
    }

}
