package com.ronvolt.airport.interfaces;

public interface Reservation {
    public void setBookingTypes();

    public void promptChoice();

    public void showAvailableSeats();

    public void assignRandomSeat();

    public void assignSelectedSeat();

    public void showPrice();

    public void bookingConfirmation();

    public void generateTicket();

    public void showTicket();
}