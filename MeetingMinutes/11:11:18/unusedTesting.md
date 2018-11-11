# Development testing

Below is a first draft of the tests we will implement on our preliminary idea of the function that will handle a user creating a booking. The actual function itself may be altered as we progress with the application, hence the tests will need to be altered to match.

The very focus of the application should be on a user creating a new booking, as this is what will be used most. This would be handled by the ***User.createBooking()*** method, which would take in the following parameters:
* ***roomID*** : a unique integer that corresponds to the room to be booked
* ***dateTime*** : the date and start time of the booking to be made
* ***noUsers*** : an integer to represent the number of students using the room at a time
* ***length*** : An integer to represent the number of hours for the booking
* ***userID*** : a unique integer that corresponds to the logged in user that is making the booking

***User.createBooking()*** would create a new Booking object with properties corresponding to the parameters above, using ***Booking.construct()*** which takes in the same parameters. An additional property is ***bookingID*** : which is a unique integer that identifies that booking.

***Booking.makeBooking()*** is then called, which creates and uses an SQL statement that inserts a new booking into the booking table with all of the Booking object's parameters.

Below is a table representing the tests we will implement to check that ***User.createBooking()*** is functioning correctly.

| Test Description | Input | Assertion |
|----------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| Assert that a booking cannot be created if the room does not exist | roomID=-1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | Error raised |
| Assert that a booking cannot be created if there is already a booking for the same room at the same time | Run createBooking() twice, both with roomID=1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | Normal running first time, error raised on second |
| Assert that Booking.construct() is called and the output is a Booking object with attributes matching the input | roomID=1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | Booking object attributes =  Booking.roomID=1, Booking.dateTime="13:00 21.03.19", Booking.noUsers=1, Booking.length=1, Booking.userID=1 |
| Assert that Booking.makeBooking() is called and produces the correct SQL statement | roomID=1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | SQL Statement =  INSERT INTO Bookings (DateWithTime, NoUsers, Length, UserID, RoomID) VALUES (2019-03-21 13:00, 1, 1, 1, 1) |
| Assert that after User.createBooking is completed, the booking table contains 1 extra entry | roomID=1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | Before procedure call, set x=COUNT(*), then after the call check COUNT(*) = x+1 |
| Assert that after User.createBooking is completed, the booking table contains an entry that matches the parameters passed in to User.createBooking | roomID=1, dateTime="13:00 21.03.19", noUsers=1, length=1, userID=1 | EXISTS (SELECT * FROM Bookings WHERE DateWithTime=2019-03-21 13:00, NoUsers=1, Length=1 UserID=1, RoomID=1) will return true |
