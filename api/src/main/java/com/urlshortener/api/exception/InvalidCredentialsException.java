package com.urlshortener.api.exception;

public class InvalidCredentialsException extends UrlShortenerException {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }
}
