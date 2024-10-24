package com.sandstrom.wigellportal.modules.padel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "court")
public class Court {  // Behåller namnet som Court

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "price_per_hour")
    private int pricePerHour;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PadelBooking> bookings;  // Refererar till PadelBooking

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "court_times", joinColumns = @JoinColumn(name = "court_id"))
    @MapKeyColumn(name = "time")
    @Column(name = "is_available")
    @JsonIgnore
    private Map<LocalTime, Boolean> availableTimes = new HashMap<>();

    // Default constructor
    public Court() {
        initializeAvailableTimes();  // Initiera tillgängliga tider vid skapandet av objektet
    }

    public Court(String name, String location, int pricePerHour, String description) {
        this.name = name;
        this.location = location;
        this.pricePerHour = pricePerHour;
        this.description = description;
        initializeAvailableTimes();
    }

    private void initializeAvailableTimes() {
        // Lägg till fördefinierade tider mellan 08:00 till 20:00
        for (int hour = 8; hour < 20; hour++) {
            availableTimes.put(LocalTime.of(hour, 0), true); // Alla tider är initialt tillgängliga
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PadelBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<PadelBooking> bookings) {
        this.bookings = bookings;
    }

    public Map<LocalTime, Boolean> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(Map<LocalTime, Boolean> availableTimes) {
        this.availableTimes = availableTimes;
    }

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", description='" + description + '\'' +
                ", availableTimes=" + availableTimes +
                '}';
    }
}
