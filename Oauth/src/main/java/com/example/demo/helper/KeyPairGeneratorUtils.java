package com.example.demo.helper;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public final class KeyPairGeneratorUtils {

    private KeyPairGeneratorUtils() {}

    public static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}