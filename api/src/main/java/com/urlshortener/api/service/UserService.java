package com.urlshortener.api.service;

import com.urlshortener.api.model.dto.UserDTO;
import com.urlshortener.api.model.entity.User;

public interface UserService {

    UserDTO getUserDTOById(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    
}
