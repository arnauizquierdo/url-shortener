package com.urlshortener.api.exception;

public class UnauthorizedOperationException extends UrlShortenerException {
    public UnauthorizedOperationException() {
        super("Not authorized to perform this operation");
    }
}
