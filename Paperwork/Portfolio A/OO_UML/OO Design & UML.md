# OO Design & UML

## High-level Architecture Diagram

See image.

Above is a high-level architecture diagram of our web application. It provides you with an overview of our entire system, identifying the main components that are used in an easily understandable abstract format. The controllers are used to act as a gateway between our user and the system by communicating with the user’s browser and responding to HTTP requests. At a very high level, its main responsibilities are: 
* Handling HTTP requests and issuing responses
* Converts data sent within HTTP requests into a format accessible by the internal structure of the system
* Sends data to Model for processing
* Receives data from Model and forwards it to View for rendering
The service receives data from the controller and performs any logic / calculations that need to be done to it. It then calls methods within the repositorys layer to query the database and feeds the data back into the controller.

## Static UML Model

See image.

The object-class diagram is a type of static UML diagram used to display the structure of our system by outlining the classes, their methods and attributes and the relationships between each of the classes.

We decided to model the majority of the classes within our system so a clear overview of the whole program can be easily seen. The relationships between the controllers, services and repositorys are all displayed with empty diamond arrows; which represent an aggregation relationship between the classes. At a very high level, each of different sections of classes can be explained by the following:
* The controller’s responsibility is to handle the incoming request
* The service layer receives the data from the controller and performs any logic/calculations that need to be done to it
* The repository layer provides the methods used by the service layer in querying the database

The classes at the top of the diagram represent the models used in the system. Each model’s attributes and methods are clearly outlined, as well as the data type each use. The relationships between the models can be seen through the lines connecting the classes (User and Room both have one to many relationships with Booking). 

We decided to use a object-class diagram as our static UML model as we believed that this type of diagram was the best in terms of displaying the overall structure of the system. It is very obvious to see the attributes and methods used by a given class, and the relationships between all the classes is clear. Due to the complex nature of our program, it is very easy for people to get lost when viewing our system for the first time; therefore, having this diagram allows them to see a clear overview of exactly what is present within our code. It very clearly defines what IS IN the system. This diagram also helped tremendously in terms of ensuring a better alignment among the members of the team in terms of what the code is to look like. 

## Dynamic UML Model (Aspect of the system)

See image.

For the dynamic UML model, we decided to make a sequence diagram that models the “View Bookings” sequence. We chose to use a sequence diagram over others as it allows the user to visualise the interactions within a system in a clear and sequential way. We selected this feature of our system to model specifically as it contained a good balance between importance and complexity. First time viewers are able to easily get a gauge of the workflow of the application without being overwhelmed by the complexity of the whole system (only a limited number of classes & methods are used in achieving this “View Bookings” function relative to other functions). 

Reflecting on this diagram, it has helped us a lot in terms of visualising the logic of a sophisticated aspect of our system; being able to clearly follow the methods used allowed us to easily see how objects and components interact with each other to complete a process. This greatly helped members of the group that found it difficult to follow parts of the code. 


