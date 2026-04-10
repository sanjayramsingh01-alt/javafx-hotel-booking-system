package com.cts.javafxhotelbookingapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the user registration screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class RegisterUserController implements Initializable {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private ComboBox<String> cBoxRole;

    @FXML
    private Label lblMessage;

    /**
     * Initializes the registration screen controls.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cBoxRole.setItems(FXCollections.observableArrayList("Guest", "Staff"));
    }

    /**
     * Registers a new user in the database.
     */
    @FXML
    void registerUser() throws SQLException {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String role = cBoxRole.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty()
                || password.isEmpty() || email.isEmpty() || phone.isEmpty() || role == null) {

            lblMessage.setText("Please fill in all fields.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields before registering.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "INSERT INTO tblUser (firstname, lastname, username, password, useremail, userphone, userrole, useractive) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";

            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, firstName);
            dc.ps.setString(2, lastName);
            dc.ps.setString(3, username);
            dc.ps.setString(4, password);
            dc.ps.setString(5, email);
            dc.ps.setString(6, phone);
            dc.ps.setString(7, role);

            dc.ps.executeUpdate();

            lblMessage.setText("User registered successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User registered successfully.");
            alert.showAndWait();

        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Returns the user to the login screen.
     */
    @FXML
    void goBack() throws IOException {
        HotelBookingApp d = new HotelBookingApp();
        d.changeScene("login-view.fxml", 1100, 700);
    }
}