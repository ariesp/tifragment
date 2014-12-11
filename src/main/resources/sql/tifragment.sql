-- phpMyAdmin SQL Dump
-- version 3.5.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 04, 2014 at 05:58 PM
-- Server version: 5.5.34-0ubuntu0.13.04.1
-- PHP Version: 5.4.9-4ubuntu2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `tifragment`
--

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentHolder`
--

CREATE TABLE `genidea_documentHolder` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_documentInfo`
--

CREATE TABLE `genidea_documentInfo` (
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_group`
--

CREATE TABLE `genidea_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_groupParticipant`
--

CREATE TABLE `genidea_groupParticipant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participantRole` int(11) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK51771E2CB7AD45CA` (`group_id`),
  KEY `FK51771E2C4281AD0A` (`user_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_group_genidea_groupParticipant`
--

CREATE TABLE `genidea_group_genidea_groupParticipant` (
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

CREATE TABLE `genidea_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` longtext,
  `title` varchar(100) DEFAULT NULL,
  `sender_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65AA160F6226F0E0` (`sender_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_messageDelivered`
--

CREATE TABLE `genidea_messageDelivered` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `destination_uuid` varchar(255) DEFAULT NULL,
  `message_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK629CCB35761E06A7` (`destination_uuid`),
  KEY `FK629CCB35F7AE1CCA` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genidea_message_tifragment_user`
--

CREATE TABLE `genidea_message_tifragment_user` (
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

CREATE TABLE `genidea_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBC15676E4281AD0A` (`user_uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genidea_role`
--

INSERT INTO `genidea_role` (`id`, `role`, `user_uuid`) VALUES
(1, 1, 'ff808181435cae8201435caf47e30000'),
(2, 1, 'ff808181435cae8201435caf72a00001');

-- --------------------------------------------------------

--
-- Table structure for table `genidea_security_code`
--

CREATE TABLE `genidea_security_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `timeRequest` datetime DEFAULT NULL,
  `typeActivationEnum` int(11) DEFAULT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA7C7E7944281AD0A` (`user_uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genidea_security_code`
--

INSERT INTO `genidea_security_code` (`id`, `code`, `timeRequest`, `typeActivationEnum`, `user_uuid`) VALUES
(1, 'jatKP4X4Xz5ANfK', '2014-01-04 16:57:07', 0, 'ff808181435cae8201435caf47e30000'),
(2, 'nGeFZDRdHBd9GDQ', '2014-01-04 16:57:18', 0, 'ff808181435cae8201435caf72a00001');

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_detail_timeframe`
--

CREATE TABLE `tifragment_detail_timeframe` (
  `uuid` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `task_name` varchar(20) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `fk_timeframe` varchar(255) DEFAULT NULL,
  `fk_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid` (`uuid`),
  KEY `FKE696C98C81494105` (`fk_timeframe`),
  KEY `FKE696C98CFE081060` (`fk_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_project`
--

CREATE TABLE `tifragment_project` (
  `uuid` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_timeframe`
--

CREATE TABLE `tifragment_timeframe` (
  `uuid` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tifragment_user`
--

CREATE TABLE `tifragment_user` (
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
('ff808181435cae8201435caf47e30000', 0, 0, 'anonymousUser', '2014-01-04 16:57:07', 'ariezprayoga@gmail.com', 1, 'Aries Prayoga', 'Aries', '5e56d994c79f335927b970d0806cdd10', '085749293238', 'projectManager', NULL, NULL, 'deodorant'),
('ff808181435cae8201435caf72a00001', 0, 0, 'anonymousUser', '2014-01-04 16:57:18', 'ariezprayoga@gmail.com', 1, 'Aries Prayoga', 'Aries', '5f6fd04739ad22c0864aae38db160bc4', '085749293238', 'projectManager', NULL, NULL, 'ariesp');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `genidea_documentInfo`
--
ALTER TABLE `genidea_documentInfo`
  ADD CONSTRAINT `FK331113614F7CB722` FOREIGN KEY (`owner_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_groupParticipant`
--
ALTER TABLE `genidea_groupParticipant`
  ADD CONSTRAINT `FK51771E2C4281AD0A` FOREIGN KEY (`user_uuid`) REFERENCES `tifragment_user` (`uuid`),
  ADD CONSTRAINT `FK51771E2CB7AD45CA` FOREIGN KEY (`group_id`) REFERENCES `genidea_group` (`id`);

--
-- Constraints for table `genidea_group_genidea_groupParticipant`
--
ALTER TABLE `genidea_group_genidea_groupParticipant`
  ADD CONSTRAINT `FK64E858E422D0F0C2` FOREIGN KEY (`genidea_group_id`) REFERENCES `genidea_group` (`id`),
  ADD CONSTRAINT `FK64E858E4110842DE` FOREIGN KEY (`participants_id`) REFERENCES `genidea_groupParticipant` (`id`);

--
-- Constraints for table `genidea_message`
--
ALTER TABLE `genidea_message`
  ADD CONSTRAINT `FK65AA160F6226F0E0` FOREIGN KEY (`sender_uuid`) REFERENCES `tifragment_user` (`uuid`);

--
-- Constraints for table `genidea_messageDelivered`
--
ALTER TABLE `genidea_messageDelivered`
  ADD CONSTRAINT `FK629CCB35F7AE1CCA` FOREIGN KEY (`message_id`) REFERENCES `genidea_message` (`id`),
  ADD CONSTRAINT `FK629CCB35761E06A7` FOREIGN KEY (`destination_uuid`) REFERENCES `tifragment_user` (`uuid`);

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
  ADD CONSTRAINT `FKE696C98CFE081060` FOREIGN KEY (`fk_user`) REFERENCES `tifragment_user` (`uuid`),
  ADD CONSTRAINT `FKE696C98C81494105` FOREIGN KEY (`fk_timeframe`) REFERENCES `tifragment_timeframe` (`uuid`);

--
-- Constraints for table `tifragment_project`
--
ALTER TABLE `tifragment_project`
  ADD CONSTRAINT `FK3CEA089FFE081060` FOREIGN KEY (`fk_user`) REFERENCES `tifragment_user` (`uuid`),
  ADD CONSTRAINT `FK3CEA089F81494105` FOREIGN KEY (`fk_timeFrame`) REFERENCES `tifragment_timeframe` (`uuid`),
  ADD CONSTRAINT `FK3CEA089F93554F17` FOREIGN KEY (`fk_project`) REFERENCES `tifragment_project` (`uuid`);
