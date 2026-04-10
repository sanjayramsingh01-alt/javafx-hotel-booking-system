package com.cts.javafxhotelbookingapp;

/**
 * Purpose: Stores room details used in the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class Room {

    private int roomid;
    private String roomnumber;
    private String roomstatus;

    /**
     * Creates a room object with room details.
     */
    public Room(int roomid, String roomnumber, String roomstatus) {
        this.roomid = roomid;
        this.roomnumber = roomnumber;
        this.roomstatus = roomstatus;
    }

    /**
     * Returns the room ID.
     */
    public int getRoomid() {
        return roomid;
    }

    /**
     * Returns the room number.
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * Returns the room status.
     */
    public String getRoomstatus() {
        return roomstatus;
    }
}