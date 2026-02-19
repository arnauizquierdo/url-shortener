package com.urlshortener.api.exception;

public class UserNotFoundException extends UrlShortenerException {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}