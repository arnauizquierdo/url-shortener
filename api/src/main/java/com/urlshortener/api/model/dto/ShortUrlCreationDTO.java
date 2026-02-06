package com.urlshortener.api.model.dto;

public class ShortUrlCreationDTO { // create new shortUrl

    private String originalUrl;

    public ShortUrlCreationDTO() {}

    public ShortUrlCreationDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    
}
