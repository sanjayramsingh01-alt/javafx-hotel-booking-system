CREATE DATABASE IF NOT EXISTS javafxhotelbookingapp;
USE javafxhotelbookingapp;

-- =========================
-- TABLE: tbluser
-- =========================
CREATE TABLE IF NOT EXISTS tbluser (
    userid INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    userrole VARCHAR(20) NOT NULL,
    useremail VARCHAR(100),
    userphone VARCHAR(20),
    useractive TINYINT(1) NOT NULL DEFAULT 1,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    PRIMARY KEY (userid)
);

INSERT INTO tbluser (userid, username, password, userrole, useremail, userphone, useractive, firstname, lastname)
VALUES
(6, 'admin', '1234', 'Staff', NULL, NULL, 1, NULL, NULL),
(14, 'sanjay', 'pass', 'Guest', 'sanjay2gmail.com', '770-4590', 1, 'sanjay', 'ramsingh'),
(15, 'jake', 'pass', 'Guest', 'jake@gmail.com', '343-8976', 1, 'jake', 'Singh'),
(16, 'shriya', 'pass', 'Guest', 'shriya@gmail.com', '454-876', 1, 'Shriya', 'Ramsingh');
(17, 'maria', 'pass', 'Guest', 'maria@gmail.com', '355-7812', 1, 'Maria', 'Ali')

-- =========================
-- TABLE: tblroom
-- =========================
CREATE TABLE IF NOT EXISTS tblroom (
    roomid INT NOT NULL AUTO_INCREMENT,
    roomnumber VARCHAR(20) NOT NULL,
    roomstatus VARCHAR(20) NOT NULL,
    PRIMARY KEY (roomid)
);

-- Add your actual room records here
-- Example:
INSERT INTO tblroom (roomid, roomnumber, roomstatus)
VALUES
(1, '101', 'Available'),
(2, '102', 'Available'),
(3, '103', 'Pending'),
(4, '200', 'Checked Out'),
(5, '201', 'Cancelled'),
(6, '301', 'Checked In'),
(7, '305', 'Pending');

-- =========================
-- TABLE: tblbooking
-- =========================
CREATE TABLE IF NOT EXISTS tblbooking (
    bookingid INT NOT NULL AUTO_INCREMENT,
    guestname VARCHAR(100) NOT NULL,
    roomnumber VARCHAR(20) NOT NULL,
    bookingdate VARCHAR(20) NOT NULL,
    bookingstatus VARCHAR(20) NOT NULL,
    checkoutdate VARCHAR(20) NOT NULL,
    numberofguests INT NOT NULL,
    PRIMARY KEY (bookingid)
);

INSERT INTO tblbooking (bookingid, guestname, roomnumber, bookingdate, bookingstatus, checkoutdate, numberofguests)
VALUES
(12, 'sanjay', '103', '2026-10-17', 'Cancelled', '2026-10-17', 4),
(13, 'sanjay', '200', '2026-11-13', 'Checked Out', '2026-11-13', 4),
(14, 'sanjay', '102', '2026-11-18', 'Pending', '2026-11-19', 4),
(15, 'sanjay', '201', '2026-11-19', 'Cancelled', '2026-11-21', 4),
(16, 'sanjay', '305', '2026-11-27', 'Pending', '2026-11-29', 2),
(17, 'shriya', '301', '2026-11-19', 'Checked In', '2026-11-22', 4);

-- =========================
-- VIEW DATA
-- =========================
SELECT * FROM tbluser;
SELECT * FROM tblroom;
SELECT * FROM tblbooking;