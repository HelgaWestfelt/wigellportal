package com.sandstrom.wigellportal.padel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "padel_address")
public class PadelAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PadelCustomer> residents;

    // Default constructor
    public PadelAddress() {}

    public PadelAddress(String street, String zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<PadelCustomer> getResidents() {
        return residents;
    }

    public void setResidents(List<PadelCustomer> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "PadelAddress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
