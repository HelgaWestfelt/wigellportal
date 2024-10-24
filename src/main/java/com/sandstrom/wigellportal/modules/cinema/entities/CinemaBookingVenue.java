package com.sandstrom.wigellportal.modules.cinema.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sandstrom.wigellportal.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "cinema_booking_venue")
public class CinemaBookingVenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;


    @Column(name="nr_of_guests")
    private int nrOfGuests;

    @ManyToOne
    @JoinColumn(name = "cinema_venue_id")
//    @JsonManagedReference
    private CinemaVenue cinemaVenue;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    @Column(name="entertainment")
    private String entertainment;

    @Column (name="date_and_time")
    private LocalDateTime timeForEvent;

    @Column(name = "total_price_in_SEK")
    private BigDecimal totalPriceSEK;

    @Column (name = "total_price_in_USD")
    private BigDecimal totalPriceUSD;


    public CinemaBookingVenue() {}

    public CinemaBookingVenue(int nrOfGuests, CinemaVenue venue, Customer customer, String entertainment,
                              LocalDateTime timeForEvent, BigDecimal totalPriceInSEK) {
        this.nrOfGuests = nrOfGuests;
        this.cinemaVenue = venue;
        this.customer = customer;
        this.entertainment = entertainment;
        this.timeForEvent = timeForEvent;
        this.totalPriceSEK = totalPriceInSEK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNrOfGuests() {
        return nrOfGuests;
    }

    public void setNrOfGuests(int nrOfGuests) {
        this.nrOfGuests = nrOfGuests;
    }

    public CinemaVenue getCinemaVenue() {
        return cinemaVenue;
    }

    public void setCinemaVenue(CinemaVenue venue) {
        this.cinemaVenue = venue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public LocalDateTime getTimeForEvent() {
        return timeForEvent;
    }

    public void setTimeForEvent(LocalDateTime timeForEvent) {
        this.timeForEvent = timeForEvent;
    }

    public BigDecimal getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(BigDecimal totalPriceInSEK) {
        this.totalPriceSEK = totalPriceInSEK;
    }

    public BigDecimal getTotalPriceUSD() {
        return totalPriceUSD;
    }

    public void setTotalPriceUSD(BigDecimal totalPriceUSD) {
        this.totalPriceUSD = totalPriceUSD;
    }

//    public BigDecimal getTotalPriceUSD(BigDecimal exchangeRate) {
//        return totalPriceSEK.multiply(exchangeRate);
//    }


    @Override
    public String toString() {
        return "BookingCinemaVenue{" +
                "id=" + id +
                ", nrOfGuests=" + nrOfGuests +
                ", venue=" + cinemaVenue +
                ", customer=" + customer +
                ", entertainment='" + entertainment + '\'' +
                ", timeForEvent=" + timeForEvent +
                ", totalPriceSEK=" + totalPriceSEK +
                ", totalPriceUSD=" + totalPriceUSD +
                '}';
    }
}
