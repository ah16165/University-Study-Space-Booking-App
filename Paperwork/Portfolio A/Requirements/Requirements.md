# Requirements

## Stakeholders

Rahim Ahmed - Faculty Office Manager for the Engineering Department -
The space booking app was requested by Rahim, and he laid out his own specification. Although Rahim is not an end-user, he must approve of the application and is responsible for it in the University. His priorities are in student and admin ease of use.

UoB Engineering Undergraduates -
These students form one side of the main end users. They themselves will be using the app to book the room.

UoB Engineering Office Admins / Librarians -
These are the other side of the end users. They will use the app to monitor and manage the bookings, aiding the students and the process.

Chris Totthill - IT Manager for the Engineering Department -
Chris is in charge of implementation and purchase of new applications, and his approval is also needed.

University IT and Maintenance Teams -
They will be in charge of maintaining and updating the application post release. They will also deal with students needing help.

## High Level Use Case Diagrams

https://github.com/university-study-space-booking-app/University-Study-Space-Booking-App/blob/master/Paperwork/Portfolio%20A/Requirements/admin%20high%20level%20case%20diagram.png

https://github.com/university-study-space-booking-app/University-Study-Space-Booking-App/blob/master/Paperwork/Portfolio%20A/Requirements/student%20high%20level%20case%20diagram.png



## Flow Breakdown of Student making a booking then using a room

Basic Flow¹ -
* Student opens browser on phone/tablet/computer
* Student enters the URL for the Study Space Booking Application
* The student then clicks log in
* The student then enters their login details and logs into the system
* At the home screen, the student chooses to click the "Make a booking" button
* The student enters in their preferred date / time / duration and clicks the "Submit" button
* The student is presented with a grid displaying the available rooms that fit their entered criteria
* The student may select one of the rooms listed by clicking the "Select" button
* After reviewing the final details of the booking, the student can submit the booking by clicking the "Submit" button
* A confirmation page lets the user know that they have booked the room
* The student then goes to the room

Alternative Flow² -
* The student does not have access to a personal device
* The student approaches a librarian/admin and asks them to book a room for them
* The admin logs in to their admin account, and repeats the process as above but books for the student

Exceptional Flow³ -
* A student wishes to book a slot that another student has already booked
* By the requirements, this is not allowed and so the student must find another, not already booked slot
* A student accidentally booked the wrong room
* The student can cancel the room by viewing their bookings, and clicking cancel

## Flow Breakdown of Admin Viewing Statistics

Basic Flow¹ -
* Admin opens browser on phone/tablet/computer
* Admin enters the URL for the Study Space Booking Application
* The admin then clicks log in
* The admin then enters their login details and logs into the system
* At the admin home screen, the admin clicks the "View statistics" button
* The admin is then presented with a set of options to view the statistics
* After selecting the options they want, the relevant statistics are presented

Exceptional Flow³ -
* The admin accidentally presses "View bookings" instead of "View statistics"
* They can just press the home button to go back to the home page and then press "View statistics" as intended

Key

* ¹ - Basic flow is the typical use of a user to perform the task
* ² - Alternative flow, a route that is different to the basic flow, but still reaches the same result
* ³ - Exceptional flows are where the user takes a route through the application that is not intended.

## Functional Requirements

* To be able book one of many study space rooms in the Engineering department, the core of the project
* To log in with Single Sign On, fitting in with other University applications and increasing speed and ease of use for users
* To see the availability of rooms before booking, enabling users to plan ahead
* To allow the users to view the specifications of each study room
* For students to view their current bookings
* For students to be able to cancel their bookings
* For admins to view all bookings for management
* For admins to delete ANY booking they would like, regardless of user
* For fairness and wellbeing, 
* * students will only be able to book up to 4 hours per day (20 a week)
* * rooms may only be booked up to two weeks in advance
* All required information should be stored on a database
* The password of users should not be stored in plain text within the database
* Have a separate version for administrators, enabling them to manage the system
* There should be a page to show statistics about the use of the application, viewable by administrators only
* Administrators should be able to blacklist students
* Administrators should be able to delete students' accounts
* Students not from the Engineering department should not be allowed to book rooms
* Have a terms and conditions page, highlighting the current policies of the rooms
* Have wellbeing information
* There should be a grid or table to display availability of rooms on specific days
* Administrators should be able to book rooms for students
* Once a room has been booked at a time, it cannot be booked by other students unless it is cancelled
* To prevent unauthorized users from accessing the system

## Non-Functional Requirements

* The software must be accessible from phones / tablets / computers
* The time taken to log into the system and access the main menu should be 1 minute maximum
* All features of the program should be accessible from the main menu
* The web page, database and servers should all be secure - meaning that they are not susceptible to malicious injection or information exposure.
* Access to the application should be 24/7, with some allowance for updated and maintenance to the system itself
* Ease of use should be a priority. No manual or instructions should be required by students in order to operate the application. Administrators have extra features so may need minimal coaching but the application should be intuitive enough for most to understand without.
* Accessible and fair to all users, rooms shouldn't be booked up by the same people constantly
* The application and code should be easy to improve, extend and add features to. Code should be clean and well documented.
* The user interface should be aesthetically pleasing, to provide a positive experience so that users will want to use the app
* If the application is a success, other departments in the University may want the application to be extended to include them. The application should be able to scale up to accommodate this.
* The application should have a web user interface, and a server back-end
* Users should be able to access the application remotely, whether on the University network (Eduroam) or not.
