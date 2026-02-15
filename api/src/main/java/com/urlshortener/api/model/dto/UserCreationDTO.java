package com.urlshortener.api.model.dto;

public record UserCreationDTO( // create new user
    String username,
    String password
) {}
