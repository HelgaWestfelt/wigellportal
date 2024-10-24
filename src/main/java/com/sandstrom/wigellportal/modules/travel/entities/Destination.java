package com.sandstrom.wigellportal.modules.travel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer id;
    private String city;
    private String country;
    @OneToMany(mappedBy = "destination")
    @JsonIgnore
    private List<Trip> trips;

    public Destination () {

    }

    public Destination(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Destination(String city, String country, List<Trip> trips) {
        this.city = city;
        this.country = country;
        this.trips = trips;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return  "\n     Stad:           " + city +
                "\n     Land:           " + country;
    }
}
