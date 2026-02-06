package com.urlshortener.api.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlCodeGenerator {
    
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    private static final int CODE_LENGTH = 6;
    
    private final SecureRandom random = new SecureRandom();
    
    public String generate() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return code.toString();
    }
    
}