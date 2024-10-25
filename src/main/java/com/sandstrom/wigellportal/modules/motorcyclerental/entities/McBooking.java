package com.westfelt.wigellmcrental.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "booking")
public class McBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "price_in_GBP")
    private BigDecimal priceInGBP;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking_mc",
    joinColumns = @JoinColumn(name = "booking_id"),
    inverseJoinColumns = @JoinColumn(name = "motorcycle_id"))
    private List<Motorcycle> motorcycles;

    public McBooking(){

    }

    public McBooking(LocalDate startDate, LocalDate endDate, BigDecimal price, BigDecimal priceInGBP,
                     Customer customer, List<Motorcycle> motorcycles) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceInGBP = priceInGBP;
        this.customer = customer;
        this.motorcycles = motorcycles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceInGBP() {
        return priceInGBP;
    }

    public void setPriceInGBP(BigDecimal priceInGBP) {
        this.priceInGBP = priceInGBP;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }

    @Override
    public String toString() {
        return "McBooking{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceInGBP=" + priceInGBP +
                ", customer=" + customer +
                ", motorcycles=" + motorcycles +
                '}';
    }

}
