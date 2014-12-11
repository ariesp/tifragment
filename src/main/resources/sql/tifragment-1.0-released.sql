-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: 127.11.88.2:3306
-- Generation Time: May 05, 2014 at 08:26 AM
-- Server version: 5.5.36
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tifragment`
--

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentholder`
--

CREATE TABLE IF NOT EXISTS `genidea_documentholder` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentHolder`
--

CREATE TABLE IF NOT EXISTS `genidea_documentHolder` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentinfo`
--

CREATE TABLE IF NOT EXISTS `genidea_documentinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateCreated` date DEFAULT NULL,
  `dateUpdated` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `originalName` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `owner_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK331113614F7CB722` (`owner_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentInfo`
--

CREATE TABLE IF NOT EXISTS `genidea_documentInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateCreated` date DEFAULT NULL,
  `dateUpdated` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `originalName` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `owner_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_group`
--

CREATE TABLE IF NOT EXISTS `genidea_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_groupParticipant`
--

CREATE TABLE IF NOT EXISTS `genidea_groupParticipant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participantRole` int(11) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_groupparticipant`
--

CREATE TABLE IF NOT EXISTS `genidea_groupparticipant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participantRole` int(11) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK51771E2CB7AD45CA` (`group_id`),
  KEY `FK51771E2C4281AD0A` (`user_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_group_genidea_groupParticipant`
--

CREATE TABLE IF NOT EXISTS `genidea_group_genidea_groupParticipant` (
  `genidea_group_id` bigint(20) NOT NULL,
  `participants_id` bigint(20) NOT NULL,
  PRIMARY KEY (`genidea_group_id`,`participants_id`),
  UNIQUE KEY `participants_id` (`participants_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_group_genidea_groupparticipant`
--

CREATE TABLE IF NOT EXISTS `genidea_group_genidea_groupparticipant` (
  `genidea_group_id` bigint(20) NOT NULL,
  `participants_id` bigint(20) NOT NULL,
  PRIMARY KEY (`genidea_group_id`,`participants_id`),
  UNIQUE KEY `participants_id` (`participants_id`),
  KEY `FK64E858E4110842DE` (`participants_id`),
  KEY `FK64E858E422D0F0C2` (`genidea_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_message`
--

CREATE TABLE IF NOT EXISTS `genidea_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` longtext,
  `title` varchar(100) DEFAULT NULL,
  `sender_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65AA160F6226F0E0` (`sender_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_messageDelivered`
--

CREATE TABLE IF NOT EXISTS `genidea_messageDelivered` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `destination_uuid` varchar(255) DEFAULT NULL,
  `message_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_messagedelivered`
--

CREATE TABLE IF NOT EXISTS `genidea_messagedelivered` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `destination_uuid` varchar(255) DEFAULT NULL,
  `message_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK629CCB35761E06A7` (`destination_uuid`),
  KEY `FK629CCB35F7AE1CCA` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_message_tifragment_user`
--

CREATE TABLE IF NOT EXISTS `genidea_message_tifragment_user` (
  `genidea_message_id` bigint(20) NOT NULL,
  `receiver_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`genidea_message_id`,`receiver_uuid`),
  UNIQUE KEY `receiver_uuid` (`receiver_uuid`),
  KEY `FK206F9D5BBC9E566` (`receiver_uuid`),
  KEY `FK206F9D52892E9C2` (`genidea_message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_role`
--

CREATE TABLE IF NOT EXISTS `genidea_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBC15676E4281AD0A` (`user_uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `genidea_role`
--

INSERT INTO `genidea_role` (`id`, `role`, `user_uuid`) VALUES
(1, 1, 'ff808181435cae8201435caf47e30000'),
(2, 1, 'ff808181435cae8201435caf72a00001'),
(3, 2, '8aa3bfcf4566b2f9014566ba8e90000a'),
(4, 2, '8aa3bfcf4566b2f9014566bb5285000b'),
(5, 2, '8aa3bfcf4566b2f9014566bbc9d3000c'),
(6, 2, '8aa3bfcf4566b2f90145684c26230017'),
(7, 1, '8aa3bfcf457aa4a9014584f500fe0020'),
(8, 2, '8aa3bfcf457aa4a9014584f627900021');

-- --------------------------------------------------------

--
-- Table structure for table `genidea_security_code`
--

CREATE TABLE IF NOT EXISTS `genidea_security_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `timeRequest` datetime DEFAULT NULL,
  `typeActivationEnum` int(11) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA7C7E7944281AD0A` (`user_uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `genidea_security_code`
--

INSERT INTO `genidea_security_code` (`id`, `code`, `timeRequest`, `typeActivationEnum`, `user_uuid`) VALUES
(1, 'jatKP4X4Xz5ANfK', '2014-01-04 16:57:07', 0, 'ff808181435cae8201435caf47e30000'),
(2, 'nGeFZDRdHBd9GDQ', '2014-01-04 16:57:18', 0, 'ff808181435cae8201435caf72a00001'),
(3, 'e3Mkmn9JzTDyPyr', '2014-04-15 14:51:13', 0, '8aa3bfcf4566b2f9014566ba8e90000a'),
(4, 'KB5QJgqKzckTvnB', '2014-04-15 14:52:03', 0, '8aa3bfcf4566b2f9014566bb5285000b'),
(5, 'bhQ6W4pyxJPd59R', '2014-04-15 14:52:34', 0, '8aa3bfcf4566b2f9014566bbc9d3000c'),
(6, 'sx8VFKRvg865x2V', '2014-04-15 22:09:52', 0, '8aa3bfcf4566b2f90145684c26230017'),
(7, '3zNbMCVHke8YQ82', '2014-04-21 11:43:40', 0, '8aa3bfcf457aa4a9014584f500fe0020'),
(8, 'qK4zBxmfYuxrYW@', '2014-04-21 11:44:55', 0, '8aa3bfcf457aa4a9014584f627900021');

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_detail_timeframe`
--

CREATE TABLE IF NOT EXISTS `tifragment_detail_timeframe` (
  `uuid` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` longtext,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `task_name` varchar(20) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `fk_timeframe` varchar(255) DEFAULT NULL,
  `fk_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`,`id`),
  UNIQUE KEY `uuid` (`uuid`),
  KEY `FKE696C98C81494105` (`fk_timeframe`),
  KEY `FKE696C98CFE081060` (`fk_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tifragment_detail_timeframe`
--

INSERT INTO `tifragment_detail_timeframe` (`uuid`, `id`, `created_by`, `created_date`, `description`, `end_date`, `start_date`, `status`, `task_name`, `updated_by`, `updated_date`, `fk_timeframe`, `fk_user`) VALUES
('8aa3bfcf4566b2f9014566bdcbdc000d', 1, 'ariesp', '2014-04-15 14:54:45', 'nyapu lantai, atap, genteng, kolong', '2014-05-22 00:00:00', '2014-04-17 00:00:00', 1, 'nyapu', NULL, NULL, '8aa3bfcf4566b2f9014566b8c8d10009', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf4566b2f9014566be7465000e', 2, 'ariesp', '2014-04-15 14:55:28', 'ngepel lantai, aspal, tembok', '2014-04-26 00:00:00', '2014-04-21 00:00:00', 1, 'ngepel', 'ariesp', '2014-04-15 15:07:28', '8aa3bfcf4566b2f9014566b8c8d10009', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf4566b2f9014566bf4363000f', 3, 'ariesp', '2014-04-15 14:56:21', 'sholat, dzikir, tirakad, ngaji', '2014-05-27 00:00:00', '2014-04-15 00:00:00', 1, 'tobat', 'ariesp', '2014-04-15 15:05:52', '8aa3bfcf4566b2f9014566b8c8d10009', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf4566b2f9014566c07f1e0010', 4, 'ariesp', '2014-04-15 14:57:42', 'patroli kampung, kampus, kandang', '2014-04-30 00:00:00', '2014-04-21 00:00:00', 1, 'patrol', 'deodorant', '2014-04-16 08:44:06', '8aa3bfcf4566b2f9014566b8c8d10009', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf4566b2f9014566cbf2360012', 5, 'ariesp', '2014-04-15 15:10:13', 'create project using eclipse', '2014-04-17 00:00:00', '2014-04-15 00:00:00', 1, 'create project', 'ariesp', '2014-04-15 15:13:43', '8aa3bfcf4566b2f9014566cb010d0011', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf4566b2f9014566cc74420013', 6, 'ariesp', '2014-04-15 15:10:46', 'add dependency library into project', '2014-04-19 00:00:00', '2014-04-17 00:00:00', 1, 'prepare library', NULL, NULL, '8aa3bfcf4566b2f9014566cb010d0011', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf4566b2f9014566cd09c50014', 7, 'ariesp', '2014-04-15 15:11:24', 'install and configure server', '2014-04-24 00:00:00', '2014-04-21 00:00:00', 1, 'prepare server', NULL, NULL, '8aa3bfcf4566b2f9014566cb010d0011', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf4566b2f9014566ce11460015', 8, 'ariesp', '2014-04-15 15:12:32', 'create table and it''s relation, create eer, define procedure and function', '2014-04-26 00:00:00', '2014-04-21 00:00:00', 1, 'design database', NULL, NULL, '8aa3bfcf4566b2f9014566cb010d0011', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf4566b2f9014566cec8c30016', 9, 'ariesp', '2014-04-15 15:13:19', 'create mockup for all requirements', '2014-04-30 00:00:00', '2014-04-15 00:00:00', 1, 'create mockup', NULL, NULL, '8aa3bfcf4566b2f9014566cb010d0011', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a901457acb1fe60000', 11, 'ariesp', '2014-04-19 12:21:43', 'narik angkot', '2014-04-25 00:00:00', '2014-04-21 00:00:00', 1, 'narik', NULL, NULL, '8aa3bfcf4566b2f9014566b8c8d10009', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a901457acdf7ff0002', 12, 'ariesp', '2014-04-19 12:24:49', 'buat proposal pengajuan', '2014-04-21 00:00:00', '2014-04-19 00:00:00', 1, 'buat proposal', NULL, NULL, '8aa3bfcf457aa4a901457acd2cd30001', '8aa3bfcf4566b2f90145684c26230017'),
('8aa3bfcf457aa4a901457aced5450003', 13, 'ariesp', '2014-04-19 12:25:46', 'cek kerjaan karyawan kantor', '2014-04-22 00:00:00', '2014-04-19 00:00:00', 1, 'cek karyawan', NULL, NULL, '8aa3bfcf457aa4a901457acd2cd30001', '8aa3bfcf4566b2f90145684c26230017'),
('8aa3bfcf457aa4a901457acf6f9d0004', 14, 'ariesp', '2014-04-19 12:26:26', 'bantuin bikin proposal, ngecek karyawan, beliin nasi bungkus', '2014-04-22 00:00:00', '2014-04-19 00:00:00', 1, 'bantuin bejo', NULL, NULL, '8aa3bfcf457aa4a901457acd2cd30001', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a901457ad086120005', 15, 'ariesp', '2014-04-19 12:27:37', 'jagain warung sebelah', '2014-04-21 00:00:00', '2014-04-21 00:00:00', 1, 'jaga warung', NULL, NULL, '8aa3bfcf457aa4a901457acd2cd30001', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a90145826d922a0008', 17, 'deodorant', '2014-04-20 23:56:30', '', '2014-05-05 00:00:00', '2014-05-01 00:00:00', 1, 'create mockup', NULL, NULL, '8aa3bfcf457aa4a901457f5866ab0006', '8aa3bfcf4566b2f90145684c26230017'),
('8aa3bfcf457aa4a90145842fea89000b', 18, 'ariesp', '2014-04-21 08:08:23', 'Form Login', '2014-05-02 00:00:00', '2014-05-01 00:00:00', 1, 'Login', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a90145843184d7000c', 19, 'ariesp', '2014-04-21 08:10:08', 'Form Home (Point + Promo + News) ', '2014-05-03 00:00:00', '2014-05-01 00:00:00', 1, 'Home', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a90145843239c1000d', 20, 'ariesp', '2014-04-21 08:10:55', 'Form Detail News / Promo', '2014-05-08 00:00:00', '2014-05-05 00:00:00', 1, 'Home', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a901458432b09e000e', 21, 'ariesp', '2014-04-21 08:11:25', 'General Sidebar', '2014-05-02 00:00:00', '2014-05-01 00:00:00', 1, 'Sidebar', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a9014584336a60000f', 22, 'ariesp', '2014-04-21 08:12:13', 'Detail Sidebar', '2014-05-09 00:00:00', '2014-05-07 00:00:00', 1, 'Sidebar', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a90145843490510011', 24, 'ariesp', '2014-04-21 08:13:28', 'Form List Point Issuer', '2014-05-10 00:00:00', '2014-05-07 00:00:00', 1, 'Point Issuer Con', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a90145843500590012', 25, 'ariesp', '2014-04-21 08:13:57', 'Form Point at External Source', '2014-05-09 00:00:00', '2014-05-07 00:00:00', 1, 'Transfer Point', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a9014584355ca30013', 26, 'ariesp', '2014-04-21 08:14:20', 'Form Point at MyPoint', '2014-05-12 00:00:00', '2014-05-10 00:00:00', 1, 'Transfer Point', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a901458435cb7d0014', 27, 'ariesp', '2014-04-21 08:14:49', 'Form List Catalogue', '2014-05-10 00:00:00', '2014-05-06 00:00:00', 1, 'Catalogue', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a90145843643cc0015', 28, 'ariesp', '2014-04-21 08:15:20', 'Form Detail Product', '2014-05-15 00:00:00', '2014-05-12 00:00:00', 1, 'Catalogue', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a9014584369c270016', 29, 'ariesp', '2014-04-21 08:15:42', 'Form Redeem with Participant Merchant', '2014-05-17 00:00:00', '2014-05-15 00:00:00', 1, 'Catalogue', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a9014584371e780017', 30, 'ariesp', '2014-04-21 08:16:15', 'Form Authentication Process', '2014-05-09 00:00:00', '2014-05-07 00:00:00', 1, 'Point Redeem Gateway', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a901458437a0480018', 31, 'ariesp', '2014-04-21 08:16:49', 'Form Enter Token', '2014-05-12 00:00:00', '2014-05-10 00:00:00', 1, 'Point Redeem Gateway', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a901458437fc8d0019', 32, 'ariesp', '2014-04-21 08:17:12', 'Form Receipt', '2014-05-14 00:00:00', '2014-05-13 00:00:00', 1, 'Receipt', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a90145843879af001a', 33, 'ariesp', '2014-04-21 08:17:44', 'Form Transaction History', '2014-05-20 00:00:00', '2014-05-17 00:00:00', 1, 'Point History', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a901458438d953001b', 34, 'ariesp', '2014-04-21 08:18:09', 'From Source Summary', '2014-05-23 00:00:00', '2014-05-21 00:00:00', 1, 'Point History', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf457aa4a9014584394020001c', 35, 'ariesp', '2014-04-21 08:18:35', 'Form List Order', '2014-05-15 00:00:00', '2014-05-13 00:00:00', 1, 'Order History', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf457aa4a901458439b7fe001d', 36, 'ariesp', '2014-04-21 08:19:06', 'Form View Profile', '2014-05-17 00:00:00', '2014-05-15 00:00:00', 1, 'Profile', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a90145843a2135001e', 37, 'ariesp', '2014-04-21 08:19:33', 'Form View Profile', '2014-05-21 00:00:00', '2014-05-19 00:00:00', 1, 'Profile', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf457aa4a90145843ab0c2001f', 38, 'ariesp', '2014-04-21 08:20:09', '', '2014-05-31 00:00:00', '2014-05-24 00:00:00', 1, 'Finalisasi', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf458780a50145879d3c240002', 41, 'ariesp', '2014-04-22 00:06:39', 'module B', '2014-05-10 00:00:00', '2014-05-01 00:00:00', 1, 'Buat module B', 'deodorant', '2014-04-22 01:45:57', '8aa3bfcf458780a501458799f8d30001', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf458780a50145879e73930003', 42, 'ariesp', '2014-04-22 00:07:59', 'module A', '2014-05-10 00:00:00', '2014-05-05 00:00:00', 1, 'Buat module A', 'deodorant', '2014-04-22 01:45:29', '8aa3bfcf458780a501458799f8d30001', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf458780a5014587a5a36e0004', 43, 'ariesp', '2014-04-22 00:15:50', 'buat skrip', '2014-05-08 00:00:00', '2014-05-01 00:00:00', 1, 'skrip', NULL, NULL, '8aa3bfcf457aa4a90145842f4557000a', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbddaa130005', 51, 'deodorant', '2014-05-02 03:37:17', 'create webapps project using eclipse IDE', '2014-05-02 00:00:00', '2014-05-02 00:00:00', 1, 'create project', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbde49670006', 52, 'deodorant', '2014-05-02 03:37:58', 'add dependency project using maven', '2014-05-05 00:00:00', '2014-05-02 00:00:00', 1, 'add dependency', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbdef3b20007', 53, 'deodorant', '2014-05-02 03:38:41', 'create versioning system using Git', '2014-05-09 00:00:00', '2014-05-08 00:00:00', 1, 'prepare versioning', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbdfe9a40008', 54, 'deodorant', '2014-05-02 03:39:44', '', '2014-05-10 00:00:00', '2014-05-02 00:00:00', 1, 'prepare system flow', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe0d2ce000b', 57, 'deodorant', '2014-05-02 03:40:44', 'create mockup for all modules', '2014-05-10 00:00:00', '2014-05-02 00:00:00', 1, 'create mockup', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbe1abf7000c', 58, 'deodorant', '2014-05-02 03:41:40', 'create transaction for select, insert and update for user buyer', '2014-05-15 00:00:00', '2014-05-12 00:00:00', 1, 'module buyer', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf4566b2f9014566bb5285000b'),
('8aa3bfcf45a0007a0145bbe20aff000d', 59, 'deodorant', '2014-05-02 03:42:04', 'create view for module seller', '2014-05-17 00:00:00', '2014-05-15 00:00:00', 1, 'module seller', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe2ed6d000e', 60, 'deodorant', '2014-05-02 03:43:02', 'do test for all functions', '2014-05-31 00:00:00', '2014-05-26 00:00:00', 1, 'prepare UAT', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf457aa4a9014584f627900021'),
('8aa3bfcf45a0007a0145bbe3a7c7000f', 61, 'deodorant', '2014-05-02 03:43:50', 'implement security service using CAS', '2014-05-24 00:00:00', '2014-05-21 00:00:00', 1, 'implement security', NULL, NULL, '8aa3bfcf45a0007a0145bbdcc3480004', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe6b1df0011', 62, 'deodorant', '2014-05-02 03:47:09', 'create java project using maven', '2014-05-05 00:00:00', '2014-05-05 00:00:00', 1, 'prepare project', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe714150012', 63, 'deodorant', '2014-05-02 03:47:34', 'add dependency project in pom.xml', '2014-05-05 00:00:00', '2014-05-05 00:00:00', 1, 'add dependency', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe7931f0013', 64, 'deodorant', '2014-05-02 03:48:07', 'implement jdbc connector for MySQL', '2014-05-06 00:00:00', '2014-05-05 00:00:00', 1, 'implement jdbc', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe7ee670014', 65, 'deodorant', '2014-05-02 03:48:30', 'create a unit test for all implementations', '2014-05-07 00:00:00', '2014-05-05 00:00:00', 1, 'create unit testing', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe8aaa80015', 66, 'deodorant', '2014-05-02 03:49:18', 'implement remote service for transaction', '2014-05-09 00:00:00', '2014-05-05 00:00:00', 1, 'implement service', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566bbc9d3000c'),
('8aa3bfcf45a0007a0145bbe8fb0e0016', 67, 'deodorant', '2014-05-02 03:49:39', 'do full test', '2014-05-17 00:00:00', '2014-05-14 00:00:00', 1, 'testing', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566ba8e90000a'),
('8aa3bfcf45a0007a0145bbe92ce30017', 68, 'deodorant', '2014-05-02 03:49:51', 'do full test', '2014-05-17 00:00:00', '2014-05-14 00:00:00', 1, 'testing', NULL, NULL, '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf4566b2f9014566bbc9d3000c');

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_project`
--

CREATE TABLE IF NOT EXISTS `tifragment_project` (
  `uuid` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `fk_project` varchar(255) DEFAULT NULL,
  `fk_timeFrame` varchar(255) DEFAULT NULL,
  `fk_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid` (`uuid`),
  KEY `FK3CEA089F93554F17` (`fk_project`),
  KEY `FK3CEA089F81494105` (`fk_timeFrame`),
  KEY `FK3CEA089FFE081060` (`fk_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tifragment_project`
--

INSERT INTO `tifragment_project` (`uuid`, `created_by`, `created_date`, `description`, `name`, `status`, `updated_by`, `updated_date`, `fk_project`, `fk_timeFrame`, `fk_user`) VALUES
('8aa3bfcf4566b2f9014566b531b50000', 'deodorant', '2014-04-15 14:45:21', 'Insana\r\nL-Men\r\nKompor', 'InSana Men KomporeSono', 1, 'ariesp', '2014-04-19 12:30:56', NULL, '8aa3bfcf457aa4a901457acd2cd30001', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b5bde80001', 'deodorant', '2014-04-15 14:45:57', 'Virtual Xchange', 'Dandelion', 1, 'deodorant', '2014-04-20 09:23:27', NULL, '8aa3bfcf4566b2f9014566cb010d0011', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b678a90002', 'deodorant', '2014-04-15 14:46:45', '', 'ProjectC', 1, 'ariesp', '2014-04-20 09:34:31', NULL, '8aa3bfcf457aa4a901457f5866ab0006', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b6a5aa0003', 'deodorant', '2014-04-15 14:46:57', '', 'ProjectD', 1, NULL, NULL, NULL, NULL, 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b6d2130004', 'deodorant', '2014-04-15 14:47:08', '', 'ProjectE', 1, NULL, NULL, NULL, NULL, 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b706ca0005', 'deodorant', '2014-04-15 14:47:22', '', 'ProjectAB', 1, NULL, NULL, NULL, NULL, 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b72fc70006', 'deodorant', '2014-04-15 14:47:32', '', 'Tifragment', 1, 'deodorant', '2014-04-20 07:38:30', '8aa3bfcf4566b2f9014566b706ca0005', '8aa3bfcf4566b2f9014566b8c8d10009', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b7667f0007', 'deodorant', '2014-04-15 14:47:46', '', 'ProjectDEF', 1, NULL, NULL, '8aa3bfcf4566b2f9014566b706ca0005', NULL, 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf4566b2f9014566b784980008', 'deodorant', '2014-04-15 14:47:54', '', 'ProjectGHI', 1, NULL, NULL, '8aa3bfcf4566b2f9014566b706ca0005', NULL, 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf457aa4a90145842e2c8b0009', 'ariesp', '2014-04-21 08:06:29', 'MyPoint Android', 'MyPoint', 1, 'ariesp', '2014-04-21 08:07:41', NULL, '8aa3bfcf457aa4a90145842f4557000a', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf458780a50145879258bb0000', 'ariesp', '2014-04-21 23:54:46', '', 'ForcedOne', 1, 'tifragment', '2014-05-03 06:45:18', NULL, '8aa3bfcf458780a501458799f8d30001', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf45a0007a0145ae9850480000', 'ariesp', '2014-04-29 13:46:28', 'Tugas Kelompok', 'GLite', 1, 'tifragment', '2014-05-03 06:43:42', NULL, NULL, '8aa3bfcf457aa4a9014584f500fe0020'),
('8aa3bfcf45a0007a0145bbdb693e0001', 'deodorant', '2014-05-02 03:34:49', 'Xchange Services', 'Dandelion', 1, 'deodorant', '2014-05-02 03:36:18', NULL, '8aa3bfcf45a0007a0145bbdcc3480004', 'ff808181435cae8201435caf72a00001'),
('8aa3bfcf45a0007a0145bbdbafba0002', 'deodorant', '2014-05-02 03:35:07', 'Broker', 'CBroker', 1, NULL, NULL, '8aa3bfcf45a0007a0145bbdb693e0001', NULL, '8aa3bfcf457aa4a9014584f500fe0020'),
('8aa3bfcf45a0007a0145bbdbf2b60003', 'deodorant', '2014-05-02 03:35:25', '', 'DConnector', 1, 'deodorant', '2014-05-02 03:46:43', '8aa3bfcf45a0007a0145bbdb693e0001', '8aa3bfcf45a0007a0145bbe64c340010', '8aa3bfcf457aa4a9014584f500fe0020');

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_sequence`
--

CREATE TABLE IF NOT EXISTS `tifragment_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tifragment_sequence`
--

INSERT INTO `tifragment_sequence` (`next_val`) VALUES
(81);

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_timeframe`
--

CREATE TABLE IF NOT EXISTS `tifragment_timeframe` (
  `uuid` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` longtext,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tifragment_timeframe`
--

INSERT INTO `tifragment_timeframe` (`uuid`, `created_by`, `created_date`, `description`, `end_date`, `start_date`, `status`, `name`, `updated_by`, `updated_date`) VALUES
('8aa3bfcf4566b2f9014566b8c8d10009', 'deodorant', '2014-04-15 14:49:17', 'Time Plan Project ABC', '2014-05-22 00:00:00', '2014-04-15 00:00:00', 0, 'Gondes Putri', 'deodorant', '2014-04-27 13:34:28'),
('8aa3bfcf4566b2f9014566cb010d0011', 'ariesp', '2014-04-15 15:09:11', '', '2014-04-30 00:00:00', '2014-04-15 00:00:00', 0, 'Legolas', 'deodorant', '2014-04-27 13:34:33'),
('8aa3bfcf457aa4a901457acd2cd30001', 'ariesp', '2014-04-19 12:23:57', '', '2014-04-22 00:00:00', '2014-04-19 00:00:00', 0, 'Gondangdia', 'deodorant', '2014-04-27 13:34:22'),
('8aa3bfcf457aa4a901457f5866ab0006', 'ariesp', '2014-04-20 09:34:31', '', '2014-05-31 00:00:00', '2014-05-01 00:00:00', 0, 'HyperStone', 'deodorant', '2014-04-27 13:34:56'),
('8aa3bfcf457aa4a90145842f4557000a', 'ariesp', '2014-04-21 08:07:41', '', '2014-05-31 00:00:00', '2014-05-01 00:00:00', 1, 'MyPoint', NULL, NULL),
('8aa3bfcf458780a501458799f8d30001', 'ariesp', '2014-04-22 00:03:06', 'RPL2', '2014-05-30 00:00:00', '2014-05-01 00:00:00', 0, 'RPL2', 'ariesp', '2014-04-24 05:35:46'),
('8aa3bfcf45a0007a0145bbdcc3480004', 'deodorant', '2014-05-02 03:36:18', '', '2014-05-31 00:00:00', '2014-05-02 00:00:00', 1, 'XChange Service', NULL, NULL),
('8aa3bfcf45a0007a0145bbe64c340010', 'deodorant', '2014-05-02 03:46:43', '', '2014-05-17 00:00:00', '2014-05-05 00:00:00', 1, 'DCc', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_user`
--

CREATE TABLE IF NOT EXISTS `tifragment_user` (
  `uuid` varchar(255) NOT NULL,
  `accountExpired` tinyint(1) NOT NULL,
  `accountLocked` tinyint(1) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `position` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `username` varchar(10) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid` (`uuid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tifragment_user`
--

INSERT INTO `tifragment_user` (`uuid`, `accountExpired`, `accountLocked`, `created_by`, `created_date`, `email`, `enabled`, `full_name`, `name`, `password`, `phone_number`, `position`, `updated_by`, `updated_date`, `username`) VALUES
('8aa3bfcf4566b2f9014566ba8e90000a', 0, 0, 'ariesp', '2014-04-15 14:51:13', 'budi@gmail.com', 1, 'Budi Sunarno', 'Budi', 'dd5aef1098f2f4e496ab6697861da8f1', '08123456789', 'developer', 'admin', '2014-04-19 11:49:58', 'budi'),
('8aa3bfcf4566b2f9014566bb5285000b', 0, 0, 'ariesp', '2014-04-15 14:52:03', 'udin@gmail.com', 1, 'Udin Mangkunegoro', 'Udin', 'd9905d9571308fc7a82e333af6409725', '08123456789', 'developer', NULL, NULL, 'udin'),
('8aa3bfcf4566b2f9014566bbc9d3000c', 0, 0, 'ariesp', '2014-04-15 14:52:34', 'ucok@gmail.com', 1, 'Ucok Djajarahardja', 'Ucok', '176171f69b148f08d0f6e2565d3fe92e', '08123456789', 'developer', NULL, NULL, 'ucok'),
('8aa3bfcf4566b2f90145684c26230017', 0, 0, 'ariesp', '2014-04-15 22:09:52', 'bejo@gmail.com', 1, '', 'Bejo', 'df3957da0f72f93df6bab35aeda1fac4', '08765432190', 'developer', 'admin', '2014-04-19 11:48:45', 'bejo'),
('8aa3bfcf457aa4a9014584f500fe0020', 0, 0, 'deodorant', '2014-04-21 11:43:40', 'zap@gmail.com', 1, 'Led Zappelin', 'Led', '65cfae48bd3f9fb3a35221007df19497', '08123456789', 'projectManager', NULL, NULL, 'tifragment'),
('8aa3bfcf457aa4a9014584f627900021', 0, 0, 'deodorant', '2014-04-21 11:44:55', 'john@yahoo.com', 1, 'John Lennon', 'John', '672d4bd7e7e0861b2113ccfabe2d0ab7', '08123456789', 'developer', NULL, NULL, 'developer'),
('ff808181435cae8201435caf47e30000', 0, 0, 'anonymousUser', '2014-01-04 16:57:07', 'ariezprayoga@gmail.com', 1, 'Aries Prayoga', 'Aries', '5e56d994c79f335927b970d0806cdd10', '085749293238', 'projectManager', NULL, NULL, 'deodorant'),
('ff808181435cae8201435caf72a00001', 0, 0, 'anonymousUser', '2014-01-04 16:57:18', 'ariezprayoga@gmail.com', 1, 'Aries Prayoga', 'Aries', 'd81128027ce03a8c84b1d0bbf2650db7', '085749293238', 'projectManager', 'admin', '2014-04-15 14:44:36', 'ariesp');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `genidea_documentinfo`
--
ALTER TABLE `genidea_documentinfo`
  ADD CONSTRAINT `FK331113614F7CB722` FOREIGN KEY (`owner_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_groupparticipant`
--
ALTER TABLE `genidea_groupparticipant`
  ADD CONSTRAINT `FK51771E2C4281AD0A` FOREIGN KEY (`user_uuid`) REFERENCES `tifragment_user` (`uuid`),
  ADD CONSTRAINT `FK51771E2CB7AD45CA` FOREIGN KEY (`group_id`) REFERENCES `genidea_group` (`id`);

--
-- Constraints for table `genidea_group_genidea_groupparticipant`
--
ALTER TABLE `genidea_group_genidea_groupparticipant`
  ADD CONSTRAINT `FK64E858E4110842DE` FOREIGN KEY (`participants_id`) REFERENCES `genidea_groupparticipant` (`id`),
  ADD CONSTRAINT `FK64E858E422D0F0C2` FOREIGN KEY (`genidea_group_id`) REFERENCES `genidea_group` (`id`);

--
-- Constraints for table `genidea_message`
--
ALTER TABLE `genidea_message`
  ADD CONSTRAINT `FK65AA160F6226F0E0` FOREIGN KEY (`sender_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_messagedelivered`
--
ALTER TABLE `genidea_messagedelivered`
  ADD CONSTRAINT `FK629CCB35761E06A7` FOREIGN KEY (`destination_uuid`) REFERENCES `tifragment_user` (`uuid`),
  ADD CONSTRAINT `FK629CCB35F7AE1CCA` FOREIGN KEY (`message_id`) REFERENCES `genidea_message` (`id`);

--
-- Constraints for table `genidea_message_tifragment_user`
--
ALTER TABLE `genidea_message_tifragment_user`
  ADD CONSTRAINT `FK206F9D52892E9C2` FOREIGN KEY (`genidea_message_id`) REFERENCES `genidea_message` (`id`),
  ADD CONSTRAINT `FK206F9D5BBC9E566` FOREIGN KEY (`receiver_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_role`
--
ALTER TABLE `genidea_role`
  ADD CONSTRAINT `FKBC15676E4281AD0A` FOREIGN KEY (`user_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_security_code`
--
ALTER TABLE `genidea_security_code`
  ADD CONSTRAINT `FKA7C7E7944281AD0A` FOREIGN KEY (`user_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `tifragment_detail_timeframe`
--
ALTER TABLE `tifragment_detail_timeframe`
  ADD CONSTRAINT `FKE696C98C81494105` FOREIGN KEY (`fk_timeframe`) REFERENCES `tifragment_timeframe` (`uuid`),
  ADD CONSTRAINT `FKE696C98CFE081060` FOREIGN KEY (`fk_user`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `tifragment_project`
--
ALTER TABLE `tifragment_project`
  ADD CONSTRAINT `FK3CEA089F81494105` FOREIGN KEY (`fk_timeFrame`) REFERENCES `tifragment_timeframe` (`uuid`),
  ADD CONSTRAINT `FK3CEA089F93554F17` FOREIGN KEY (`fk_project`) REFERENCES `tifragment_project` (`uuid`),
  ADD CONSTRAINT `FK3CEA089FFE081060` FOREIGN KEY (`fk_user`) REFERENCES `tifragment_user` (`uuid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
