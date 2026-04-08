//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ResourceServerConfig {
//
//    @Bean
//    @Order(3) // must be after auth server (1) and default security (2)
//    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .securityMatcher("/hello/**") // protect /hello
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated()
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(Customizer.withDefaults()) // validate JWTs issued by Auth Server
//            );
//
//        return http.build();
//    }
//}