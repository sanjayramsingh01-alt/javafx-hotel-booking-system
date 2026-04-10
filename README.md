# Hotel Booking Management System

## 🔗 Project Repository
This GitHub repository contains the full source code for the Hotel Booking Management System.

---

## 📌 Project Information
- Assignment: Assignment 2 – Hotel Online Customer Booking and Management System  
- Group No.: 31  
- Group Name: R & G Hotel Reservation Collective  
- Group Slogan: Seamless Reservations, Superior Stays  

---

## 👥 Group Members
- 2544500 – Andrea Goorachan  
- 2531767 – Sanjay Ramsingh  

---

## ℹ️ Additional Information
- Lecturer: Mr. Chanan Sudama  
- Unit Code: CIS016-1 Principle of Programming  

---

## 🧾 Project Description
This project is a Hotel Booking Management System developed using Java, JavaFX, FXML, and MySQL.  
It allows users to register, log in, view available rooms, make bookings, and manage reservations in an organized and efficient way.

---

## 🚀 Main Features

### Guest Functions
- Register account  
- Login  
- View available rooms  
- Make booking  
- View personal bookings  
- Prevent double booking of rooms  

### Staff Functions
- View all bookings  
- View guest details  
- Add rooms  
- Manage room availability  
- Check-in / Check-out  

---

## 🛠️ Technologies Used
- Java  
- JavaFX  
- FXML  
- MySQL  
- JDBC  

---

## 📁 Project Structure

### Java Files
Located in: src/main/java/com.cts.javafxhotelbookingapp  

Handles system logic, controllers, database connection, and booking operations.

### FXML Files
Located in: src/main/resources/com.cts.javafxhotelbookingapp  

Handles screen layouts, forms, dashboards, and user interface.

### SQL Files
Located in: src/main/resources/sql  

Important files:
- hotel_booking_system.sql (main file)  
- Dump20260407.sql (backup file)  

---

## 🗄️ How to Recreate the Database

### Option 1: Using Main SQL File
1. Open MySQL Workbench  
2. Create or select database (javafxdemo)  
3. Open hotel_booking_system.sql  
4. Run the SQL script  
5. Refresh the schema  

This file recreates the tables: tbluser, tblroom, tblbooking.

### Option 2: Using Backup Dump File
1. Open MySQL Workbench  
2. Create or select database (javafxdemo)  
3. Open Dump20260407.sql  
4. Run the SQL script  
5. Refresh the schema  

---

## 🗃️ Database Tables
- tbluser  
- tblroom  
- tblbooking  

---

## ▶️ How to Run the Project
1. Open the project in IntelliJ IDEA  
2. Ensure MySQL is running  
3. Recreate the database using hotel_booking_system.sql  
4. If needed, use Dump20260407.sql  
5. Ensure MySQL Connector is added  

MySQL Connector location: lib/mysql-connector-j-9.6.0.jar  

6. Run: HotelBookingApp.java  

---

## ✅ Conclusion
This project demonstrates a working hotel booking system that is user-friendly, organized, and suitable for real-world use.
