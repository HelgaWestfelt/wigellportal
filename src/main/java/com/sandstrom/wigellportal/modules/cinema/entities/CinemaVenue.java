package com.sandstrom.wigellportal.modules.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cinema_venue")
public class CinemaVenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_no_of_guests", nullable = false)
    private int maxNoOfGuests;


    @Column(name = "facilities")
    private String facilities;

    @OneToMany(mappedBy = "cinemaVenue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CinemaBookingVenue> bookings;

    public CinemaVenue() {}

    public CinemaVenue(String name, int maxNoOfGuests, String facilities) {
        this.name = name;
        this.maxNoOfGuests = maxNoOfGuests;
        this.facilities = facilities;
    }

    public int getId() {
        return id;
    }

    public void setId( int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNoOfGuests() {
        return maxNoOfGuests;
    }

    public void setMaxNoOfGuests(int maxNoOfGuests) {
        this.maxNoOfGuests = maxNoOfGuests;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public List<CinemaBookingVenue> getBookings() {
        return bookings;
    }

    public void setBookings(List<CinemaBookingVenue> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxNoOfGuests=" + maxNoOfGuests +
                ", facilities=" + facilities +
                '}';
    }
}
