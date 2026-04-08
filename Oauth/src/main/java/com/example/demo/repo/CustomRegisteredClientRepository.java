package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

//@Component
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    @Autowired
    private UserRepository repo;

    @Override
    public RegisteredClient findByClientId(String clientId) {

        User user = repo.findByClientId(clientId);

        if (user == null) {
            return null;
        }

        return RegisteredClient.withId(user.getId().toString())
                .clientId(user.getClientId())
                .clientSecret(user.getClientSecret())
//                .clientSecret(passwordEncoder.encode(user.getClientSecret()))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("read")
                .build();
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Not needed");
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }
}