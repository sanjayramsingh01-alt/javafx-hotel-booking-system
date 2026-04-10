package com.cts.javafxhotelbookingapp;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Purpose: Controls the guest report screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class GuestReportController implements Initializable {

    @FXML
    private TableView<Guest> tblGuests;

    @FXML
    private TableColumn<Guest, Integer> colGuestId;

    @FXML
    private TableColumn<Guest, String> colFirstName;

    @FXML
    private TableColumn<Guest, String> colLastName;

    @FXML
    private TableColumn<Guest, String> colEmail;

    @FXML
    private TableColumn<Guest, String> colPhone;

    @FXML
    private Button btnPrintToPdf;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblMessage;

    private final ObservableList<Guest> guestList = FXCollections.observableArrayList();

    /**
     * Initializes the guest report table and loads guest data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestid"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("guestfirstname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("guestlastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("guestemail"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("guestmobilephone"));

        loadGuests();
    }

    /**
     * Loads guest records from the database into the table.
     */
    public void loadGuests() {
        DatabaseConnection dc = new DatabaseConnection();

        try {
            guestList.clear();

            String query = "SELECT userid, firstname, lastname, useremail, userphone FROM tbluser WHERE userrole = 'Guest'";
            dc.rst = dc.stat.executeQuery(query);

            while (dc.rst.next()) {
                guestList.add(new Guest(
                        dc.rst.getInt("userid"),
                        dc.rst.getString("firstname"),
                        dc.rst.getString("lastname"),
                        dc.rst.getString("useremail"),
                        dc.rst.getString("userphone")
                ));
            }

            tblGuests.setItems(guestList);
            lblMessage.setText("");

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Unable to load guests.");
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Exports the guest table data to a PDF file.
     */
    public void exportTableViewToPDF(TableView<Guest> tableView, String filePath) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            document.setMargins(20, 20, 20, 20);

            Paragraph title = new Paragraph("Hotel Booking System Report: Guest Listing")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);

            document.add(title);

            Table pdfTable = new Table(5);
            pdfTable.setWidth(UnitValue.createPercentValue(100));

            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Guest ID")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("First Name")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Last Name")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Email")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Phone")));

            for (Guest guest : tableView.getItems()) {
                pdfTable.addCell(String.valueOf(guest.getGuestid()));
                pdfTable.addCell(guest.getGuestfirstname());
                pdfTable.addCell(guest.getGuestlastname());
                pdfTable.addCell(guest.getGuestemail());
                pdfTable.addCell(guest.getGuestmobilephone());
            }

            document.add(pdfTable);
            document.close();

            lblMessage.setTextFill(Color.GREEN);
            lblMessage.setText("Guest report exported successfully.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Guest report exported successfully.");
            alert.showAndWait();

        } catch (Exception e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Failed to export guest report.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("Failed to export guest report.");
            alert.showAndWait();
        }
    }

    /**
     * Starts the PDF export process.
     */
    @FXML
    public void printToPdf() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/guest_report.pdf";
        exportTableViewToPDF(tblGuests, desktopPath);
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