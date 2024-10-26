package com.sandstrom.wigellportal.modules.travel.services.travelbooking;


import com.sandstrom.wigellportal.modules.travel.dto.TravelBookingDTO;
import com.sandstrom.wigellportal.modules.travel.dto.TripDTO;
import com.sandstrom.wigellportal.modules.travel.entities.TravelBooking;
import java.util.List;
import java.util.Optional;

public interface TravelBookingServiceInterface {
    Optional<TravelBooking> findById(int id);
    List<TravelBookingDTO> getBookingsByCustomerId(int id);
    TravelBooking save(TravelBooking travelBooking);
    TravelBookingDTO createTravelBookingDTO (TravelBooking travelBooking);
    TravelBooking update(int id, TravelBooking updatedTravelBooking);
    void delete(int id);
}