package com.urlshortener.api.model.dto;

import java.time.LocalDateTime;

public class ShortUrlDTO { // show shortUrl

    private String originalUrl;
    private String shortUrl;
    private LocalDateTime timestamp;
    private Long accessCount;
    
    public ShortUrlDTO(String originalUrl, String shortUrl, LocalDateTime timestamp, Long accessCount) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.timestamp = timestamp;
        this.accessCount = accessCount;
    }

    public String getoriginalUrl() {
        return originalUrl;
    }
    public void setoriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public String getShortUrl() {
        return shortUrl;
    }
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Long getAccessCount() {
        return accessCount;
    }
    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    

}
