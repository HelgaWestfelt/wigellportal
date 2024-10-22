package com.sandstrom.wigellportal.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sandstrom.wigellportal.mcrental.entities.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinema_customer")
public class CinemaCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name="enabled", nullable= false)
    private boolean enabled= true;

    @PrePersist
    protected void onCreate() {
        this.enabled = true;  // se till att det är true vid ny sparning om det inte redan är satt
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 30)
    private Role role;

    @OneToMany
    @Column(name = "cinema_booking_ticket")
    private List<CinemaBookingTicket> cinemaTicketBookings;

    @OneToMany(mappedBy = "cinemaCustomer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CinemaBookingVenue> cinemaVenueBookings = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinColumn(name = "cinema_address_id")
    private CinemaAddress cinemaAddress;

    public CinemaCustomer() {
    }

    public CinemaCustomer(boolean enabled, String firstName, String lastName, String phoneNumber,
                          String dateOfBirth, String email, String username,
                          String password, Role role, CinemaAddress address) {
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cinemaAddress = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public CinemaAddress getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(CinemaAddress address) {
        this.cinemaAddress = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                "enabled =" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", address=" + cinemaAddress +
                '}';
    }
}