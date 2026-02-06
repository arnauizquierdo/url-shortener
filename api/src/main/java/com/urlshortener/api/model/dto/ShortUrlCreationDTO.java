package com.urlshortener.api.model.dto;

public class ShortUrlCreationDTO { // create new shortUrl

    private String originalUrl;

    public ShortUrlCreationDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getoriginalUrl() {
        return originalUrl;
    }
    public void setoriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    
}
