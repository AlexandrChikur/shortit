![logotype][logo]
________________
#### _Shortit is a web application for shortening links using the [Spring Boot](https://spring.io/projects/spring-boot) framework._
________________

##How to run an application using Docker
````
gradlew clean build
docker-compose up --build
````
If you want to run an application with your own database, you have to do the following steps: \
__Be aware that possible only with PostgreSQL (at least yet)__

First, you have to build an application: `gradlew clean build`
Then change `DB_HOST` and `DB_PORT` in `application.properties` file on your own properties:
````
├───main
│   ├───java
│   └───resources - application.properties destination
````
And finally, run database on `DB_HOST`:`DB_PORT` that you just changed. 


<!-- Links -->

[logo]: https://i.imgur.com/vScFb96.png