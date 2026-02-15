package com.urlshortener.api.model.entity;

import jakarta.persistence.Column;
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

    @Column(columnDefinition = "longtext")
    private String originalUrl;

    @Column(unique = true, nullable = false, length = 10)
    private String code;

    private LocalDateTime timestamp;
    private Long accessCount;

    protected ShortUrl() {}

    public ShortUrl(User user, String originalUrl, String code) {
        this.user = user;
        this.originalUrl = originalUrl;
        this.code = code;
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
    public String getOriginalUrl() {
        return originalUrl;
    }
    public String getCode() {
        return code;
    }
    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Override
    public String toString() {
        return String.format(
            "ShortUrl[id=%d, userId='%d', originalUrl='%s', code='%s', timestamp='%s', accessCount='%d']", 
            id, user.getId(), originalUrl, code, timestamp, accessCount
        );
    }

}
