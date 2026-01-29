# To-Do List Web Application

A simple Java-based web application that provides user authentication and personal to-do list management. The project is built using **Jakarta Servlet**, **JSP**, **Apache Tomcat**, and **MySQL**, and is fully containerized with **Docker** and **Docker Compose** for easy setup and deployment.

This project was developed as part of ICCS 370 Software System Construction course at Mahidol University International College

---

## Author

**Nathanon C.**
Computer Science Student

This project was my first web application, created for learning purposes to demonstrate backend Java web development and containerization skills.

---

## Features

* User registration with password hashing (BCrypt)
* User login and logout
* Create, read, update, and delete (CRUD) to-do items
* Toggle to-do completion status
* Each user has an isolated to-do list
* Connection pooling with HikariCP
* Persistent MySQL database using Docker volumes
* One-command startup using Docker Compose

---

## Tech Stack

**Backend**

* Java 21
* Jakarta Servlet API
* JSP & JSTL
* Apache Tomcat 10
* JDBC + HikariCP

**Database**

* MySQL 8

**DevOps / Tooling**

* Docker
* Docker Compose
* Maven

---

## Project Structure

```
.
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── dev/taro/webapp
│       │       ├── controller
│       │       ├── model
│       │       ├── repository
|       |       └── service
│       └── webapp
│           ├── WEB-INF
│           └── *.jsp
└── target
    └── WebApp-1.0-SNAPSHOT.war
```

---

## Getting Started

### Prerequisites

* Docker
* Docker Compose

---

### Run the Application

From the project root directory:

```bash
mvn clean package # Generates target/WebApp-1.0-SNAPSHOT.war
docker-compose up --build # Builds images and starts containers
```

Once started:

* Web app: [http://localhost:8080](http://localhost:8080)
* MySQL runs internally on port `3306`

---

## Environment Variables

| Variable         | Description                |
| ---------------- | -------------------------- |
| USER_DB_URL      | JDBC URL for user database |
| USER_DB_USERNAME | Database username          |
| USER_DB_PASSWORD | Database password          |
| TODO_DB_URL      | JDBC URL for todo database |
| TODO_DB_USERNAME | Database username          |
| TODO_DB_PASSWORD | Database password          |

---

## Notes

* The application is packaged as a WAR file and deployed to Tomcat
* MySQL Connector/J is bundled inside the WAR
* Database connections are managed using HikariCP for efficiency

---

## Future Improvements

* Pagination for large to-do lists
* REST API + frontend framework
* Session timeout configuration
* Unit and integration tests

---
