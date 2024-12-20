package com.sandstrom.wigellportal.modules.motorcyclerental.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "motorcycle")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;
    @Column(name = "price_per_day_in_GBP")
    private BigDecimal pricePerDayInGBP;
    @Column(name = "availability")
    private boolean availability;
    @ManyToMany(mappedBy = "motorcycles")
    @JsonIgnore
    private List<McBooking> mcBookings;

    public Motorcycle() {
    }

    public Motorcycle(String brand, String model, String registrationNumber,
                      BigDecimal pricePerDay, BigDecimal pricePerDayInGBP, boolean availability) {
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.pricePerDay = pricePerDay;
        this.pricePerDayInGBP = pricePerDayInGBP;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getPricePerDayInGBP() {
        return pricePerDayInGBP;
    }

    public void setPricePerDayInGBP(BigDecimal pricePerDayInGBP) {
        this.pricePerDayInGBP = pricePerDayInGBP;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<McBooking> getMcBookings() {
        return mcBookings;
    }

    public void setMcBookings(List<McBooking> mcBookings) {
        this.mcBookings = mcBookings;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", pricePerDayInGBP=" + pricePerDayInGBP +
                ", availability=" + availability +
                '}';
    }
}
