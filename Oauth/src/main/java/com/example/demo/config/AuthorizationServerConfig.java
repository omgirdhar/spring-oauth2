package com.example.demo.config;


import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.helper.KeyPairGeneratorUtils;
import com.example.demo.repo.CustomRegisteredClientRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {

    @Autowired
    private DataSource dataSource;

    // ✅ REQUIRED: JWK (without this server won't start)
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (selector, context) -> selector.select(jwkSet);
    }

    private RSAKey generateRsa() {
        KeyPair keyPair = KeyPairGeneratorUtils.generateRsaKey();

        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new CustomRegisteredClientRepository();
    }
    
//    // ✅ KEEP your JDBC (but requires DB tables)
//    @Bean
//	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
//	    JdbcRegisteredClientRepository repo = new JdbcRegisteredClientRepository(jdbcTemplate);
//
//	    RegisteredClient client = RegisteredClient.withId("1")
//	        .clientId("client")
//	        .clientSecret("secret")
//	        .clientName("Test Client")
//	        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//	        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//	        .scope("read")
//	        .clientSettings(
//	            ClientSettings.builder()
//	                .requireProofKey(false)
//	                .build())
//	        .tokenSettings(
//	            TokenSettings.builder()
//	                .accessTokenTimeToLive(Duration.ofSeconds(300))
//	                .build())
//	        .build();
//
//	    repo.save(client);
//
//	    return repo;
//	}
    
    @Bean
    public OAuth2AuthorizationService authorizationService(RegisteredClientRepository clients) {
        return new JdbcOAuth2AuthorizationService(new JdbcTemplate(dataSource), clients);
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(RegisteredClientRepository clients) {
        return new JdbcOAuth2AuthorizationConsentService(new JdbcTemplate(dataSource), clients);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8080")
                .build();
    }

    // ✅ CRITICAL: ORDER(1)
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
            .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
            .oidc(Customizer.withDefaults()); // ✅ enable OpenID Connect

//        http.userDetailsService(userDetailsService);

        return http.build();
    }
}