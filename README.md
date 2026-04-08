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
- MySQL (configurable datasource)
- Java 17+

---

## OAuth2 Database Schema (MySQL)

The following tables **must be created in database** which are used for OAuth2 authorization and client management in this project. These were created using MySQL.

### oauth2_authorization
```sql
CREATE TABLE `oauth2_authorization` (
  `id` varchar(100) NOT NULL,
  `registered_client_id` varchar(100) DEFAULT NULL,
  `principal_name` varchar(200) DEFAULT NULL,
  `authorization_grant_type` varchar(100) DEFAULT NULL,
  `attributes` text,
  `state` varchar(500) DEFAULT NULL,
  `authorization_code_value` text,
  `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
  `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
  `authorization_code_metadata` text,
  `access_token_value` text,
  `access_token_issued_at` timestamp NULL DEFAULT NULL,
  `access_token_expires_at` timestamp NULL DEFAULT NULL,
  `access_token_metadata` text,
  `access_token_type` varchar(100) DEFAULT NULL,
  `access_token_scopes` varchar(1000) DEFAULT NULL,
  `refresh_token_value` text,
  `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
  `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
  `refresh_token_metadata` text,
  `oidc_id_token_value` text,
  `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_metadata` text,
  `user_code_value` text,
  `user_code_issued_at` timestamp NULL DEFAULT NULL,
  `user_code_expires_at` timestamp NULL DEFAULT NULL,
  `user_code_metadata` text,
  `device_code_value` text,
  `device_code_issued_at` timestamp NULL DEFAULT NULL,
  `device_code_expires_at` timestamp NULL DEFAULT NULL,
  `device_code_metadata` text,
  `authorized_scopes` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `oauth2_authorization_consent` (
  `registered_client_id` varchar(100) NOT NULL,
  `principal_name` varchar(200) NOT NULL,
  `authorities` text,
  PRIMARY KEY (`registered_client_id`,`principal_name`)
);

CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) NOT NULL,
  `client_id` varchar(100) NOT NULL,
  `client_id_issued_at` timestamp NULL DEFAULT NULL,
  `client_secret` text,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) NOT NULL,
  `client_authentication_methods` text NOT NULL,
  `authorization_grant_types` text NOT NULL,
  `redirect_uris` text,
  `post_logout_redirect_uris` text,
  `scopes` text,
  `client_settings` text NOT NULL,
  `token_settings` text NOT NULL,
  PRIMARY KEY (`id`)
);
