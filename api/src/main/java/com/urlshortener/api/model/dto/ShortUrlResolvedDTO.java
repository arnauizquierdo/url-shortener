package com.urlshortener.api.model.dto;

public class ShortUrlResolvedDTO {

    private String originalUrl;

    public ShortUrlResolvedDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
