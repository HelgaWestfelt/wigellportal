package com.sandstrom.wigellportal.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sandstrom.wigellportal.address.Address;
import com.sandstrom.wigellportal.modules.cinema.entities.CinemaBookingTicket;
import com.sandstrom.wigellportal.modules.cinema.entities.CinemaBookingVenue;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.McBooking;
import com.sandstrom.wigellportal.modules.motorcyclerental.entities.Role;
import com.sandstrom.wigellportal.modules.padel.entities.PadelBooking;
import com.sandstrom.wigellportal.modules.travel.entities.TravelBooking;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @Column(name = "date_of_birth", length = 20)
    private String dateOfBirth;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "username", length = 45)
    private String username;
    @Column(name = "password", length = 68)
    private String password;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<McBooking> mcBookings;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<CinemaBookingTicket> cinemaBookingTickets;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<CinemaBookingVenue> cinemaBookingVenues;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<TravelBooking> travelBookings;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PadelBooking> bookings;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phoneNumber,
                    String dateOfBirth, String email, String username,
                    String password, Role role, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.address = address;
        this.enabled = true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = true;
    }

    public void setCinemaBookingTickets(List<CinemaBookingTicket> cinemaBookingTickets) {
        this.cinemaBookingTickets = cinemaBookingTickets;
    }

    public void setCinemaBookingVenues(List<CinemaBookingVenue> cinemaBookingVenues) {
        this.cinemaBookingVenues = cinemaBookingVenues;
    }

    public List<CinemaBookingTicket> getCinemaBookingTickets() {
        return cinemaBookingTickets;
    }

    public List<CinemaBookingVenue> getCinemaBookingVenues() {
        return cinemaBookingVenues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<McBooking> getMcBookings() {
        return mcBookings;
    }

    public void setMcBookings(List<McBooking> bookings) {
        this.mcBookings = bookings;
    }

    @Override
    public String toString() {
        return "Kund:   " + id +
                "\n     Förnamn:        " + firstName +
                "\n     Efternamn:      " + lastName +
                "\n     E-postadess:    " + email +
                "\n     Telefonnummer:  " + phoneNumber +
                "\n     Personnummer:   " + dateOfBirth +
                "\n" + address;
        //eventuellt lägga till username och password här (men det kommer ju även med login när man visar alla kunder)
    }
}
