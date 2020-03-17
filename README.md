# RadekRates Application</h1>

<p align="center">
  <img width="180" height="180" src="https://zapodaj.net/images/b3c5b34fde616.jpg">
</p>

**This is the backend side of the application**  
*To check out the frontend side of the application click the link shown below:*  

[Radek Rates App Frontend Vaadin](https://github.com/radoslaw-lazur/radekRatesFrontVaadin)  
  
## Description

This app is a fake currency exchange web application. 
The applications allows to sign in, save fake IBAN numbers and finnally create currency echanges.
After signing in to the application you can expect activation e-mail.
If the user is not activated there is no possibility to log in.
If the user forgets the password there is a way to get it back with a requested e-mail.
After creating the transaction we can expect only the e-mail showing the orer.
There is no connection to another API regarding the payment activities.
Pleas note the transaction is not real and the app does not store any sensitive information.
The provided IBAN numbers are with wrong dimension. 
Below you can find the expected e-mail regarding the requested order:  
[Sample Transaction E-mail outlook](https://zapodaj.net/images/4cd9d4dc3351a.png)  
The App is connected with APIs: [Fixer Currency API](https://fixer.io/) and [OpenWeather API](https://openweathermap.org/)  
The app can handle the scheduled Email regarding the current currency rates and the simple weather forecast data. 

All expected E-mails you can check in the link shown below:  

[Samle E-mails](https://drive.google.com/drive/folders/1sqPugOzT309ssavN9kkeoHJs7UUYCZeu?usp=sharing)

## Tech Description


This is a Java app uses Gradle build. The project has been made based on Spring Boot Framework (also contains Thymeleaf, Actuator, RestTemplate), Hibernate, database activities based on H2 Memory Database Engine. The app has got E-mail and password validation. 
The project is covered with tests using Junit, Mockito, and SpringBootTest. 
This project was created uning InteliJ Idea IDE.

## Start-up / Launch

To start-up the application clone this repository. When cloned build the project using 'gradlew build' terminal command. 
Make sure if the H2 driver is imported in the application.properties. 
When the project is build, run the project.
To operate the app there is a need to save the seed AdminRatio with Post command using Insomnia or other REST app. 
The AdminRatio is needed to created the transactions based on currency choice. Every ratio regarding the puchased and sold currency can be be set seperatly. 
**To do so, please copy the JSON into you POST request as shown below:** 

[Admin Ratio Json](https://drive.google.com/file/d/12dx1tffOBkHcF6p-1f69PbEqP4L7Ok-W/view?usp=sharing)

Below you can find also starting package you can import to your REST app as Insomnia etc.

[Import HTTP requests to Insomnia](https://drive.google.com/drive/folders/1enXQa5ovWKFmjkGJBqzuRmqBPypgym2W?usp=sharing)

[Import HTTP request to Postman](https://www.getpostman.com/collections/d99444aae4a04e7ef4fb)

The app starts on localhost:8080 - Tomcat

I invite you to see the frontend side of this app here: 

[Radek Rates App Frontend Vaadin](https://github.com/radoslaw-lazur/radekRatesFrontVaadin)  

If you have any questions please do not hesitate to ask: radoslaw.lazur@gmail.com







