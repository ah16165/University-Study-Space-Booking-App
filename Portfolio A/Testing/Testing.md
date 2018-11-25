//Development Testing//

The Java application server backend which deals with processing input, communicating between the web server and database server, will be tested using JUnit. There will be assertions on each function, checking the correct output is given for a specified input. For the Javascript front end, we will be using a Javascript testing framework such as Mocha. We will also be providing continuous iterations of the front end to the client, so that improvement and alterations can easily be made. Part of our JUnit tests will check that the SQL queries are correct. We will also use DbUnit for the Database, a JUnit extension to ensure that the database is correct.

/Possible Testing frameworks/

JUnit
DbUnit
Mocha

/Challenges/

The API will be difficult to test because it will be developed by us, rather than coming from an external source. To overcome this we will create our own, thorough tests. Often the front end can be hard to test, but using a framework such as mocha will enable us to ensure that there are no issues such as browser incompatibility. The database will be difficult to test without real-world data. To overcome this we will do our best to populate it with thorough data during the testing phase.

/An example test/

Below is a first draft of the tests we will implement on our preliminary idea of the function that will handle a user creating a booking. Please see the attached image for context. The actual function itself may be altered as we progress with the application, hence the tests will need to be altered to match.

The very focus of the application should be on a user creating a new booking, as this is what will be used most. This would be handled by the ***User.createBooking()*** method, which would take in the following parameters:
***roomID*** : a unique integer that corresponds to the room to be booked
***dateTime*** : the date and start time of the booking to be made
***noUsers*** : an integer to represent the number of students using the room at a time
***length*** : An integer to represent the number of hours for the booking
***userID*** : a unique integer that corresponds to the logged in user that is making the booking

***User.createBooking()*** would create a new Booking object with properties corresponding to the parameters above, using ***Booking.construct()*** which takes in the same parameters. An additional property is ***bookingID*** : which is a unique integer that identifies that booking. ***Booking.makeBooking()*** is then called, which creates and uses an SQL statement that inserts a new booking into the booking table with all of the Booking object's parameters. Find attached an image detailing the tests.
