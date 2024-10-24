package com.sandstrom.wigellportal.modules.travel.dto;


import java.time.LocalDate;

public class TravelBookingDTO {
    private int bookingId; // Om du vill inkludera ID för TravelBooking
    private String bookingDate;
    private LocalDate travelDate;
    private LocalDate returnDate;
    private int numberOfWeeks;
    private double totalPriceSEK;
    private double totalPricePLN;
    private TripDTO trip; // Använd TripDTO
    private CustomerDTO customer;

    public TravelBookingDTO () {}

    public TravelBookingDTO(int bookingId, String bookingDate, LocalDate travelDate, LocalDate returnDate, int numberOfWeeks, double totalPriceSEK, double totalPricePLN, TripDTO trip, CustomerDTO customer) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
        this.returnDate = returnDate;
        this.numberOfWeeks = numberOfWeeks;
        this.totalPriceSEK = totalPriceSEK;
        this.totalPricePLN = totalPricePLN;
        this.trip = trip;
        this.customer = customer;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(int numberOfWeeks) {
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

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "\nResebokning: " + bookingId +
                "\n     Bokningsdatum:  " + bookingDate +
                "\n     Avresedatum:    " + travelDate +
                "\n     Returdatum:     " + returnDate +
                "\n     Antal veckor:   " + numberOfWeeks +
                "\n     Totalsumma:     " + totalPriceSEK + "SEK/" + totalPricePLN + "PLN" +
                "\n\n" + trip +
                "\n\n" + customer;
    }
}