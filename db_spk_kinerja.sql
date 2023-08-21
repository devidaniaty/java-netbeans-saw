-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2023 at 06:15 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_spk_kinerja`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bobot`
--

CREATE TABLE `tbl_bobot` (
  `w1` float NOT NULL,
  `w2` float NOT NULL,
  `w3` float NOT NULL,
  `w4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_bobot`
--

INSERT INTO `tbl_bobot` (`w1`, `w2`, `w3`, `w4`) VALUES
(0.25, 0.3, 0.2, 0.25);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_karyawan`
--

CREATE TABLE `tbl_karyawan` (
  `id` varchar(30) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jk` enum('Laki - Laki','Perempuan') DEFAULT NULL,
  `pendidikan` enum('Tidak Sekolah','SD','SMP','SMA Sederajat','S1') DEFAULT NULL,
  `nohp` int(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_karyawan`
--

INSERT INTO `tbl_karyawan` (`id`, `nama`, `jk`, `pendidikan`, `nohp`, `alamat`) VALUES
('A01', 'Mulyad', 'Laki - Laki', 'SD', 81214538, 'Jl. Malabar No.12'),
('A02', 'Sugiarto', 'Laki - Laki', 'SMA Sederajat', 87121212, 'Jl. Merpati No.7'),
('A03', 'Yudhi Hatta', 'Laki - Laki', 'SD', 8628122, 'Jl. Buwak No.5'),
('A04', 'Deddy P.', 'Laki - Laki', 'S1', 82617181, 'Jl. Nusa Indah No.11'),
('A05', 'Hendra', 'Laki - Laki', 'SD', 81517182, 'Jl. Sukamaju No.4'),
('A06', 'Andi Ijlal', 'Laki - Laki', 'S1', 814130084, 'Jl. Melati No.98'),
('A07', 'Rendy', 'Laki - Laki', 'Tidak Sekolah', 815178882, 'Cikarang Raya No.72'),
('A08', 'Rizky', 'Laki - Laki', 'SMP', 8191017, 'Jl. Lalabar');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kriteria`
--

CREATE TABLE `tbl_kriteria` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `c1` float NOT NULL,
  `c2` float NOT NULL,
  `c3` float NOT NULL,
  `c4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_kriteria`
--

INSERT INTO `tbl_kriteria` (`id`, `nama`, `c1`, `c2`, `c3`, `c4`) VALUES
('A01', 'Mulyad', 4, 4, 4, 4),
('A02', 'Sugiarto', 4, 5, 5, 5),
('A03', 'Yudhi Hatta', 4, 5, 4, 4),
('A04', 'Deddy P.', 4, 3, 4, 4),
('A05', 'Hendra', 4, 4, 4, 4),
('A06', 'Andi Ijlal', 5, 4, 4, 5),
('A07', 'Rendy', 4, 3, 3, 4),
('A08', 'Rizky', 3, 4, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_normalisasi`
--

CREATE TABLE `tbl_normalisasi` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `c1` float NOT NULL,
  `c2` float NOT NULL,
  `c3` float NOT NULL,
  `c4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_peringkat`
--

CREATE TABLE `tbl_peringkat` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_peringkat`
--

INSERT INTO `tbl_peringkat` (`id`, `nama`, `nilai`) VALUES
('A01', 'Mulyad', 0.8),
('A02', 'Sugiarto', 0.95),
('A03', 'Yudhi Hatta', 0.86),
('A04', 'Deddy P.', 0.74),
('A05', 'Hendra', 0.8),
('A06', 'Andi Ijlal', 0.9),
('A07', 'Rendy', 0.7),
('A08', 'Rizky', 0.71);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`username`, `password`) VALUES
('admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_karyawan`
--
ALTER TABLE `tbl_karyawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_normalisasi`
--
ALTER TABLE `tbl_normalisasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  ADD CONSTRAINT `tbl_kriteria_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tbl_karyawan` (`id`);

--
-- Constraints for table `tbl_normalisasi`
--
ALTER TABLE `tbl_normalisasi`
  ADD CONSTRAINT `tbl_normalisasi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tbl_karyawan` (`id`);

--
-- Constraints for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  ADD CONSTRAINT `tbl_peringkat_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tbl_karyawan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
