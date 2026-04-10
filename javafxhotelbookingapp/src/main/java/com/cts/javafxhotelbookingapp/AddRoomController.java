package com.cts.javafxhotelbookingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Purpose: Controls the add room screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class AddRoomController {

    @FXML
    private TextField txtRoomNumber;

    @FXML
    private TextField txtRoomStatus;

    @FXML
    private Label lblMessage;

    /**
     * Saves a new room record to the database.
     */
    @FXML
    void saveRoom() throws SQLException {
        String roomNumber = txtRoomNumber.getText().trim();
        String roomStatus = txtRoomStatus.getText().trim();

        if (roomNumber.isEmpty() || roomStatus.isEmpty()) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Please fill in all fields.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter both room number and room status.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "INSERT INTO tblroom (roomnumber, roomstatus) VALUES (?, ?)";

            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, roomNumber);
            dc.ps.setString(2, roomStatus);
            dc.ps.executeUpdate();

            lblMessage.setTextFill(Color.BLUE);
            lblMessage.setText("Room added successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Room Added");
            alert.setHeaderText(null);
            alert.setContentText("Room added successfully.");
            alert.showAndWait();

            txtRoomNumber.clear();
            txtRoomStatus.setText("Available");

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Room not added.");
            throw new SQLException(e);
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Returns the user to the staff dashboard.
     */
    @FXML
    void goBack() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("staff-dashboard-view.fxml", 1100, 700);
    }

    /**
     * Logs the user out and returns to the login screen.
     */
    @FXML
    private void handleLogout() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("login-view.fxml", 1100, 700);
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
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
     * Opens the About PDF file.
     */
    @FXML
    private void handleAbout() {
        openPDF("/doc/hotel_about.pdf");
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