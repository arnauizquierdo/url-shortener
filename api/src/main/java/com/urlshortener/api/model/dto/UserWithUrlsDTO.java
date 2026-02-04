package com.urlshortener.api.model.dto;

import java.util.List;

public class UserWithUrlsDTO { // show user and its shortUrls
    
    private String username;
    private List<ShortUrlDTO> urls;
    
    public UserWithUrlsDTO(String username, List<ShortUrlDTO> urls) {
        this.username = username;
        this.urls = urls;
    }
    public String getUsername() {
        return username;
    }
    public List<ShortUrlDTO> getUrls() {
        return urls;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUrls(List<ShortUrlDTO> urls) {
        this.urls = urls;
    }

}
