package com.cts.javafxhotelbookingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Purpose: Controls the guest dashboard screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class GuestDashboardController {

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnViewRooms;

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnViewBooking;

    @FXML
    private Button btnLogout;

    /**
     * Opens the guest profile screen.
     */
    @FXML
    void openProfile() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("guest-profile-view.fxml", 1100, 700);
    }

    /**
     * Opens the view rooms screen.
     */
    @FXML
    void openViewRooms() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("guest-view-rooms.fxml", 1100, 700);
    }

    /**
     * Opens the make booking screen.
     */
    @FXML
    void openMakeBooking() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("make-booking-view.fxml", 1100, 700);
    }

    /**
     * Opens the view booking screen.
     */
    @FXML
    void openViewBooking() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("view-booking-view.fxml", 1100, 700);
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
        openPDF("/doc/hotel_faq.pdf");
    }

    /**
     * Opens the User Manual PDF file.
     */
    @FXML
    private void handleUserManual() {
        openPDF("/doc/hotel_user_manual.pdf");
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