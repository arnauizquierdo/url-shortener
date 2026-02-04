package com.urlshortener.api.model.dto;

public class UserDTO { // show user (with id)

    private Long id;
    private String username;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
