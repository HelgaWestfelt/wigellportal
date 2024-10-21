package com.westfelt.wigellmcrental.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class CinemaAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "address")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<CinemaCustomer> residents;


    public CinemaAddress() {
    }

    public CinemaAddress(String street, String zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

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

    public List<CinemaCustomer> getResidents() {
        return residents;
    }

    public void setResidents(List<CinemaCustomer> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + ' ' +
                ", zipCode='" + zipCode + ' ' +
                ", city='" + city + ' ' +
                ", residents=" + residents +
                '}';
    }
}