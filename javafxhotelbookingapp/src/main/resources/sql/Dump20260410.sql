CREATE DATABASE  IF NOT EXISTS `javafxhotelbookingapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `javafxhotelbookingapp`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javafxhotelbookingapp
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblbooking`
--

DROP TABLE IF EXISTS `tblbooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbooking` (
  `bookingid` int NOT NULL AUTO_INCREMENT,
  `guestname` varchar(45) NOT NULL,
  `roomnumber` varchar(45) NOT NULL,
  `bookingdate` varchar(45) NOT NULL,
  `bookingstatus` varchar(45) NOT NULL,
  `checkoutdate` date DEFAULT NULL,
  `numberofguests` int DEFAULT NULL,
  PRIMARY KEY (`bookingid`),
  UNIQUE KEY `bookingkey_UNIQUE` (`bookingid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbooking`
--

LOCK TABLES `tblbooking` WRITE;
/*!40000 ALTER TABLE `tblbooking` DISABLE KEYS */;
INSERT INTO `tblbooking` VALUES (12,'sanjay','103','2026-10-17','Cancelled','2026-10-17',4),(13,'sanjay','200','2026-11-13','Checked Out','2026-11-13',4),(14,'sanjay','102','2026-11-18','Pending','2026-11-19',4),(15,'sanjay','201','2026-11-19','Cancelled','2026-11-21',4),(16,'sanjay','305','2026-11-27','Pending','2026-11-29',2),(17,'shriya','301','2026-11-19','Checked In','2026-11-22',4),(18,'sona','215','2026-11-14','Checked Out','2026-11-17',2),(19,'sona','220','2026-11-14','Pending','2026-11-17',2),(20,'sona','290','2026-11-18','Cancelled','2026-11-18',2),(21,'sona','120','2026-11-14','Checked Out','2026-11-19',2),(22,'sona','125','2026-11-20','Cancelled','2026-11-22',3);
/*!40000 ALTER TABLE `tblbooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblroom`
--

DROP TABLE IF EXISTS `tblroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblroom` (
  `roomid` int NOT NULL AUTO_INCREMENT,
  `roomnumber` varchar(45) NOT NULL,
  `roomstatus` varchar(45) NOT NULL,
  PRIMARY KEY (`roomid`),
  UNIQUE KEY `roomkey_UNIQUE` (`roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblroom`
--

LOCK TABLES `tblroom` WRITE;
/*!40000 ALTER TABLE `tblroom` DISABLE KEYS */;
INSERT INTO `tblroom` VALUES (1,'101','Pending'),(2,'102','Pending'),(3,'103','Pending'),(4,'201','Pending'),(5,'202','Occupied'),(6,'200','Pending'),(7,'121','Pending'),(8,'301','Pending'),(9,'305','Pending'),(10,'200','Available'),(11,'215','Pending'),(12,'220','Pending'),(13,'290','Pending'),(14,'120','Pending'),(15,'125','Pending'),(16,'123','Available');
/*!40000 ALTER TABLE `tblroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbluser` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `userrole` varchar(45) NOT NULL,
  `useremail` varchar(45) DEFAULT NULL,
  `userphone` varchar(45) DEFAULT NULL,
  `useractive` tinyint NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userkey_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES (6,'admin','1234','Staff',NULL,NULL,1,NULL,NULL),(14,'sanjay','pass','Guest','sanjay2gmail.com','770-4590',1,'sanjay','ramsingh'),(15,'jake','pass','Guest','jake@gmail.com','343-8976',1,'jake','Singh'),(16,'shriya','pass','Guest','shriya@gmail.com','454-876',1,'Shriya','Ramsingh'),(17,'maria','pass','Guest','maria@gmail.com','355-7812',1,'Maria','Ali'),(20,'sona','pass','Guest','sona@gmail.com','334-5678',1,'sona','pass');
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-10 10:48:34
