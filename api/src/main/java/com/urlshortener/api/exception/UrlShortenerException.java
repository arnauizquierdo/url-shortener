package com.urlshortener.api.exception;

public abstract class UrlShortenerException extends RuntimeException {
    public UrlShortenerException(String message) {
        super(message);
    }
}
