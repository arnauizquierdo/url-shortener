package com.urlshortener.api.exception;

public class UserAlreadyExistsException extends UrlShortenerException {
    public UserAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}
