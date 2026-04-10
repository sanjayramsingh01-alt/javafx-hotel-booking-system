package com.cts.javafxhotelbookingapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the view booking screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class ViewBookingController implements Initializable {

    @FXML
    private ComboBox<Booking> cmbBookings;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtGuestName;

    @FXML
    private TextField txtRoomNumber;

    @FXML
    private TextField txtBookingDate;

    @FXML
    private TextField txtCheckoutDate;

    @FXML
    private TextField txtNumberOfGuests;

    @FXML
    private TextField txtBookingStatus;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblMessage;

    private final ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    /**
     * Initializes the controller and loads bookings for the logged-in user.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBookings();
    }

    /**
     * Loads bookings belonging to the logged-in user into the combo box.
     */
    public void loadBookings() {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "SELECT * FROM tblbooking WHERE guestname = ?";
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, LoginController.loggedInUsername);
            dc.rst = dc.ps.executeQuery();

            bookingList.clear();

            while (dc.rst.next()) {
                bookingList.add(new Booking(
                        dc.rst.getInt("bookingid"),
                        dc.rst.getString("guestname"),
                        dc.rst.getString("roomnumber"),
                        dc.rst.getString("bookingdate"),
                        dc.rst.getString("checkoutdate"),
                        dc.rst.getInt("numberofguests"),
                        dc.rst.getString("bookingstatus")
                ));
            }

            cmbBookings.setItems(bookingList);
            lblMessage.setText("");

        } catch (SQLException e) {
            lblMessage.setText("Unable to load bookings.");
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Displays the details of the selected booking.
     */
    @FXML
    void showBookingDetails() {
        Booking selectedBooking = cmbBookings.getValue();

        if (selectedBooking != null) {
            txtBookingId.setText(String.valueOf(selectedBooking.getBookingid()));
            txtGuestName.setText(selectedBooking.getGuestname());
            txtRoomNumber.setText(selectedBooking.getRoomnumber());
            txtBookingDate.setText(selectedBooking.getBookingdate());
            txtCheckoutDate.setText(selectedBooking.getCheckoutdate());
            txtNumberOfGuests.setText(String.valueOf(selectedBooking.getNumberofguests()));
            txtBookingStatus.setText(selectedBooking.getBookingstatus());
        }
    }

    /**
     * Cancels the selected booking and refreshes the booking list.
     */
    @FXML
    void cancelBooking() {
        Booking selectedBooking = cmbBookings.getValue();

        if (selectedBooking == null) {
            lblMessage.setText("Please select a booking.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking before trying to cancel.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "UPDATE tblbooking SET bookingstatus = 'Cancelled' WHERE bookingid = ?";
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setInt(1, selectedBooking.getBookingid());
            dc.ps.executeUpdate();

            lblMessage.setText("Booking cancelled.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Cancelled");
            alert.setHeaderText(null);
            alert.setContentText("Booking cancelled successfully.");
            alert.showAndWait();

            loadBookings();
            cmbBookings.setValue(null);
            clearFields();

        } catch (SQLException e) {
            lblMessage.setText("Error cancelling booking.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cancellation Error");
            alert.setHeaderText(null);
            alert.setContentText("There was an error cancelling the booking.");
            alert.showAndWait();

        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Clears all booking detail fields.
     */
    public void clearFields() {
        txtBookingId.clear();
        txtGuestName.clear();
        txtRoomNumber.clear();
        txtBookingDate.clear();
        txtCheckoutDate.clear();
        txtNumberOfGuests.clear();
        txtBookingStatus.clear();
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