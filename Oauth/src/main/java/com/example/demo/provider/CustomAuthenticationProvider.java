//package com.example.demo.provider;
//
//import java.util.List;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if ("user".equals(username) && "password".equals(password)) {
//            return new UsernamePasswordAuthenticationToken(
//                    username,
//                    password,
//                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//        }
//        throw new BadCredentialsException("Invalid credentials");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}