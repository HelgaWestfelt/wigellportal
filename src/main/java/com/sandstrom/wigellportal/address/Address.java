package com.sandstrom.wigellportal.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sandstrom.wigellportal.customer.Customer;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "address", cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JsonIgnore
    private List<Customer> residents;

    public Address() {
    }

    public Address(String street, String zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Customer> getResidents() {
        return residents;
    }

    public void setResidents(List<Customer> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "\nAdress" +
                "\n     Adressid:       " + id +
                "\n     Gatuadress:     " + street +
                "\n     Postnummer:     " + zipCode +
                "\n     Ort:            " + city;
    }
}
