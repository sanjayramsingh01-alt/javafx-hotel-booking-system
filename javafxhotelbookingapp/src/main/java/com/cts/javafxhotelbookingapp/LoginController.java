package com.cts.javafxhotelbookingapp;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the login screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    private PasswordField tBoxPassword;

    @FXML
    private TextField tBoxUsername;

    @FXML
    private ComboBox<String> cBoxRole;

    @FXML
    private Label lblWrongLogin;

    @FXML
    private Hyperlink lblRegister;

    public static String loggedInUsername;
    public static String loggedInPassword;
    public static String loggedInRole;

    /**
     * Initializes the login screen controls.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cBoxRole.setItems(FXCollections.observableArrayList("Guest", "Staff"));
        cBoxRole.setStyle("-fx-font: 20px \"Arial\";");
    }

    /**
     * Handles the login button action.
     */
    @FXML
    void userLogin(ActionEvent event) throws IOException, SQLException {
        checkLogin();
    }

    /**
     * Validates the user's login credentials and opens the correct dashboard.
     */
    @FXML
    private void checkLogin() throws IOException, SQLException {
        HotelBookingApp d = new HotelBookingApp();

        String s1 = tBoxUsername.getText().trim();
        String s2 = tBoxPassword.getText().trim();
        String s3 = cBoxRole.getValue();

        if (s1.isEmpty() || s2.isEmpty() || s3 == null) {
            lblWrongLogin.setTextFill(Color.BLUE);
            lblWrongLogin.setText("Please enter valid credentials.");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter username, password, and select a role.");
            alert.showAndWait();
            return;
        }

        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "SELECT username, password, userrole, useractive "
                    + "FROM tblUser "
                    + "WHERE username = ? AND password = ? AND userrole = ? AND useractive = 1";

            dc.ps = dc.con.prepareStatement(query);
            dc.ps.setString(1, s1);
            dc.ps.setString(2, s2);
            dc.ps.setString(3, s3);

            dc.rst = dc.ps.executeQuery();

            if (dc.rst.next()) {
                loggedInUsername = s1;
                loggedInPassword = s2;
                loggedInRole = s3;

                lblWrongLogin.setTextFill(Color.GREEN);
                lblWrongLogin.setText("Credentials Authenticated");

                if (s3.equals("Guest")) {
                    d.changeScene("guest-dashboard-view.fxml", 1100, 700);
                } else if (s3.equals("Staff")) {
                    d.changeScene("staff-dashboard-view.fxml", 1100, 700);
                }
            } else {
                lblWrongLogin.setTextFill(Color.RED);
                lblWrongLogin.setText("Wrong username or password!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username, password, or role.");
                alert.showAndWait();
            }
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Clears all login input fields.
     */
    @FXML
    private void reset() throws IOException {
        try {
            tBoxUsername.clear();
            tBoxPassword.clear();
            cBoxRole.setValue(null);
            lblWrongLogin.setText("");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    /**
     * Opens the registration screen.
     */
    @FXML
    void registerUser(ActionEvent event) throws IOException {
        try {
            HotelBookingApp d = new HotelBookingApp();
            d.changeScene("register-user-view.fxml", 1100, 750);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}