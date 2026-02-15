package com.urlshortener.api.model.dto;

import java.time.LocalDateTime;

public record ShortUrlDTO(
    String originalUrl,
    String shortUrl,
    LocalDateTime timestamp,
    Long accessCount
) {}
