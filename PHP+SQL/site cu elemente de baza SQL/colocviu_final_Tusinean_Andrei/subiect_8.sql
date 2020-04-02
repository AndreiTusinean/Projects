-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2019 at 01:28 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `subiect_8`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `max_lim` ()  BEGIN
SELECT nrcard,limita 
FROM Carti_de_credit
WHERE limita >= ALL(SELECT limita FROM Carti_de_credit);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `carti_de_credit`
--

CREATE TABLE `carti_de_credit` (
  `nrcard` int(10) NOT NULL,
  `data_de_la` date DEFAULT NULL,
  `data_la` date DEFAULT NULL,
  `limita` int(20) DEFAULT NULL,
  `nrcont` int(10) DEFAULT NULL,
  `tip` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carti_de_credit`
--

INSERT INTO `carti_de_credit` (`nrcard`, `data_de_la`, `data_la`, `limita`, `nrcont`, `tip`) VALUES
(120, '2018-10-14', '2025-09-12', 7800, 3, 'VISA'),
(121, '2018-07-10', '2021-07-10', 6000, 2, 'MASTERCARD'),
(122, '2018-04-01', '2020-10-30', 7000, 3, 'MASTERCARD'),
(123, '2018-09-01', '2020-08-30', 7000, 2, 'VISA'),
(124, '2015-04-12', '2023-04-12', 9000, 3, 'MASTERCARD'),
(125, '2008-09-01', '2010-08-30', 6132, 4, 'VISA'),
(126, '2013-03-14', '2016-07-12', 8532, 6, 'VISA'),
(127, '2010-10-10', '2028-09-10', 10000, 7, 'MASTERCARD'),
(123456, '2003-04-01', '2008-04-01', 6000, 1, 'MASTERCARD');

-- --------------------------------------------------------

--
-- Table structure for table `conturi`
--

CREATE TABLE `conturi` (
  `nrcont` int(10) NOT NULL,
  `sold` int(20) DEFAULT NULL,
  `idpers` int(8) DEFAULT NULL,
  `stare` varchar(3) DEFAULT 'A'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conturi`
--

INSERT INTO `conturi` (`nrcont`, `sold`, `idpers`, `stare`) VALUES
(1, 5000, 1, 'A'),
(2, 7000, 2, 'B'),
(3, 7000, 3, 'A'),
(4, 6132, 4, 'A'),
(5, 5000, 5, 'B'),
(6, 8500, 6, 'A'),
(7, 9200, 7, 'B');

-- --------------------------------------------------------

--
-- Table structure for table `miscari`
--

CREATE TABLE `miscari` (
  `nrcard` int(10) NOT NULL,
  `data_ora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valoare` int(20) DEFAULT NULL,
  `scop` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `miscari`
--

INSERT INTO `miscari` (`nrcard`, `data_ora`, `valoare`, `scop`) VALUES
(122, '2018-11-29 22:00:00', 1900, 'testing'),
(123, '2018-11-13 19:42:00', 19, 'lidl'),
(101101, '2018-03-11 22:00:00', 105, 'discount 10%'),
(123456, '2018-11-28 22:00:00', 6000, 'test buy'),
(123456, '2018-11-29 22:00:00', 123, 'test buy 2');

-- --------------------------------------------------------

--
-- Table structure for table `persoane`
--

CREATE TABLE `persoane` (
  `idpers` int(8) NOT NULL,
  `nume` varchar(32) DEFAULT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  `gen` varchar(3) DEFAULT NULL,
  `data_nasterii` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persoane`
--

INSERT INTO `persoane` (`idpers`, `nume`, `adresa`, `gen`, `data_nasterii`) VALUES
(1, 'Andrei Tusinean', 'Bistrita', 'M', '1998-04-22'),
(2, 'Pop Stefan', 'Cluj', 'M', '1998-05-12'),
(3, 'Pop Popescu', 'Cluj', 'M', '1982-00-01'),
(4, 'Ionescu Ioana', 'Cluj', 'F', '1992-01-04'),
(5, 'Basescu Elena', 'Bucuresti', 'F', '1930-09-07'),
(6, 'Basescu Traian', 'Bucuresti', 'M', '1980-12-07'),
(7, 'Klaus Iohannis', 'Bucuresti', 'M', '1980-04-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carti_de_credit`
--
ALTER TABLE `carti_de_credit`
  ADD PRIMARY KEY (`nrcard`);

--
-- Indexes for table `conturi`
--
ALTER TABLE `conturi`
  ADD PRIMARY KEY (`nrcont`);

--
-- Indexes for table `miscari`
--
ALTER TABLE `miscari`
  ADD PRIMARY KEY (`nrcard`,`data_ora`);

--
-- Indexes for table `persoane`
--
ALTER TABLE `persoane`
  ADD PRIMARY KEY (`idpers`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
