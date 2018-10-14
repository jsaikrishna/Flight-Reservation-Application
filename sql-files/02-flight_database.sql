CREATE DATABASE IF NOT EXISTS `flight_database`;
USE `flight_database`;


CREATE TABLE IF NOT EXISTS `address` (
 `address_id` int(11) NOT NULL AUTO_INCREMENT,
 `street` varchar(45) DEFAULT NULL,
 `unit` int(11) NOT NULL,
 `city` varchar(45) DEFAULT NULL,
 `state` varchar(45) DEFAULT NULL,
 `country` varchar(45) DEFAULT NULL,
 `zipcode` int(7) NOT NULL,
 PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `person`;

CREATE TABLE IF NOT EXISTS`person` (
 `first_name` varchar(45) DEFAULT NULL,
 `last_name` varchar(45) DEFAULT NULL,
 `gender` varchar(1) DEFAULT NULL,
 `dob` date NOT NULL,
 `email` varchar(50) NOT NULL,
 `password` varchar(100) NOT NULL,
 `address_id` int(11) NOT NULL,
 PRIMARY KEY (`email`),
 KEY `address_fk` (`address_id`),
 CONSTRAINT `address_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `passenger`;

CREATE TABLE `passenger` (
 `visa_type` varchar(45) DEFAULT NULL,
 `ticket_type` varchar(45) DEFAULT NULL,
 `meal_type` varchar(45) DEFAULT NULL,
 `passport_no` varchar(45) NOT NULL,
 `email` varchar(50) NOT NULL, 
 PRIMARY KEY (`passport_no`),
 KEY `person_fk` (`email`),
 CONSTRAINT `person_fk` FOREIGN KEY (`email`) REFERENCES `person` (`email`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
 `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `no_of_reservation` int(11) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
 PRIMARY KEY (`customer_id`),
 KEY `person_fl` (`email`),
 CONSTRAINT `person_fl` FOREIGN KEY (`email`) REFERENCES `person` (`email`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;


DROP TABLE IF EXISTS `customer_passenger`;

CREATE TABLE `customer_passenger` (
 `customer_id` int(11) NOT NULL AUTO_INCREMENT,
 `passport_no` varchar(45) NOT NULL,
 PRIMARY KEY (`customer_id`,`passport_no`),
 CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
 CONSTRAINT `passenger_fk` FOREIGN KEY (`passport_no`) REFERENCES `passenger` (`passport_no`)
)ENGINE=InnoDB CHARSET=latin1;