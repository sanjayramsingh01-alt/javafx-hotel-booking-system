package com.cts.javafxhotelbookingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Purpose: Controls the staff dashboard screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class StaffDashboardController {

    @FXML
    private Button btnAddRoom;

    @FXML
    private Button btnViewBookings;

    @FXML
    private Button btnViewGuests;

    @FXML
    private Button btnCheckInOut;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnGuestReport;

    @FXML
    private Button btnBookingReport;

    /**
     * Opens the add room screen.
     */
    @FXML
    void openAddRoom() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("add-room-view.fxml", 1100, 700);
    }

    /**
     * Opens the staff view bookings screen.
     */
    @FXML
    void openViewBookings() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("staff-view-booking-view.fxml", 1100, 700);
    }

    /**
     * Opens the staff view guests screen.
     */
    @FXML
    void openViewGuests() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("staff-view-guests-view.fxml", 1100, 700);
    }

    /**
     * Opens the check in/out screen.
     */
    @FXML
    void openCheckInOut() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("check-in-out-view.fxml", 1100, 700);
    }

    /**
     * Opens the guest report screen.
     */
    @FXML
    void openGuestReport() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("guest-report-view.fxml", 1100, 700);
    }

    /**
     * Opens the booking report screen.
     */
    @FXML
    void openBookingReport() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("booking-report-view.fxml", 1100, 700);
    }

    /**
     * Logs the user out and returns to the login screen.
     */
    @FXML
    void logout() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("login-view.fxml", 1100, 700);
    }

    /**
     * Handles logout from the File menu.
     */
    @FXML
    private void handleLogout() throws IOException {
        logout();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Opens the About PDF file.
     */
    @FXML
    private void handleAbout() {
        openPDF("/doc/hotel_about.pdf");
    }

    /**
     * Opens the FAQ PDF file.
     */
    @FXML
    private void handleFAQ() {
        openPDF("/doc/staff_faq.pdf");
    }

    /**
     * Opens the User Manual PDF file.
     */
    @FXML
    private void handleUserManual() {
        openPDF("/doc/staff_user_manual.pdf");
    }

    /**
     * Opens a PDF file from the project resources.
     */
    private void openPDF(String path) {
        try {
            URL url = getClass().getResource(path);

            if (url != null) {
                File file = new File(url.toURI());
                java.awt.Desktop.getDesktop().open(file);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File Not Found");
                alert.setHeaderText(null);
                alert.setContentText("PDF not found: " + path);
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Opening PDF");
            alert.setHeaderText(null);
            alert.setContentText("Unable to open the PDF file.");
            alert.showAndWait();
        }
    }
}