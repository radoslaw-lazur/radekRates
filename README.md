# RadekRates Application</h1>

<p align="center">
  <img width="180" height="180" src="https://zapodaj.net/images/b3c5b34fde616.jpg">
</p>

**This is the backend side of the application**  
*To check out the frontend side of the application click the link shown below:*  

[Radek Rates App Frontend Vaadin](https://github.com/radoslaw-lazur/radekRatesFrontVaadin)  
  
## Description

This app is an exemplary currency exchange web application. 
The application a new user to sign in, save IBAN numbers and finally create currency exchanges.
After signing in to the application activation E-mail is sent.
Other functions are also provided by the applications e.g. 
if the user is not activated there is no possibility to log in; 
the user forgets the password there is a way to get it back with the use of requested e-mail.
After creating the transaction, the application sends order details via E-mail.

There is no connection to another API regarding the payment activities.
Please note that transactions are not real and they are created only to show how the application works. 
Thus, the app does not store any sensitive information.
Additionally, the provided IBAN numbers are with wrong dimension. 
Below you can find the expected E-mail with reference to the requested order:  

[Sample Transaction E-mail outlook](https://zapodaj.net/images/4cd9d4dc3351a.png)  

The App is connected with APIs: [Fixer Currency API](https://fixer.io/) and [OpenWeather API](https://openweathermap.org/)  
The app can handle the scheduled Email regarding the current currency rates and the simple weather forecast data. 

All expected E-mails you can check in the link shown down below:  

[Samle E-mails](https://drive.google.com/drive/folders/1sqPugOzT309ssavN9kkeoHJs7UUYCZeu?usp=sharing)

## Tech Description


This is a Java app which uses Gradle build. The project has been made based on Spring Boot Framework (including Thymeleaf, Actuator, RestTemplate), Hibernate, database activities based on H2 Memory Engine. The app has got E-mail and password validation. 
The project is covered with tests using Junit, Mockito, and SpringBootTest. 
This project was created using InteliJ Idea IDE.

## Start-up / Launch

To start-up the application the repository needs to be cloned. When cloned, build the project using 'gradlew build' terminal command. 
Make sure, if the H2 driver is imported in the application.properties file. 
When the project is built, run the app.
To operate the app there is a need to save the AdminRatio seed with Post command using Insomnia or other REST app. 
The AdminRatio is needed to create the transactions based on currency choice. Every ratio concerning the puchased and sold currency can be set separately. 
**To do so, please copy the JSON into you POST request as shown below:** 

[Admin Ratio Json](https://drive.google.com/file/d/12dx1tffOBkHcF6p-1f69PbEqP4L7Ok-W/view?usp=sharing)

Below you can find also starting package you can import to your REST app as Insomnia etc.

[Import HTTP requests to Insomnia](https://drive.google.com/drive/folders/1enXQa5ovWKFmjkGJBqzuRmqBPypgym2W?usp=sharing)

[Import HTTP requests to Postman](https://www.getpostman.com/collections/d99444aae4a04e7ef4fb)

The app starts on localhost:8080 - Tomcat

I invite you to see the frontend side of this app here: 

[Radek Rates App Frontend Vaadin](https://github.com/radoslaw-lazur/radekRatesFrontVaadin)  

If you have any questions please do not hesitate to ask: radoslaw.lazur@gmail.com
