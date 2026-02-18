How to run locally: 

In order to run the site RateCS locally please follow the steps below

(1) Ensure you have a copy of the code cloned onto your PC locally 

(2) Open src --> main --> demo and run DemoApplication.java

(3) Then go onto your desired browser preferably chrome and search http://localhost:8080/

How to deploy:

(1) Head to render.com

(2) Create a webservice that will run Docker and link a github repo that contains the site files

(3) Create a PostgreSQL db and then modify application.properties with the desired database information 

(4) If you have everything done right and your site is set to track main branch and you are merged with the correct verison and working DB then it should run.

Acknolwedmgenets:

Used ChatGBT to debug issues within my file and issues with missing dependcies 

Used ChatGBT to create the html for delete.html the shortest amount
Used ChatGBT to help me understand how to create endpoints and just the general idea of using a MVC where we dont write SQL ourselves but instead write the code needed within a medium(controller)

FutureWork/Known Isssues:

I spent alot of time on design and UI so I didnt realize that I had to create persistance test, so in the future would have loved to created them to test endpoint.

Also am missing server side validation and the desired test for that but did client side valiations atleast. However, I did do some debugging used pgAdmin sort of as test where I opened the server and manually viewed the tables every time I wanted to test a feature to see if my POST request are going through and if my delete feature was work as intended 


Apologies for any grammar mistakes I was in a rush and unfortunately realized I needed to create test a little too late. 



