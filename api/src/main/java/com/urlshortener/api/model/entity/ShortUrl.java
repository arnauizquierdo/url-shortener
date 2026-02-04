package com.urlshortener.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ShortUrl {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String origintalUrl;
    private String shortUrl;
    private LocalDateTime timestamp;
    private Long accessCount;

    protected ShortUrl() {}

    public ShortUrl(Long userId, String origintalUrl, String shortUrl) {
        this.userId = userId;
        this.origintalUrl = origintalUrl;
        this.shortUrl = shortUrl;
        this.timestamp = LocalDateTime.now();
        this.accessCount = 0L;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public Long getAccessCount() {
        return accessCount;
    }
    public String getOrigintalUrl() {
        return origintalUrl;
    }
    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public String toString() {
        return String.format(
            "ShortUrl[id=%d, userId='%d', origintalUrl='%s', shortUrl='%s', timestamp='%s', accessCount='%d']", 
            id, userId, origintalUrl, shortUrl, timestamp, accessCount
        );
    }

}
