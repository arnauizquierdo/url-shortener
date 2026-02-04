package com.urlshortener.api.model.dto;

public class ShortUrlCreationDTO { // create new shortUrl

    private String originalUrl;
    private String shortUrl;

    public ShortUrlCreationDTO(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
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
