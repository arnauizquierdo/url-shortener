package com.urlshortener.api.exception;

public class UrlNotFoundException extends UrlShortenerException {
    public UrlNotFoundException(String shortCode) {
        super("URL not found: " + shortCode);
    }
}
