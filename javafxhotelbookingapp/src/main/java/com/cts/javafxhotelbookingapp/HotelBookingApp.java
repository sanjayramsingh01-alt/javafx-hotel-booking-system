package com.cts.javafxhotelbookingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Purpose: Main application class for the Hotel Booking System.
 * It starts the application and manages scene changes.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class HotelBookingApp extends Application {

    private static final Logger logger = Logger.getLogger(HotelBookingApp.class.getName());

    private static Stage currentStg;

    /**
     * Starts the JavaFX application and loads the login screen.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        currentStg = primaryStage;
        primaryStage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(HotelBookingApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Hotel Booking System: Login");
        primaryStage.setWidth(1100);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Changes the current scene to the selected FXML view.
     */
    public void changeScene(String fxml, Integer sWidth, Integer sHeight) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));

        switch (fxml) {
            case "login-view.fxml":
                currentStg.setTitle("Hotel Booking System: Login");
                break;

            case "register-user-view.fxml":
                currentStg.setTitle("Hotel Booking System: Register User");
                break;

            case "guest-dashboard-view.fxml":
                currentStg.setTitle("Hotel Booking System: Guest Dashboard");
                break;

            case "guest-profile-view.fxml":
                currentStg.setTitle("Hotel Booking System: Guest Profile");
                break;

            case "guest-view-rooms.fxml":
                currentStg.setTitle("Hotel Booking System: View Rooms");
                break;

            case "make-booking-view.fxml":
                currentStg.setTitle("Hotel Booking System: Make Booking");
                break;

            case "view-booking-view.fxml":
                currentStg.setTitle("Hotel Booking System: View Booking");
                break;

            case "staff-dashboard-view.fxml":
                currentStg.setTitle("Hotel Booking System: Staff Dashboard");
                break;

            case "staff-view-guests-view.fxml":
                currentStg.setTitle("Hotel Booking System: View Guests");
                break;

            case "staff-view-booking-view.fxml":
                currentStg.setTitle("Hotel Booking System: View Bookings");
                break;

            case "add-room-view.fxml":
                currentStg.setTitle("Hotel Booking System: Add Room");
                break;

            case "check-in-out-view.fxml":
                currentStg.setTitle("Hotel Booking System: Check In / Check Out");
                break;

            case "guest-report-view.fxml":
                currentStg.setTitle("Hotel Booking System: Guest Report");
                break;

            case "booking-report-view.fxml":
                currentStg.setTitle("Hotel Booking System: Booking Report");
                break;

            default:
                currentStg.setTitle("Hotel Booking System");
                break;
        }

        currentStg.setWidth(sWidth);
        currentStg.setHeight(sHeight);
        currentStg.getScene().setRoot(pane);
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}