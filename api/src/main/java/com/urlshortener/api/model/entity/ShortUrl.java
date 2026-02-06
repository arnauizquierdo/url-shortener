package com.urlshortener.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="shorturl")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime timestamp;
    private Long accessCount;

    protected ShortUrl() {}

    public ShortUrl(User user, String originalUrl, String shortUrl) {
        this.user = user;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.timestamp = LocalDateTime.now();
        this.accessCount = 0L;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
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
    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    @Override
    public String toString() {
        return String.format(
            "ShortUrl[id=%d, userId='%d', originalUrl='%s', shortUrl='%s', timestamp='%s', accessCount='%d']", 
            id, user.getId(), originalUrl, shortUrl, timestamp, accessCount
        );
    }

}
