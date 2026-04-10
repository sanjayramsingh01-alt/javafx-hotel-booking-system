package com.cts.javafxhotelbookingapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the guest view rooms screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class GuestViewRoomsController implements Initializable {

    @FXML
    private TableView<Room> tblRooms;

    @FXML
    private TableColumn<Room, Integer> colRoomId;

    @FXML
    private TableColumn<Room, String> colRoomNumber;

    @FXML
    private TableColumn<Room, String> colRoomStatus;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblMessage;

    private final ObservableList<Room> roomList = FXCollections.observableArrayList();

    /**
     * Initializes the room table and loads available room data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomid"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomstatus"));

        loadRooms();
    }

    /**
     * Loads available rooms from the database into the table.
     */
    public void loadRooms() {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            String query = "SELECT * FROM tblroom WHERE roomstatus = 'Available'";
            dc.rst = dc.stat.executeQuery(query);

            roomList.clear();

            while (dc.rst.next()) {
                roomList.add(new Room(
                        dc.rst.getInt("roomid"),
                        dc.rst.getString("roomnumber"),
                        dc.rst.getString("roomstatus")
                ));
            }

            tblRooms.setItems(roomList);
            lblMessage.setText("");

        } catch (SQLException e) {
            lblMessage.setText("Unable to load rooms.");
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