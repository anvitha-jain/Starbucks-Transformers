## Starbucks

Final project for Starbucks by Team Transformers.


* Created Spring Boot Maven Project Application for building Rest API's using MySQL and JPA
* Used MVC design pattern to create API's
* Created Android Aplication for UI which is displaying result for all API's


**CI/CD Deployment Architecture**
---

* Implemented Continuous Integration and Continuous Deployment of Application on EC2 instance.
* Implemented Autoscalling of the EC2 instance.

![archi](https://user-images.githubusercontent.com/42703827/57346097-2704a580-7102-11e9-8790-38c26d079399.png)


## Feature Set 

Authentication API </br> 
Sign Up API </br>
Get User Details API </br>
Add Cards API </br> 
Reload Card API </br> 
View Card Details API </br>
Manage Order API </br> 
Payment API </br> 
Transactions Summary API </br>
User Profile API </br> 
</br> 
<b> Extra </b> </br> 

Android Mobile Application </br> 
Automated Cloud Deployment via CI/CD pipeline which consists of Jenkins.


## Design Decisions 



This project uses Model-View-Controller design. The design was made keeping in mind reusability, de-coupling of components and maintaining a large size application. </br>

- For the ease of use, instead of displaying the API response via Postman, we have implemented an interactive Android application. </br>

- For the ease of on-going maintainability and deployments, the deployment pipeline on this application is completely automated via Jenkins.

 
**List of REST APIs**	
---
  	
   
   
* Sign up API
* Authentication/Login API
* Getting user profile API
* Add Card API
* Reload Card Balance API
* Get all cards for a user API
* Manage Order API
* Payment API
* Get all transactions for a user API

**Architecture:**
---



![Draft_Architecture](https://user-images.githubusercontent.com/44592616/57203492-5df18480-6f65-11e9-83c0-e96e9c6ed27f.png)




## Sprint Task Sheet and Story Board Links

[Sprint Task Sheet and Burndown Chart ](https://docs.google.com/spreadsheets/d/15Wpj40Am4bEe3XgMnDBChcCjzgIvM10R8c90u-sdjQk/edit?usp=sharing)


<img width="1046" alt="Burndown_Chart" src="https://user-images.githubusercontent.com/44592616/57564067-fc615980-735a-11e9-8ec7-dc3b26367f69.png">

[Story Board/ Task board ](https://github.com/amruthasingh/Starbucks-Transformers/projects/1)

Screenshot: 

![storyboard](https://user-images.githubusercontent.com/42703827/57564071-05522b00-735b-11e9-987d-6f0c182104c9.png)

[Project Journal ](https://github.com/amruthasingh/Starbucks-Transformers/tree/master/Sprint%20Reviews)

[Weekly Sprints for individuals and Individual XP Core Vlues](https://docs.google.com/spreadsheets/d/1lKzMqVtVFNgS5SjS00B9Lt-FgD2lddVnmd6EFCSYBfs/edit?usp=sharing)

Screenshot: 

![weekly_scrum_report](https://user-images.githubusercontent.com/42703827/57564070-01260d80-735b-11e9-9ad9-981c93eec2ad.png)

## Deployment Architecture Diagram



![archi (1)](https://user-images.githubusercontent.com/42703827/57350591-828b5f00-7113-11e9-966a-0e7ce56301bf.png)


## Component Diagram

![archi (3)](https://user-images.githubusercontent.com/42703827/57556868-14ba7f80-732d-11e9-9282-8f636df9f5b2.png)

## UI Wireframe

![UI-Wireframe (1)](https://user-images.githubusercontent.com/44592616/57556992-619e5600-732d-11e9-844e-aeb0a8758bc9.png)



 ## XP Core Values

* Communication: Everyone in the team were meeting on a daily basis to discuss the tasks that each one is working on and challenges, if there were any. We discussed the challenges faced by each member in the team together and came up with solutions. For instance, to finalize design pattern to be used for the project, the team sat together and discussed the best strategy with is apt for the requirements.

* Simplicity: As a team we sat together and discussed to keep the project as simplistic as possible. The approach, the strategy for on-going maintenance were all well thought about and made simple.

* Feedback: Everyone in the team would discuss the tasks each one is working, on a daily basis and listen to each tasks carefully and would give feedback if the approach each one is following is correct.  

* Courage: Each one in the team was extremely honest about the tasks and the state each of the assigned tasks were at. Everyone in the team adapted to the on-going discussions and changes very effectively. 

* Respect: Each one in the team had mutually respect for each other. Everyone were treated equal and there was a sense of responsibility in every member to complete their assigned tasks. Everyone in the team was valued.


## Github Contributions

![contribute](https://user-images.githubusercontent.com/42703827/57564157-3c750c00-735c-11e9-89b4-74bee22c43c6.png)

**Team Members: Contributions**
---

* Application Design and Setup: Team
* Code Reviews: Team
* EC2, Auto Scale Group, ELB Deployment: Amrutha Singh, Anvitha Jain
* Android Application: Poorva Agarwal, Snehal Yeole
* Authentication/Login API: Anvitha Jain
* Sign Up API: Anvitha Jain
* Manual Testing Login and Sign up APIs: Anvitha Jain
* Add Card API: Amrutha Singh Balaji Singh 
* Reload Card API: Amrutha Singh Balaji Singh 
* Manual Testing AddCard and ReloadCard APIs: Amrutha Singh Balaji Singh 
* Cloud EC2 Cluster Setup: Amrutha Singh Balaji Singh 
* Cloud Jenkins and ELB Setup: Amrutha Singh Balaji Singh
* User Profile API: Snehal Yeole
* Get Card Details API: Snehal Yeole
* Manual Testing Card Flows: Snehal Yeole
* Make Payment and Update Card API: Poorva Agarwal
* Get Transaction Details of user API: Poorva Agarwal
* Get all Transactions API: Poorva Agarwal
* Manual Testing of Payment Flows: Poorva Agarwal
* RDS setup: Snehal Yeole








