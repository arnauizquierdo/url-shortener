package com.urlshortener.api.service;

import com.urlshortener.api.model.dto.UserDTO;
import com.urlshortener.api.model.entity.User;

public interface UserService {

    User getUserByUsername(String username);
    UserDTO getUserDTO(User user);
}
