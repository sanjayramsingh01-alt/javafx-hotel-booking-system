package com.cts.javafxhotelbookingapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the guest profile screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class GuestProfileController implements Initializable {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private Button btnBack;

    /**
     * Loads the logged-in guest's profile information from the database.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "SELECT * FROM tbluser WHERE username = ?";
            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, LoginController.loggedInUsername);
            dc.rst = dc.ps.executeQuery();

            if (dc.rst.next()) {
                txtFirstName.setText(dc.rst.getString("firstname"));
                txtLastName.setText(dc.rst.getString("lastname"));
                txtUsername.setText(dc.rst.getString("username"));
                txtPassword.setText(dc.rst.getString("password"));
                txtEmail.setText(dc.rst.getString("useremail"));
                txtPhone.setText(dc.rst.getString("userphone"));
            }

        } catch (SQLException e) {
            txtUsername.setText(LoginController.loggedInUsername);
            txtPassword.setText(LoginController.loggedInPassword);
        } finally {
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