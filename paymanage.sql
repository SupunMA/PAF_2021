-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 08:40 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `paymanage`
--

CREATE TABLE `paymanage` (
  `paymentID` int(10) NOT NULL,
  `date` varchar(10) NOT NULL,
  `paymentMethod` varchar(10) NOT NULL,
  `paymentAmt` double NOT NULL,
  `invoiceID` int(10) DEFAULT NULL,
  `cardNo` int(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymanage`
--

INSERT INTO `paymanage` (`paymentID`, `date`, `paymentMethod`, `paymentAmt`, `invoiceID`, `cardNo`) VALUES
(1, '2021-04-25', 'Card', 5000, NULL, 13453456),
(2, '2021-04-26', 'Cash', 2000, 2201, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paymanage`
--
ALTER TABLE `paymanage`
  ADD PRIMARY KEY (`paymentID`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paymanage`
--
ALTER TABLE `paymanage`
  MODIFY `paymentID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
