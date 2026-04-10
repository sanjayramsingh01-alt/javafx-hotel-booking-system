Hotel Booking Management System

Project Information
Assignment: Assignment 2 – Hotel Online Customer Booking and Management System
Group No.: 31
Group Name: R & G Hotel Reservation Collective
Group Slogan: Seamless Reservations, Superior Stays.

Group Members
2544500 – Andrea Goorachan
2531767 – Sanjay Ramsingh

Additional Information
Lecturer: Mr. Chanan Sudama
Unit Code and Title: CIS016-1 Principle of Programming

Project Description
This project is a Hotel Booking Management System developed using Java, JavaFX, FXML, and MySQL.
It allows users to register, log in, view available rooms, make bookings, and manage reservations in an organized and efficient way.

Main Features

Guest Functions

Register account
Login
View available rooms
Make booking
View personal bookings
Prevent double booking of rooms

Staff Functions

View all bookings
View guest details
Add rooms
Manage room availability
Check-in / Check-out

Technologies Used

Java
JavaFX
FXML
MySQL
JDBC

Project Structure

Java Files
The Java source files can be found in:
src/main/java/com.cts.javafxhotelbookingapp

These files handle:

system logic
controllers
database connection
booking and room operations

FXML Files
The FXML files can be found in:
src/main/resources/com.cts.javafxhotelbookingapp

These files handle:

screen layouts
forms
dashboard designs
user interface

SQL Files
The SQL files can be found in:
src/main/resources/sql

Important SQL files:
hotel_booking_system.sql (main file)
Dump20260407.sql (backup file)

How to Recreate the Database

Option 1: Using main SQL file

Open MySQL Workbench
Create or select your database (javafxdemo)
Open the file: hotel_booking_system.sql
Run the SQL commands in MySQL
Refresh the schema

This file contains all the SQL commands needed to recreate the tables (tbluser, tblroom, tblbooking) and insert the required data.

Option 2: Using backup dump file

Open MySQL Workbench
Create or select your database (javafxdemo)
Open the file: Dump20260407.sql
Run the SQL commands in MySQL
Refresh the schema

This file is a backup dump that can also recreate the database structure and data if the main file does not work.

Database Tables
tbluser
tblroom
tblbooking

How to Run the Project

Open the project in IntelliJ IDEA
Make sure MySQL is running
Recreate the database using hotel_booking_system.sql
If needed, use Dump20260407.sql
Ensure MySQL Connector is added

The MySQL Connector (mysql-connector-j-9.6.0.jar) is located in:
lib folder of the project

Run HotelBookingApp.java


Conclusion
This project demonstrates a working hotel booking system that is user-friendly, organized, and suitable for real-world use.