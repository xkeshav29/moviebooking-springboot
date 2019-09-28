# Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial

Build Restful CRUD API for a simple Movie-Booking application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/xkeshav29/moviebooking-springboot.git
```

**2. Create Mysql database**
```bash
create database movie_app
create table cron(cron_name varchar(255) PRIMARY KEY, last_execution_time datetime);
insert into cron values('booking_status', now());
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/movie-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines CRUD APIs and others as listed the respective controllers.
    /actor
    /movie
    /theater
    /booking
    /show
    /payment

You can test them using postman or any other rest client.

