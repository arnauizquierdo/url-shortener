package com.urlshortener.api.model.dto;

public class ShortUrlCreationDTO { // create new shortUrl

    private Long userId;
    private String originalUrl;
    private String shortUrl;

    public ShortUrlCreationDTO(Long userId, String originalUrl, String shortUrl) {
        this.userId = userId;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
}
