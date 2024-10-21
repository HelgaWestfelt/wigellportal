package com.westfelt.wigellmcrental.services;



import com.westfelt.wigellmcrental.dao.CinemaBookingVRepository;
import com.westfelt.wigellmcrental.entities.CinemaBookingVenue;
import com.westfelt.wigellmcrental.models.CinemaExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class CinemaBookingVServiceImpl implements CinemaBookingVService {

    private static final Logger logger = LoggerFactory.getLogger(CinemaBookingVServiceImpl.class);

    @Autowired
    private CinemaBookingVRepository bookingCVenueRepository;

    @Autowired
    private CinemaExchangeRateService exchangeRateService; // Injektera ExchangeRateService

    @Override
    public List<CinemaBookingVenue> findAllBookingsByCustomerId(int customerId) {
        logger.info("All venue bookings made by customer with id " + customerId + " was listed.");
        return bookingCVenueRepository.findByCustomerId(customerId);
    }

    @Override
    public CinemaBookingVenue findById(int id){
        Optional<CinemaBookingVenue> b = bookingCVenueRepository.findById(id);
        CinemaBookingVenue bookingCVenue = null;
        if(b.isPresent()){
            bookingCVenue = b.get();
        }
        else{
            throw new RuntimeException("Booking with id  " + id + " could not be found.");
        }
        logger.info("Cinema venue booking with id " + id + " was listed.");
        return bookingCVenue;
    }

    @Override
    public CinemaBookingVenue save(CinemaBookingVenue bookingCVenue) {
        BigDecimal priceInUSD = calculatePriceInUSD(bookingCVenue.getTotalPriceSEK());
        bookingCVenue.setTotalPriceUSD(priceInUSD);

        logger.info("New cinema venue booking was made.");
        return bookingCVenueRepository.save(bookingCVenue);
    }

    @Override
    public void deleteById(int id) {
        bookingCVenueRepository.deleteById(id);
    }

    private BigDecimal calculatePriceInUSD(BigDecimal priceInSEK) {
        CinemaExchangeRateResponse exchangeRate = exchangeRateService.getExchangeRateToUSD();

        if (exchangeRate != null && exchangeRate.getRates() != null) {
            BigDecimal usdRate = exchangeRate.getRate("USD");
            BigDecimal sekRate = exchangeRate.getRate("SEK");
            if (usdRate != null && sekRate != null) {
                BigDecimal priceInUSD = priceInSEK.divide(sekRate, 4, BigDecimal.ROUND_HALF_UP).multiply(usdRate);
                priceInUSD = priceInUSD.setScale(2, BigDecimal.ROUND_HALF_UP);
                logger.info("Price in SEK " + priceInSEK + " was converted to USD " + priceInUSD + ".");
                return priceInUSD;
            }
        }

        return BigDecimal.ZERO; // Returnera 0 om växelkursen inte kan hämtas
    }
}
