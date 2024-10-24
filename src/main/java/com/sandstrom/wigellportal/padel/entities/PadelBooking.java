package com.sandstrom.wigellportal.padel.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "booking")
public class PadelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "total_price_eur")
    private double totalPriceEur;

    @Column(name = "currency")
    private String currency;

    @Column(name = "players_count")
    private int playersCount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private PadelCustomer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "court_id")
    private Court court;

    // Getters, Setters, Constructors, toString()

    // Default constructor
    public PadelBooking() {
    }

    public PadelBooking(LocalDate date, LocalTime time, int totalPrice, double totalPriceEur, String currency, int playersCount, PadelCustomer customer, Court court) {
        this.date = date;
        this.time = time;
        this.totalPrice = totalPrice;
        this.totalPriceEur = totalPriceEur;
        this.currency = currency;
        this.playersCount = playersCount;
        this.customer = customer;
        this.court = court;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceEur() {
        return totalPriceEur;
    }

    public void setTotalPriceEur(double totalPriceEur) {
        this.totalPriceEur = totalPriceEur;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public PadelCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(PadelCustomer customer) {
        this.customer = customer;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    @Override
    public String toString() {
        return "PadelBooking{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", totalPrice=" + totalPrice +
                ", totalPriceEur=" + totalPriceEur +
                ", currency='" + currency + '\'' +
                ", playersCount=" + playersCount +
                ", customer=" + customer +
                ", court=" + court +
                '}';
    }
}
