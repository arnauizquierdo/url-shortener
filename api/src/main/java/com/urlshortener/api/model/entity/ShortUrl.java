package com.urlshortener.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="shorturl")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime timestamp;
    private Long accessCount;

    protected ShortUrl() {}

    public ShortUrl(Long userId, String originalUrl, String shortUrl) {
        this.userId = userId;
        this.originalUrl = originalUrl;
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
    public String getoriginalUrl() {
        return originalUrl;
    }
    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public String toString() {
        return String.format(
            "ShortUrl[id=%d, userId='%d', originalUrl='%s', shortUrl='%s', timestamp='%s', accessCount='%d']", 
            id, userId, originalUrl, shortUrl, timestamp, accessCount
        );
    }

}
