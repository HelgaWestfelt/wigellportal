package com.sandstrom.wigellportal.modules.travel.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Trip {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Integer id;

    private String hotel;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @OneToMany(mappedBy = "trip")
    @JsonIgnore
    private List<TravelBooking> bookings;

    private Double weeklyPrice;

    private boolean active;
    public Trip () {

    }

    public Trip(String hotel, Destination destination, Double weeklyPrice) {
        this.hotel = hotel;
        this.destination = destination;
        this.weeklyPrice = weeklyPrice;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Double getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(Double weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public List<TravelBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<TravelBooking> bookings) {
        this.bookings = bookings;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}