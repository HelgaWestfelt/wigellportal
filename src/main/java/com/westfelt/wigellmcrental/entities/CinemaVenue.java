package com.westfelt.wigellmcrental.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "venue")
public class CinemaVenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_no_of_guests", nullable = false)
    private int maxNoOfGuests;

    @ElementCollection
    @Column(name = "facilities")
    private List<String> facilities;


    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonBackReference
    private List<CinemaBookingVenue> bookings;

    public CinemaVenue() {}

    public CinemaVenue(String name, int maxNoOfGuests, List<String> facilities) {
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

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
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
