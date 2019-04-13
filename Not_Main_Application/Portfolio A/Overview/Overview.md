<h1>Overview</h1>

The team’s assignment was to create a study space booking website and web application for use by engineering students at the University of Bristol to book study spaces in the Queen’s building.

<h3>Client</h3>

<p>The client is the University of Bristol. More specifically, we have been working for for the Faculty of Engineering, with our primary point of contact being Rahim Ahmed who is the Faculty of Engineering office manager. We also liaised with the IT team, both at a faculty level and a university level.</p>

<p>For some background context, the University of Bristol is currently seeking to improve and simplify accessibility and use of services such as a booking system for designated study spaces. This is in addition to other technology upgrades the university is working on in 2019, such as a new cloud library system  and a migration from it's current Google partnership to Microsoft services such as Outlook.</p>

<h3>Application Domain</h3>

<p>The sector we are working in is the educational sector, which is a sector with many opportunities to enhance day to day administration and student activities via technological additions and improvements. An article from ICEF Monitor (http://monitor.icef.com/2016/02/mapping-technological-change-in-higher-education-through-2020/) reiterates this view, and adds that by 2020 universities will have to have made substantial changes to their technology both inside and outside the classroom in order to keep up with the pace of improvement in the educational sector. The University of Bristol is clearly being proactive in this regard and our project is one example of that.</p>

<h3>Key Reasons for the Product</h3>

<p>Currently the Faculty of Engineering admin and library staff use crude methods for booking study spaces - google docs etc. On top of this, students must go in person to the library desk in order to book study spaces. The client was keen for us to create a solution that streamlines and automates the process, with students being able to book remotely, and admin staff only being needed to manage booking in exceptional circumstances. The client wanted this application to be expandable to other study spaces outside of Queen's for future integration, and as a result we created a flexible system where new rooms and buildings can be added easily. In addition, the client wanted there to be admin and statistical functions for use by staff, such as blacklisting and limiting overuse by individuals, which have also been implemented successfully.</p>

<h3>Implementation of Product</h3>

<p>We have created an elegant and attractive solution to the client brief that is user friendly and aesthetically pleasing on the front end, and expandable, efficient and intuitive on the backend. The front end is written in HTML with a Thymeleaf framework and CSS styling. The web-app has a easy to navigate layout, with student and admin specific pages. This is ready to be ported to the University of Bristol website, and allows for most of the functions that the client briefed us on. The exception to this is the integration of the Single-Sign-On system, as the University of Bristol IT team would not allow us access to SSO. Instead, we implemented our own login system with the intent that the University of Bristol will attach SSO as they see fit. The backend system is housed on the Oracle cloud server systems. Here we are using Java as our backend language as part of a Spring framework, with MySQL being our database management system. Finally, we incorporated CircleCI for continuous integration of the product in the development stage</p>
