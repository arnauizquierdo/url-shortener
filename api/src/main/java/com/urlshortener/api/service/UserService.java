package com.urlshortener.api.service;

import com.urlshortener.api.model.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(Long id);

}
