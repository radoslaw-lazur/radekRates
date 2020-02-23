package com.radekrates.service.generators;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UniqueStringChainGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SECURE_TOKEN_LENGTH = 50;
    private static char[] symbols = CHARACTERS.toCharArray();

    public String createUniqueStringChain() {
        SecureRandom random = new SecureRandom();
        char[] buf = new char[SECURE_TOKEN_LENGTH];
        for (int i = 0; i < buf.length; ++i) {
           buf[i] = symbols[random.nextInt(symbols.length)];
        }
       return new String(buf);
    }
}
