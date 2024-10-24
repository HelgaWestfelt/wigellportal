package com.sandstrom.wigellportal.modules.travel.entities;

import com.sandstrom.wigellportal.customer.Customer;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Component
public class TravelBooking {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;
    private LocalDate travelDate;
    private LocalDate returnDate;
    @Column(name = "number_of_weeks")
    private Integer numberOfWeeks;

    @Column(name = "total_price_sek")
    private double totalPriceSEK;

    @Column(name = "total_price_pln")
    private double totalPricePLN;

    @Column(name = "booking_date")
    private String bookingDate;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public TravelBooking() {

    }

    public TravelBooking(LocalDate travelDate, Trip trip, Integer numberOfWeeks, double totalPriceSEK, Customer customer, String bookingDate) {
        this.travelDate = travelDate;
        this.trip = trip;
        this.numberOfWeeks = numberOfWeeks;
        this.totalPriceSEK = totalPriceSEK;
        this.customer = customer;
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getTravelDate() {
        return travelDate;
    }
    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public Integer getNumberOfWeeks() {
        return numberOfWeeks;
    }
    public void setNumberOfWeeks(Integer numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }
    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }
    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }
    public double getTotalPricePLN() {
        return totalPricePLN;
    }
    public void setTotalPricePLN(double totalPricePLN) {
        this.totalPricePLN = totalPricePLN;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
