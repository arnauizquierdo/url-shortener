package com.urlshortener.api.model.dto;

public record TokenRequestDTO(
    String username,
    String password
) {}