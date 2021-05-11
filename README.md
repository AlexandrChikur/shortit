![logotype][logo]
________________
#### _Shortit is a web application for shortening links using the [Spring Boot](https://spring.io/projects/spring-boot) framework._
________________

#Project requirements:
- Docker ^20.10.5
- npm ^6.14.10

## 1.How to run an application using [Docker](https://www.docker.com/)
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

## 2.How can you see the UI?
After you started the backend you gotta start frontend.\
First, move to frontend root directory.
```
cd src/frontend #Linux
```
or
```
cd src\frontend #Windows
```
And, finally setup frontend dependencies and run the UI:
```
npm install
npm run serve
```

<!-- Links -->

[logo]: https://i.imgur.com/vScFb96.png