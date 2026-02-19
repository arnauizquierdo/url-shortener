package com.urlshortener.api.config;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.urlshortener.api.exception.InvalidCredentialsException;
import com.urlshortener.api.exception.UnauthorizedOperationException;
import com.urlshortener.api.exception.UrlAlreadyExistsException;
import com.urlshortener.api.exception.UrlNotFoundException;
import com.urlshortener.api.exception.UrlShortenerException;
import com.urlshortener.api.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials")); 
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Cannot perform this operation"));
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUrlNotFound(UrlNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "URL not found"));
    }

    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUrlAlreadyExists(UrlAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "URL already exists"));
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedOperation(UnauthorizedOperationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Not authorized to perform this operation"));
    }

    @ExceptionHandler(UrlShortenerException.class)
    public ResponseEntity<Map<String, String>> handleUrlShortenerException(UrlShortenerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Internal server error"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error"));
    }
}
