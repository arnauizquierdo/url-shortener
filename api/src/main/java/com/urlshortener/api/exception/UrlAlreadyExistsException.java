package com.urlshortener.api.exception;

public class UrlAlreadyExistsException extends UrlShortenerException {
    public UrlAlreadyExistsException(String originalUrl) {
        super("URL already exists: " + originalUrl);
    }
}
