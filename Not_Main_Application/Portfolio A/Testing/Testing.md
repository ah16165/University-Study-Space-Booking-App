<h1>Development Testing</h1>

<h3>Overview of Testing</h3>

<p>The Spring application is tested mostly using the testing framework JUnit. We chose this approach as JUnit seemed to be the most well documented testing framework for Spring applications, it was easy to use and had a really strong set of online resources and articles. Additionally, we supplemented this with a JUnit extension called DbUnit, which was used for database integration tests. We chose this way of testing database integration as we were already using JUnit, so to keep all the systems happy and talking to each other we kept the database testing to a related framework. A happy side effect was that it was as easy to use and as well documented as JUnit. We also used multiple test classes from the Spring framework, such as 'TestExecutionListeners' as these were used to directly test Spring components.We endeavoured to test all the critical sections of the backend while spanning all the different types of software development tests as shown in the sections below. </p>

<h4>Unit tests</h4>
<p>Our unit tests strived to test the low level areas of our source code, such as testing individual low level methods that don't span across systems. An example of this is in our 'CreateUserTest' test, where we check that the methods for creating a new user and storing them in the relative repository are all operating as intended.</p>

<h4>Integration tests</h4>
<p>The aim of our integration focussed tests were to check that different systems work correctly together. A prime example of this was in our 'DB_test', where we used the DBUnit framework to test that database integration and linking to the Spring repositories was all working fine.  </p>

<h4>Functional tests</h4>
<p>We incorporated functional tests that aimed to test that an action returned a desired result to the user or admin. A good example of this was in our 'StaticHTMLTests', where we checked that given a valid REST request, it returned the correct HTML page. NEED AN SQL TEST.</p>

<h4>End-to-end tests</h4>
<p>We also had some end to end tests which aimed to tests a replication of user behaviour, such as our 'AuthenticationTests' that checked that our login and security systems were correctly working when given a valid REST request. </p>

<h4>Other types of testing</h4>
<p>Areas of testing that we did not explicitly test with code were performance testing, acceptance testing and smoke testing. We did not implement these, as after research it seemed these were difficult to implement and out of our scope. Despite this, there were some aspects of these areas that we did test through manual user-end testing, such as acceptance testing via checking all the functionality of the system was working for a generic user.</p>

<h3>Challenges</h3>

*Spring hides a lot of what it is doing from the developer, so unless you have a really in depth knowledge of the Spring framework, or trawl through lots of documentation, it is sometimes hard to test aspects of what the Spring framework is doing behind the scenes. We addressed this by firstly trusting that Spring is robust and polished enough that it is reliable, and secondly we tested end to end features and integration features instead of directly unit testing Spring frameworks.
*Frontend testing proved a challenge as we were not sure what the best framework or method to do this with was. We overcame this by leaving it to manual testing as the application is relatively mall and can be tested easily. None-the-less we appreciate this is not ideal and is discussed in our evaluation. 
*Cross-browser compatibilities proved irritating, and we did not have a strong framework to test this sort of problem with. We again reverted to manual testing to find browser related bugs, which worked for this scale, but was not ideal.
*The way the Spring security module dealt with login systems proved difficult to test as it handled a lot of the process for you. We overcame this in our 'AutheticationTests' using a TestRestTemplate module's 'withBasicAuth' method to check if secured HTML pages were being properly handled by the login system.


<h3>Evaluation</h3>
<p>We believe that on the whole our testing strategy was good. However, we have identified three key areas on what we could have improved on if we had a larger team or more time:</p>
*More depth to tests as quite often we would tests a feature such as user creation, and assume that this would also work for say room creation as the processes are very similar. Stuff like this cannot always be assumed.
*More frontend testing and browser support testing, as leaving it to manual testing is not scalable to larger projects.
*More robust unit tests, testing more fundamental methods and classes.
