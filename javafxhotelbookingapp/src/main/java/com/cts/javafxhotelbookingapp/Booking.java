package com.cts.javafxhotelbookingapp;

/**
 * Purpose: Stores booking details used in the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class Booking {

    private int bookingid;
    private String guestname;
    private String roomnumber;
    private String bookingdate;
    private String checkoutdate;
    private int numberofguests;
    private String bookingstatus;

    /**
     * Creates a booking object with booking details.
     */
    public Booking(int bookingid, String guestname, String roomnumber, String bookingdate, String checkoutdate, int numberofguests, String bookingstatus) {
        this.bookingid = bookingid;
        this.guestname = guestname;
        this.roomnumber = roomnumber;
        this.bookingdate = bookingdate;
        this.checkoutdate = checkoutdate;
        this.numberofguests = numberofguests;
        this.bookingstatus = bookingstatus;
    }

    /**
     * Returns the booking ID.
     */
    public int getBookingid() {
        return bookingid;
    }

    /**
     * Returns the guest name.
     */
    public String getGuestname() {
        return guestname;
    }

    /**
     * Returns the room number.
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * Returns the booking date.
     */
    public String getBookingdate() {
        return bookingdate;
    }

    /**
     * Returns the check-out date.
     */
    public String getCheckoutdate() {
        return checkoutdate;
    }

    /**
     * Returns the number of guests.
     */
    public int getNumberofguests() {
        return numberofguests;
    }

    /**
     * Returns the booking status.
     */
    public String getBookingstatus() {
        return bookingstatus;
    }

    /**
     * Returns booking information for display in combo boxes.
     */
    @Override
    public String toString() {
        return bookingid + " - " + guestname + " - Room " + roomnumber;
    }
}