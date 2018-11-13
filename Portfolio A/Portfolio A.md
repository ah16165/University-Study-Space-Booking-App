//Overview//

The team’s assignment is to create a study space booking application for use by engineering students at the University of Bristol to book study spaces in the Queen’s building. This document provides our assessment of the project, and highlights some key parts at a high level. We will be constantly iterating and reflecting our work, so the design, flows and testing is be changed and improved upon after the completion of this document.

/Client/

The client is the University of Bristol. More specifically, we will be working for the Faculty of Engineering, with our primary point of contact being Rahim Ahmed who is the Faculty of Engineering office manager. We will also be liaising with the IT team, both at a faculty level and a university level.

/Application Domain/

The sector we are working in is the educational sector. Despite this, the team is treating this very much in the same way that you would treat any industry client.

/Key Reasons for the Product/

Currently the Faculty of Engineering admin and library staff use crude methods for booking study spaces - google docs etc. On top of this, students must go in person to the library desk in order to book study spaces. The client is keen for us to create a solution that streamlines and automates the process, with students being able to book remotely, and admin staff only being needed to manage booking in exceptional circumstances. Other features and points to note:
* The product needs to be expandable to other study spaces outside of the Queen’s building in the future.
* The product is intended to be exclusively used by engineering undergraduates, with restrictions in place so that other university students cannot book study spaces in the Queen’s building.
* The product should integrate the single sign on system.
* Limits to how often and for how long students can book study spaces should be included in the product.
* There should be a separate admin page with admin functions.

/Vision of Product/

The team hope to achieve an elegant and attractive solution to the client brief that is user friendly and aesthetically pleasing on the front end, and expandable, efficient and intuitive on the backend. The front end will be a HTML, JavaScript and CSS based web-app with multiple pages and an admin page. This will be accessible from the University of Bristol website, and will allow all the functions that the client briefed us on. The backend system will be housed on the Oracle cloud server systems. Here we will be using Java as our backend language, with SQL being our language for database integration. The database will be integrated with mySQL, and we will be using a two server model (web server / applications server).

//Requirements//

/Stakeholders/

Rahim Ahmed - Faculty Office Manager for the Engineering Department
The space booking app was requested by Rahim, and he laid out his own specification. Although Rahim is not and end-user, he must approve of the application and is responsible for it in the University. His priorities are in student and admin ease of use.

UoB Engineering Undergrads
These students form one side of the main end users. They themselves will be using the app to book the room.

UoB Engineering Office Admins / Librarians
These are the other side of the end users. They will use the app to monitor and manage the bookings, aiding the students and the process.

Chris Totthill - IT Manager for the Engineering Department
Chris is in charge of implementation and purchase of new applications, and his approval is also needed.

University IT and Maintenance Teams
They will be in charge of maintaining and updating the application post release. They will also deal with students needing help.

/High Level Use Case Diagrams/

See image.

/Functional Requirements/

* To be able book one of many study space rooms in the Engineering department, the core of the project.
* To log in with Single Sign On, fitting in with other University applications and increasing speed and ease of use for users
* To see the availability of rooms before booking, enabling users to plan ahead
* To have the ability to cancel bookings, both for users and admins
* For students to view their current bookings, and admins to view all bookings for management
* To be able to export bookings to eCalendars to improve ease of use for users
* For fairness and wellbeing, students will only be able to book up to 2 hours per day (10 a week)
* All required information should be stored on a database
* Have a separate version for administrators, enabling them to manage the system
* There should be a page to show statistics about the use of the application, viewable by administrators only
* Administrators should be able to blacklist students
* Students not from the Engineering department should not be allowed to book rooms
* Have a terms and conditions page, highlighting the current policies of the rooms
* Have wellbeing information
* There should be a grid or table to display availability of rooms on specific days
* Administrators should be able to book rooms for students
* Once a room has been booked at a time, it cannot be booked by other students unless it is cancelled

/Non-Functional Requirements/

* The time from loading the initial login page completing a room booking should be very quick (1 minute max)
* The web page, database and servers should all be secure - meaning that they are not susceptible to malicious injection or information exposure.
* Ease of use should be a priority. No manual or instructions should be required by students in order to operate the application. Administrators have extra features so may need minimal coaching but the application should be intuitive enough for most to understand without.
* Accessible and fair to all users, rooms shouldn't be booked up by the same people constantly
* Efficient storage usage so that server costs are kept down
* The application and code should be easy to improve, extend and add features to. Code should be clean and well documented.
* The user interface should be aesthetically pleasing, to provide a positive experience so that users will want to use the app
* If the application is a success, other departments in the University may want the application to be extended to include them. The application should be able to scale up to accommodate this.
* The application should have a web user interface, and a server back-end
* Access to the application should be 24/7, with some allowance for updated and maintenance to the system itself
* Users should be able to access the application remotely, whether on the University network (Eduroam) or not.

/Flow Breakdown of Student Booking then Using a Room/

Basic Flow¹
Student opens browser on phone/tablet/computer
Student enters the URL for the Study Space Booking Application
The student then clicks log in
The student then logs in via single sign on
At the home screen, the user chooses to click the "View rooms/Make a booking" button
After picking the day they want to book, the user is presented with a grid/table displaying the availability of all the rooms that day
The student may click a room to find out some more information
After deciding on a room and an available slot, the user clicks it
A confirmation page lets the user know that they have booked the room
The student then goes to the room


Alternative Flow²
The student does not have access to the a personal device
The student approaches a librarian/admin and asks them to book a room for them
The admin logs in to their admin account, and repeats the process as above but books for the student

Exceptional Flow³
A student wishes to book a slot that another student has already booked
By the requirements, this is not allowed and so the student must find another, not already booked slot
A student accidentally booked the wrong room
The student can cancel the room by viewing their bookings, and clicking cancel

/Flow Breakdown of Admin Viewing Statistics/

Basic Flow¹
Admin opens browser on phone/tablet/computer
Admin enters the URL for the Study Space Booking Application
The admin then clicks log in
The admin then logs in via single sign on
At the admin home screen, the admin clicks the "View statistics" button
The admin is then presented with a set of options to view the statistics
After selecting the options they want, the relevant statistics are presented

Exceptional Flow³
The admin accidentally presses "View bookings" instead of "View statistics"
They can just press the home button to go back to the home page and then press "View statistics" as intended

/Key/

* ¹ - Basic flow is the typical use of a user to perform the task
* ² - Alternative flow, a route that is different to the basic flow, but still reaches the same result
* ³ - Exceptional flows are where the user takes a route through the application that is not intended.

See attached use-case diagrams (x3).

//OO Design and UML//

/System Architecture/

See attached image. The system diagram shown gives a basic representation of how the system will be built whilst leaving out more complex parts of the system such as Single-Sign-On and API's. In essence the user will connect to a web server via their browser which communicates to an app server which is connected to our Oracle database. The basic system has the following external dependencies:
- API to link frontend and backend (likely to be developed in house)
- Single-Sign-On System
- Eventual integration to Bristol University server systems.

The web server component transfers information between the client side and the rest of the system. The app server deals with all the functionality of the system, whilst the database will hold all of the system's records and information.

We have decided to model the user booking mechanics in UML. This is because this is the most crucial part of the system, and we knew the least about how we were going to go about implementing it. We decided to do both active and static UML diagrams, and we learnt a lot from it, especially  the class diagram and collaboration diagram from which we now have a good understanding of the system structure and flow. For all of the following explanations, see the attached images for context.

/Collaboration Diagram/

The collaboration diagram aims to show how objects interact with each other through methods, and to define some sort of system flow through a critical part of the system. Here we are modelling the flow of a user object being created, and being used to create booking objects that then deal with the actual process of managing bookings in the SQL database via several methods.

There are three potential paths that could be taken once the user object has been created; make a new booking, edit an existing booking or delete a booking:

Make a new booking:
1. user.create_booking() is called to create a new booking object. This is filled with the user defined parameters, sourced from an input form on the front end. These input parameters become the attributes of the booking object.
2. booking.make_booking() is called, with its input parameters being the attributes of the object. This method queries the database to add a new booking entry into it.
3. booking.send_email() is called which sends a confirmation email to the user's email address.
4. The booking object nullifies itself.


Edit a Booking:
1. user.edit_booking() is called to create a new booking object. The input parameters are given by a user selection. These input parameters become the attributes of the booking object.
2. booking.edit_booking() is called, with its input parameters being the attributes of the object. This method queries the database to update an entry.
3. booking.send_email() is called which sends a confirmation email to the user's email address.
4. The booking object nullifies itself.

Delete a booking:
1. user.delete_booking() is called, with input given by the user. This queries the database and deletes the corresponding entry.

/Activity Diagram/

The activity diagram first starts with the user logging into the system through the imported UoB ‘Single Sign On’ integration. From here, the user is taken to the main menu where they can then choose whether they would like to make a new booking or view their existing bookings. 

Make a booking:
Once this option is selected, the system accesses the database and fetches all the bookings from the database (from all userIDs) and displays it in a timetable type format. The user can then select the specific timetable slot for which he would like to place a booking in. If it is free, the user will then be taken to a page to confirm his booking. Once the user has confirmed the booking, the system will instantiate a new booking object and set all the attributes appropriately. This booking object is then used to insert a new record into the Booking table within the database. An email is then sent once the booking has been saved, and the user is redirected to the main menu.

Viewing a booking:
Once this option is selected, the system accesses the database and fetches all the bookings of the user’s unique userID from the database. The constructor from the booking class is called (for each booking the user has) and a new booking is instantiated with the details from that specific record in the database. Each object is then displayed within a table (with their associated rooms shown), of which the user can select a booking they would like to delete. If the user selects to delete a booking, the booking.deleteBooking method is called and the system once again accesses the database and deletes the related record from the Booking table. An email is then sent to confirm the deletion, and the user is redirected to the main page.

View room functionality:
This view room function is used twice in the system (as shown in the flowchart). It is called when: 1) The user wants to make a new booking, and the timetable for each room is shown. 2) The user wants to view their bookings, and a list of the bookings and associated rooms are shown. This view room function works by fetching the specific room information from the Rooms table within the database. This fetched data is then formatted onto a webpage and shown to the user. 

/Class Diagram/

The object-class diagram is a type of static UML diagram used to display the structure of our system by outlining the classes, their methods and attributes and the relationships between each of the classes.

db class - This is our database class and is used to store the current instance of our database as well as the methods which allow us to query the database. The classes ‘user’ and ‘booking’ inherit from this class.
user class - This is the user class and is used to store all the data relating to the currently logged in user.

admin / student class – Each logged in user will either be a student or an admin, so two classes have been setup that inherit from the user class which classifies the current user as a student/admin.

booking class – The booking class inherits from ‘db’ and has a composition aggregation relationship with ‘student’ and ‘admin’. This implies a relationship where the child class (booking) cannot exist without the parent class existing (admin / student). If the admin / student class were to be deleted, the booking class would cease to exist. 

/Use-Case Diagram/

The Use Case Diagram is a dynamic UML model aimed to show the relationships between actors and use cases within a system. In this case the primary actors; those being Student and Admin, can call on the system for each of their use case, with secondary actors; being Single Sign-On or the Server responding to the request and the system being the Study Space Booking App. The centre System rectangle contains only use cases, where Log in, Make a booking, View room information, View booking, Edit a booking and Delete a booking are all use cases from the primary actors. Verify log in and Send confirmation email are secondary use cases to react to the primary use cases used for the secondary actors.

The Log in use case is the first use case called from either of the primary actors as it will be required to perform any of the other use cases. Verify log in is included as it’s a necessary use case for Single Sign-On to perform Log in.

Make a booking and View bookings don't follow an order and either can be called from either primary actors. View room information is extended from Make a booking so room information can be checked before booking. Edit a booking is extended from View bookings, Delete a booking is extended from Edit a booking. So to Edit a booking, a primary actor has to View bookings then Edit a booking. To Delete a booking, a primary actor has to View bookings, then Edit a booking and then Delete a booking. Send confirmation email is a use case for Server which is used every time a request to Make a booking, Edit a booking or Delete a booking is made.


//Development Testing//

The Java application server backend which deals with processing input, communicating between the web server and database server, will be tested using JUnit. There will be assertions on each function, checking the correct output is given for a specified input. For the Javascript front end, we will be using a Javascript testing framework such as Mocha. We will also be providing continuous iterations of the front end to the client, so that improvement and alterations can easily be made. Part of our JUnit tests will check that the SQL queries are correct. We will also use DbUnit for the Database, a JUnit extension to ensure that the database is correct.

/Possible Testing frameworks/

JUnit
DbUnit
Mocha

/Challenges/

The API will be difficult to test because it will be developed by us, rather than coming from an external source. To overcome this we will create our own, thorough tests. Often the front end can be hard to test, but using a framework such as mocha will enable us to ensure that there are no issues such as browser incompatibility. The database will be difficult to test without real-world data. To overcome this we will do our best to populate it with thorough data during the testing phase.

/An example test/

Below is a first draft of the tests we will implement on our preliminary idea of the function that will handle a user creating a booking. The actual function itself may be altered as we progress with the application, hence the tests will need to be altered to match.

The very focus of the application should be on a user creating a new booking, as this is what will be used most. This would be handled by the ***User.createBooking()*** method, which would take in the following parameters:
***roomID*** : a unique integer that corresponds to the room to be booked
***dateTime*** : the date and start time of the booking to be made
***noUsers*** : an integer to represent the number of students using the room at a time
***length*** : An integer to represent the number of hours for the booking
***userID*** : a unique integer that corresponds to the logged in user that is making the booking

***User.createBooking()*** would create a new Booking object with properties corresponding to the parameters above, using ***Booking.construct()*** which takes in the same parameters. An additional property is ***bookingID*** : which is a unique integer that identifies that booking. ***Booking.makeBooking()*** is then called, which creates and uses an SQL statement that inserts a new booking into the booking table with all of the Booking object's parameters. Find attached an image detailing the tests.

