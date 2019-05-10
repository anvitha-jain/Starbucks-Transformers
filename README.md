## Starbucks

Final project for Starbucks by Team Transformers.


* Created Spring Boot Maven Project Application for building Rest API's using MySQL and JPA
* Used MVC design pattern to create API's
* Created Android Aplication for UI which is displaying result for all API's


**CI/CD Deployment**
---

* Implemented Continuous Integration and Continuous Deployment of Application on EC2 instance.
* Implemented Autoscalling of the EC2 instance.

![archi](https://user-images.githubusercontent.com/42703827/57346097-2704a580-7102-11e9-8790-38c26d079399.png)



## Feature Set 

Add Cards API </br> 
Manage Order API </br> 
Payment API </br> 
Authentication API </br> 
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
* Payment API
* Get all transactions for a user API

**Draft Architecture:**
---

![Draft_Architecture](https://user-images.githubusercontent.com/44592616/57203492-5df18480-6f65-11e9-83c0-e96e9c6ed27f.png)




## Sprint Task Sheet and Story Board Links

[Sprint Task Sheet ](https://docs.google.com/spreadsheets/d/15Wpj40Am4bEe3XgMnDBChcCjzgIvM10R8c90u-sdjQk/edit?usp=sharing)

[Story Board ](https://github.com/amruthasingh/Starbucks-Transformers/projects/1)


## Deployment Architecture Diagram

![archi (1)](https://user-images.githubusercontent.com/42703827/57350591-828b5f00-7113-11e9-966a-0e7ce56301bf.png)


 ## XP Core Values

* Communication: Everyone in the team were meeting on a daily basis to discuss the tasks each one is working on and challenges, if there were any. We discussed of the challenges together and came up with solutions. For instance, to finalize design pattern to be used for the project, the team sat together and discussed the best strategy with is apt for the requirements. 

* Simplicity: As a team we sat together and discussed to keep the project as simplistic as possible. The approach, the strategy for on-going maintenance were all well thought about and made simple.

* Feedback: Everyone in the team would discuss the tasks each one is working, on a daily basis and listen to each tasks carefully and would give feedback if the approach each one is following is correct.  

* Courage: Each one in the team was extremely honest about the tasks and the state each of the assigned tasks were at. Everyone in the team adapted to the on-going discussions and changes very effectively. 

* Respect: Each one in the team had mutually respect for each other. Everyone were treated equal and there was a sense of responsibility in every member to complete their assigned tasks. Everyone in the team was valued. 


**Team Member-Contributions**
---

* Application Design and Setup Poorva Agarwal
* Code Reviews Entire Team
* EC2, Auto Scale Group, ELB Deployment Amrutha Singh Anvitha Jain
* Android Application Poorva Agarwal, Snehal Yeole
* Authentication/Login API Anvitha Jain
* Sign Up API Anvitha Jain
* Manual Testing Login and Sign up APIs Anvitha Jain
* Add Card API Amrutha Singh
* Reload Card API Amrutha Singh
* Manual Testing AddCard and ReloadCard APIs Amrutha Singh
* User Profile API Snehal Yeole
* Get Card Details API Snehal Yeole
* Manual Testing Card Flows Snehal Yeole
* Make Payment and Update Card API Poorva Agarwal
* Get Transaction Details of user API Poorva Agarwal
* Get all Transactions API Poorva Agarwal
* Manual Testing of Payment Flows Poorva Agarwal
* RDS setup Snehal Yeole








