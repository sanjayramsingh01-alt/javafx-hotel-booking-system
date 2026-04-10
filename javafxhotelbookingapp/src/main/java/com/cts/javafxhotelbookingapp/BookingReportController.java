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
 * Purpose: Controls the booking report screen of the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class BookingReportController implements Initializable {

    @FXML
    private TableView<Booking> tblBookings;

    @FXML
    private TableColumn<Booking, Integer> colBookingId;

    @FXML
    private TableColumn<Booking, String> colGuestName;

    @FXML
    private TableColumn<Booking, String> colRoomNumber;

    @FXML
    private TableColumn<Booking, String> colBookingDate;

    @FXML
    private TableColumn<Booking, String> colCheckoutDate;

    @FXML
    private TableColumn<Booking, Integer> colNumberOfGuests;

    @FXML
    private TableColumn<Booking, String> colBookingStatus;

    @FXML
    private Button btnPrintToPdf;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblMessage;

    private final ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    /**
     * Initializes the booking report table and loads booking data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingid"));
        colGuestName.setCellValueFactory(new PropertyValueFactory<>("guestname"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingdate"));
        colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutdate"));
        colNumberOfGuests.setCellValueFactory(new PropertyValueFactory<>("numberofguests"));
        colBookingStatus.setCellValueFactory(new PropertyValueFactory<>("bookingstatus"));

        loadBookings();
    }

    /**
     * Loads all bookings from the database into the table.
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

            tblBookings.setItems(bookingList);
            lblMessage.setText("");

        } catch (SQLException e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Unable to load bookings.");
        } finally {
            dc.closeConnection();
        }
    }

    /**
     * Exports the booking table to a PDF file.
     */
    public void exportTableViewToPDF(TableView<Booking> tableView, String filePath) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            document.setMargins(20, 20, 20, 20);

            Paragraph title = new Paragraph("Hotel Booking System Report: Booking Listing")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);

            document.add(title);

            Table pdfTable = new Table(7);
            pdfTable.setWidth(UnitValue.createPercentValue(100));

            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Booking ID")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Guest Name")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Room Number")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Booking Date")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Check-out Date")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("No. of Guests")));
            pdfTable.addHeaderCell(new Cell().add(new Paragraph("Status")));

            for (Booking booking : tableView.getItems()) {
                pdfTable.addCell(String.valueOf(booking.getBookingid()));
                pdfTable.addCell(booking.getGuestname());
                pdfTable.addCell(booking.getRoomnumber());
                pdfTable.addCell(booking.getBookingdate());
                pdfTable.addCell(booking.getCheckoutdate());
                pdfTable.addCell(String.valueOf(booking.getNumberofguests()));
                pdfTable.addCell(booking.getBookingstatus());
            }

            document.add(pdfTable);
            document.close();

            lblMessage.setTextFill(Color.GREEN);
            lblMessage.setText("Booking report exported successfully.");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Booking report exported successfully.");
            alert.showAndWait();

        } catch (Exception e) {
            lblMessage.setTextFill(Color.RED);
            lblMessage.setText("Failed to export booking report.");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("Failed to export booking report.");
            alert.showAndWait();
        }
    }

    /**
     * Starts the PDF export process.
     */
    @FXML
    public void printToPdf() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/booking_report.pdf";
        exportTableViewToPDF(tblBookings, desktopPath);
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