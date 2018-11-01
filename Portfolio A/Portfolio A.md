# Portfolio A
## Overview
The team’s assignment is to create a study space booking application for use by engineering students at the University of Bristol to book study spaces in the Queen’s building.

### Client
The client is the University of Bristol. More specifically, we will be working for the Faculty of Engineering, with our primary point of contact being Rahim Ahmed who is the Faculty of Engineering office manager. We will also be liaising with the IT team, both at a faculty level and a university level.

### Application Domain
The sector we are working in is the educational sector. Despite this, the team is treating this very much in the same way that you would treat any industry client.

### Key Reasons for the Product
Currently the Faculty of Engineering admin and library staff use crude methods for booking study spaces - google docs etc. On top of this, students must go in person to the library desk in order to book study spaces. The client is keen for us to create a solution that streamlines and automates the process, with students being able to book remotely, and admin staff only being needed to manage booking in exceptional circumstances. Other features and points to note:
* The product needs to be expandable to other study spaces outside of the Queen’s building in the future.
* The product is intended to be exclusively used by engineering undergraduates, with restrictions in place so that other university students cannot book study spaces in the Queen’s building.
* The product should integrate the single sign on system.
* Limits to how often and for how long students can book study spaces should be included in the product.
* There should be a separate admin page with admin functions.

### Vision of Product
The team hope to achieve an elegant and attractive solution to the client brief that is user friendly and aesthetically pleasing on the front end, and expandable, efficient and intuitive on the backend.

The front end will be a HTML, JavaScript and CSS based web-app with multiple pages and an admin page. This will be accessible from the University of Bristol website, and will allow all the functions that the client briefed us on.

The backend system will be housed on the Oracle cloud server systems. Here we will be using Java as our backend language, with SQL being our language for database integration. The database will be integrated with mySQL, and we will be using a two server model (web server / applications server).

## Requirements

### Stakeholders
##### Rahim Ahmed - Faculty Office Manager for the Engineering Department
The space booking app was requested by Rahim, and he laid out his own specification. Although Rahim is not and end-user, he must approve of the application and is responsible for it in the University. His priorities are in student and admin ease of use.

##### UoB Engineering Undergrads
These students form one side of the main end users. They themselves will be using the app to book the room.

##### UoB Engineering Office Admins / Librarians
These are the other side of the end users. They will use the app to monitor and manage the bookings, aiding the students and the process.

##### Chris Totthill - IT Manager for the Engineering Department
Chris is in charge of implementation and purchase of new applications, and his approval is also needed.

##### University IT and Maintenance Teams
They will be in charge of maintaining and updating the application post release. They will also deal with students needing help.

### High Level Use cases
User:
* Student -> view available rooms -> book room -> go to room
* Student -> view current bookings -> go to room OR -> cancel room
* Admin -> view statistics
* Admin -> view available rooms -> book room for student
* Admin -> view current bookings -> cancel booking
