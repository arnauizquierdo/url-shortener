package com.urlshortener.api.mapper;

import org.springframework.stereotype.Component;

import com.urlshortener.api.model.dto.UserCreationDTO;
import com.urlshortener.api.model.dto.UserDTO;
import com.urlshortener.api.model.dto.UserWithUrlsDTO;
import com.urlshortener.api.model.entity.User;

@Component
public class UserMapper {

    public User toUserEntity(UserCreationDTO dto) {
        return new User(
            dto.username(),
            dto.password()
        );
    }

    public UserDTO toUserDTO(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername());
    }

    /*public UserDTO toUserWithUrlsDTO(User entity) {
        return new UserWithUrlsDTO(entity.getUsername(), // [find personal URLs]);
    }*/

}
