package com.cts.javafxhotelbookingapp;

/**
 * Purpose: Stores guest details used in the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class Guest {

    private int guestid;
    private String guestfirstname;
    private String guestlastname;
    private String guestemail;
    private String guestmobilephone;

    /**
     * Creates a guest object with guest details.
     */
    public Guest(int guestid, String guestfirstname, String guestlastname, String guestemail, String guestmobilephone) {
        this.guestid = guestid;
        this.guestfirstname = guestfirstname;
        this.guestlastname = guestlastname;
        this.guestemail = guestemail;
        this.guestmobilephone = guestmobilephone;
    }

    /**
     * Returns the guest ID.
     */
    public int getGuestid() {
        return guestid;
    }

    /**
     * Returns the guest first name.
     */
    public String getGuestfirstname() {
        return guestfirstname;
    }

    /**
     * Returns the guest last name.
     */
    public String getGuestlastname() {
        return guestlastname;
    }

    /**
     * Returns the guest email address.
     */
    public String getGuestemail() {
        return guestemail;
    }

    /**
     * Returns the guest mobile phone number.
     */
    public String getGuestmobilephone() {
        return guestmobilephone;
    }
}