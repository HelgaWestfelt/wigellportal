package com.sandstrom.wigellportal.modules.cinema.entities;


import com.sandstrom.wigellportal.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cinema_booking_ticket")
public class CinemaBookingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    CinemaScreening screening;

    @Column(name= "nr_of_tickets")
    private int nrOfTickets;

    @Column (name= "total_price_SEK")
    private BigDecimal totalPriceSEK;


    public CinemaBookingTicket(){

    }
    public CinemaBookingTicket(int id, Customer customer, CinemaScreening screening, int nrOfTickets, BigDecimal totalPriceSEK) {
        this.id = id;
        this.customer = customer;
        this.screening = screening;
        this.nrOfTickets = nrOfTickets;
        this.totalPriceSEK = totalPriceSEK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CinemaScreening getScreening() {
        return screening;
    }

    public void setScreening(CinemaScreening screening) {
        this.screening = screening;
    }

    public int getNrOfTickets() {
        return nrOfTickets;
    }

    public void setNrOfTickets(int nrOfTickets) {
        this.nrOfTickets = nrOfTickets;
    }

    public BigDecimal getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(BigDecimal totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }


    @Override
    public String toString() {
        return "BookingCinemaTicket{" +
                "id=" + id +
                ", customer=" + customer +
                ", screening=" + screening +
                ", nrOfTickets=" + nrOfTickets +
                ", totalPriceSEK=" + totalPriceSEK +
                '}';
    }
}
