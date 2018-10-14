CREATE TABLE flight_database.`Address` (
	`street` varchar(45) DEFAULT NULL,
    `unit` int(10) DEFAULT NULL,
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `city` varchar(45) DEFAULT NULL,
    `state` varchar(45) DEFAULT NULL,
    `country` varchar(45) DEFAULT NULL,
    `zipcode` int(10) DEFAULT NULL,
    PRIMARY KEY  (`id`)
    ) ;

    CREATE TABLE flight_database.`Person` (
	`firstName` varchar(45) DEFAULT NULL,
    `lastName` varchar(45) DEFAULT NULL,
    `id` int(10) NOT NULL,
   `gender` varchar(45) DEFAULT NULL,
   `dob` date DEFAULT NULL,
   `email` varchar(45) NOT NULL,
   `password` varchar(45) DEFAULT NULL,
    PRIMARY KEY  (`email`),
    FOREIGN KEY ( `id`)  REFERENCES flight_database.address( `id`)
    ) ;
    
    create table flight_database.`Passenger` (
    `email` varchar(50) not null,
    `visatype` varchar(10) default null,
    `passportno` varchar(10) default null,
    `mealtype` varchar(10) default null,
    `tickettype` varchar(10) default null,
    primary key (`email`)
    );
    
    create table flight_database.`Customer` (
    `email` varchar(50) not null,
    `noofreservations` varchar(10) default null,
    primary key (`email`)
    );
    
    create table flight_database.`Passenger_Customer` (
    `pemail` varchar(50) not null,
    `cemail` varchar(50) not null,
    primary key(`pemail`,`cemail`),
    foreign key(`pemail`) references `Passenger`(`email`),
    foreign key(`cemail`) references `Customer`(`email`)
    
    );
    
    create table flight_database.`Transportation` (
    `id` int(10) not null auto_increment,
    `airline` varchar(50) default null,
    `aircraft` varchar(50) default null,
    `vesselno` varchar(50) default null,
    `sourceairport` varchar(50) default null,
    `destinationairport` varchar(50) default null,
    `arrivaldate` datetime default null,
    `departuredate` datetime default null,
    `modelname` varchar(50),
    `noofseats` int(10),
    `seatsbooked` varchar(50),
    primary key(`id`)
    );
    
    create table flight_database.`Flight_details` (
    `id` int(10) not null auto_increment,
    `airline` varchar(50) default null,
    `aircraft` varchar(50) default null,
    `vesselno` varchar(50) default null,
    `sourceairport` varchar(50) default null,
    `destinationairport` varchar(50) default null,
    `arrivaldate` datetime default null,
    `departuredate` datetime default null,
    `modelname` varchar(50),
    `noofseats` int(10),
    `seatsbooked` varchar(50),
    primary key(`id`)
    );
    
    create table flight_database.`selectedtransport`(
    `stid` int(10) not null auto_increment,
    primary key(`stid`)
    #FOREIGN KEY (`id`)  REFERENCES flight_database.reservation( `id`)
    );
    
    create table flight_database.`reservation` (
    `rid` int(10) not null auto_increment,
    `id` int(10) not null,
    `email` varchar(50) not null,
    primary key(`rid`),
    FOREIGN KEY (`id`)  REFERENCES flight_database.`selectedtransport`(`stid`),
    FOREIGN KEY (`email`)  REFERENCES flight_database.`customer`(`email`)
    );
    
    
    
    create table flight_database.`selected_transportation`(
    `sid` int(10) not null,
    `tid` int(10) not null,
    primary key(`sid`,`tid`),
    foreign key(`sid`) references `selectedtransport`(`stid`),
    foreign key(`tid`) references `transportation`(`id`)
    );
    
    insert into flight_database.`flight_details` 
    values(1,"BA","BA121","121","Denver","California",
    "2017-12-15 19:05:00","2017-12-15 22:05:00",
    "BA",10,"");
    insert into flight_database.`flight_details` 
    values(2,"BA","BA242","242","Denver","California",
    "2017-12-15 19:05:00","2017-12-15 22:05:00",
    "BA",10,"");
    insert into flight_database.`flight_details` 
    values(3,"FA","FA121","121","London","California",
    "2017-12-15 19:05:00","2017-12-15 22:05:00",
    "BA",10,"");
    
    select * from flight_database.`flight_details`;
    
    
    desc flight_database.person;
    
    drop table flight_database.person;
    
    drop table flight_database.customer;
    
    drop table flight_database.passenger;
    
    drop table flight_database.transportation;
    
    drop table flight_database.reservation;
    drop table flight_database.selected_transportation;
    drop table flight_database.selectedtransport;
    drop table flight_database.transportation;
    
    select * from flight_database.customer;
    select * from flight_database.person;
    select * from flight_database.transportation;
    
    select * from  flight_database.reservation;
    select * from flight_database.selected_transportation;
    select * from flight_database.selectedtransport;
    select * from flight_database.transportation;
    
    