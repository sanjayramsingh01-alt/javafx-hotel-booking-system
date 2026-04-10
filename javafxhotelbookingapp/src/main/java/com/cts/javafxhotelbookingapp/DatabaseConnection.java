package com.cts.javafxhotelbookingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Purpose: Creates and manages the database connection for the Hotel Booking System.
 * Authors: Andrea Goorachan, Sanjay Ramsingh
 * Version: v1.0
 * Date: 2/24/2026
 */
public class DatabaseConnection {

    protected Connection con = null;
    protected Statement stat = null;
    protected PreparedStatement ps = null;
    protected ResultSet rst = null;

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    /**
     * Creates a new database connection object.
     */
    public DatabaseConnection() {
        createConnection();
    }

    /**
     * Establishes a connection to the MySQL database.
     */
    public void createConnection() {
        try {
            String JDBC_URL = "jdbc:mysql://localhost:3306/javafxhotelbookingapp";
            con = DriverManager.getConnection(JDBC_URL, "root", "mysql");
            stat = con.createStatement();
        } catch (SQLException e) {
            logger.severe("An error occurred: Database connectivity failed");
            logger.severe(e.toString());
        }
    }

    /**
     * Closes all database resources after use.
     */
    public void closeConnection() {
        try {
            if (rst != null) {
                rst.close();
            }
        } catch (SQLException e) {
            logger.severe(e.toString());
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            logger.severe(e.toString());
        }

        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            logger.severe(e.toString());
        }

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            logger.severe(e.toString());
        }
    }
}