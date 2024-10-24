package com.sandstrom.wigellportal.modules.travel.dto;


import com.sandstrom.wigellportal.modules.travel.entities.Destination;

public class TripDTO {

    private Integer id;
    private Destination destination;
    private String hotel;
    private Double weeklyPriceSEK;
    private Double weeklyPricePLN;

    public TripDTO (){

    }

    public TripDTO(Integer id, Destination destination, String hotel, Double weeklyPriceSEK, Double weeklyPricePLN) {
        this.id = id;
        this.destination = destination;
        this.hotel = hotel;
        this.weeklyPriceSEK = weeklyPriceSEK;
        this.weeklyPricePLN = weeklyPricePLN;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Double getWeeklyPriceSEK() {
        return weeklyPriceSEK;
    }

    public void setWeeklyPriceSEK(Double weeklyPriceSEK) {
        this.weeklyPriceSEK = weeklyPriceSEK;
    }

    public Double getWeeklyPricePLN() {
        return weeklyPricePLN;
    }

    public void setWeeklyPricePLN(Double weeklyPricePLN) {
        this.weeklyPricePLN = weeklyPricePLN;
    }

    @Override
    public String toString() {
        return "Destination:" +
                "\n     Reseid:         " + id +
                destination +
                "\n     Hotell:         " + hotel +
                "\n     Pris per vecka: " + weeklyPriceSEK + "SEK" + "/" + weeklyPricePLN + "PLN";
    }
}
