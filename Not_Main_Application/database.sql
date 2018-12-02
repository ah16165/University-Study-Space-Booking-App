-- ************************************** `Users`

CREATE TABLE `Users`
(
 `UserID`      integer NOT NULL ,
 `Email`       nvarchar(50) NOT NULL ,
 `Name`        char(20) NOT NULL ,
 `Faculty`     char(20) NOT NULL ,
 `YearOfStudy` int NOT NULL ,
PRIMARY KEY (`UserID`)
);

-- ************************************** `Rooms`

CREATE TABLE `Rooms`
(
 `RoomID`   int NOT NULL ,
 `RoomNo`   int NOT NULL ,
 `Building` char(20) NOT NULL ,
 `Capacity` int NOT NULL ,
PRIMARY KEY (`RoomID`)
);

-- ************************************** `users_sessions`

CREATE TABLE `users_sessions`
(
 `id`     int NOT NULL ,
 `UserID` integer NOT NULL ,
 `hash`   nvarchar(100) NOT NULL ,
PRIMARY KEY (`id`, `UserID`),
KEY `fkIdx_25` (`UserID`),
CONSTRAINT `FK_25` FOREIGN KEY `fkIdx_25` (`UserID`) REFERENCES `Users` (`UserID`)
);

-- ************************************** `Bookings`

CREATE TABLE `Bookings`
(
 `BookingID` int NOT NULL ,
 `UserID`    integer NOT NULL ,
 `RoomID`    int NOT NULL ,
 `DateTime`  datetime NOT NULL ,
 `NoUsers`   int NOT NULL ,
 `Length`    int NOT NULL ,
PRIMARY KEY (`BookingID`, `UserID`, `RoomID`),
KEY `fkIdx_28` (`UserID`),
CONSTRAINT `FK_28` FOREIGN KEY `fkIdx_28` (`UserID`) REFERENCES `Users` (`UserID`),
KEY `fkIdx_31` (`RoomID`),
CONSTRAINT `FK_31` FOREIGN KEY `fkIdx_31` (`RoomID`) REFERENCES `Rooms` (`RoomID`)
);





