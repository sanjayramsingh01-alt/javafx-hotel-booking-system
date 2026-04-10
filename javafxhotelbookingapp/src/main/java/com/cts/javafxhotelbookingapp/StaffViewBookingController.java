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
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the staff view booking screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class StaffViewBookingController implements Initializable {

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
    private Button btnAccept;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblMessage;

    private final ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    /**
     * Initializes the controller and loads all bookings.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBookings();
    }

    /**
     * Loads all bookings from the database into the combo box.
     */
    public void loadBookings() {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            bookingList.clear();

            String query = "SELECT * FROM tblbooking";
            dc.rst = dc.stat.executeQuery(query);

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
            lblMessage.setTextFill(Color.RED);
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
     * Accepts the selected booking.
     */
    @FXML
    void acceptBooking() throws SQLException {
        Booking selectedBooking = cmbBookings.getValue();

        if (selectedBooking == null) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Please select a booking.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking before accepting it.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "UPDATE tblbooking SET bookingstatus = ? WHERE bookingid = ?";
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, "Accepted");
            dc.ps.setInt(2, selectedBooking.getBookingid());
            dc.ps.executeUpdate();

            lblMessage.setTextFill(Color.BLUE);
            lblMessage.setText("Booking accepted successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Accepted");
            alert.setHeaderText(null);
            alert.setContentText("Booking accepted successfully.");
            alert.showAndWait();

            loadBookings();
            cmbBookings.setValue(null);
            clearFields();

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Booking not updated.");
            throw new SQLException(e);
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Cancels the selected booking.
     */
    @FXML
    void cancelBooking() throws SQLException {
        Booking selectedBooking = cmbBookings.getValue();

        if (selectedBooking == null) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Please select a booking.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking before cancelling it.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "UPDATE tblbooking SET bookingstatus = ? WHERE bookingid = ?";
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, "Cancelled");
            dc.ps.setInt(2, selectedBooking.getBookingid());
            dc.ps.executeUpdate();

            lblMessage.setTextFill(Color.BLUE);
            lblMessage.setText("Booking cancelled successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Cancelled");
            alert.setHeaderText(null);
            alert.setContentText("Booking cancelled successfully.");
            alert.showAndWait();

            loadBookings();
            cmbBookings.setValue(null);
            clearFields();

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Booking not updated.");
            throw new SQLException(e);
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
        openPDF("/doc/hotel_faq.pdf");
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