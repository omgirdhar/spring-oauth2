# Spring Boot OAuth2 Authorization Server

This project demonstrates a **Spring Boot Authorization Server** using **Spring Security 6** and **Spring Authorization Server**.  
It implements OAuth2 and OpenID Connect with a custom `UserDetailsService`.

---

## Features

- OAuth2 Authorization Server
- OpenID Connect support
- JWT token generation with RSA key
- Custom `UserDetailsService` backed by a database
- JDBC persistence for authorization and client details (optional)
- Secured endpoints with Spring Security

---

## Technologies Used

- Spring Boot 3.x
- Spring Security 6.x
- Spring Authorization Server
- Spring Data JPA
- Maven
- H2 / MySQL (configurable datasource)
- Java 17+
