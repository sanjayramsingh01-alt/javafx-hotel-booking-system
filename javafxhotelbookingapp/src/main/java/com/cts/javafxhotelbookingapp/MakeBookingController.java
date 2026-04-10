package com.cts.javafxhotelbookingapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the make booking screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class MakeBookingController implements Initializable {

    @FXML
    private TextField txtGuestName;

    @FXML
    private ComboBox<String> cmbRoomNumber;

    @FXML
    private TextField txtBookingDate;

    @FXML
    private TextField txtCheckoutDate;

    @FXML
    private TextField txtGuests;

    @FXML
    private Label lblMessage;

    /**
     * Initializes the booking screen and loads available rooms.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtGuestName.setText(LoginController.loggedInUsername);
        loadRooms();
    }

    /**
     * Loads all available room numbers into the room selection combo box.
     */
    public void loadRooms() {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "SELECT roomnumber FROM tblroom WHERE roomstatus = 'Available'";
            dc.rst = dc.stat.executeQuery(query);

            cmbRoomNumber.getItems().clear();

            while (dc.rst.next()) {
                cmbRoomNumber.getItems().add(dc.rst.getString("roomnumber"));
            }

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Unable to load room numbers.");
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Saves a new booking and updates the selected room status.
     */
    @FXML
    void saveBooking() throws SQLException {
        String guestName = txtGuestName.getText().trim();
        String roomNumber = cmbRoomNumber.getValue();
        String bookingDate = txtBookingDate.getText().trim();
        String checkoutDate = txtCheckoutDate.getText().trim();
        String numberOfGuests = txtGuests.getText().trim();

        if (guestName.isEmpty() || roomNumber == null || bookingDate.isEmpty()
                || checkoutDate.isEmpty() || numberOfGuests.isEmpty()) {

            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Please fill in all fields.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please complete all booking fields.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();
        ResultSet rs = null;

        try {
            String checkQuery = "SELECT roomstatus FROM tblroom WHERE roomnumber = ?";
            dc.ps = dc.con.prepareStatement(checkQuery);
            dc.ps.setString(1, roomNumber);
            rs = dc.ps.executeQuery();

            if (rs.next()) {
                String roomStatus = rs.getString("roomstatus");

                if (!roomStatus.equalsIgnoreCase("Available")) {
                    lblMessage.setTextFill(Color.RED);
                    lblMessage.setText("Room is no longer available.");

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Room Unavailable");
                    alert.setHeaderText(null);
                    alert.setContentText("The selected room is no longer available.");
                    alert.showAndWait();
                    return;
                }
            }

            if (rs != null) {
                rs.close();
            }
            if (dc.ps != null) {
                dc.ps.close();
            }

            String bookingQuery = "INSERT INTO tblbooking (guestname, roomnumber, bookingdate, checkoutdate, numberofguests, bookingstatus) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            dc.ps = dc.con.prepareStatement(bookingQuery);
            dc.ps.setString(1, guestName);
            dc.ps.setString(2, roomNumber);
            dc.ps.setString(3, bookingDate);
            dc.ps.setString(4, checkoutDate);
            dc.ps.setInt(5, Integer.parseInt(numberOfGuests));
            dc.ps.setString(6, "Pending");
            dc.ps.executeUpdate();

            dc.ps.close();

            String updateRoomQuery = "UPDATE tblroom SET roomstatus = 'Pending' WHERE roomnumber = ?";
            dc.ps = dc.con.prepareStatement(updateRoomQuery);
            dc.ps.setString(1, roomNumber);
            dc.ps.executeUpdate();

            lblMessage.setTextFill(Color.BLUE);
            lblMessage.setText("Booking saved successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Saved");
            alert.setHeaderText(null);
            alert.setContentText("Booking saved successfully.");
            alert.showAndWait();

            txtGuestName.setText(LoginController.loggedInUsername);
            cmbRoomNumber.setValue(null);
            txtBookingDate.clear();
            txtCheckoutDate.clear();
            txtGuests.clear();

            loadRooms();

        } catch (NumberFormatException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Number of guests must be a valid number.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number of guests.");
            alert.showAndWait();

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Booking not saved.");
            throw new SQLException(e);

        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dc.closeConnection();
        }
    }

    /**
     * Returns the user to the guest dashboard.
     */
    @FXML
    void goBack() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("guest-dashboard-view.fxml", 1100, 700);
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