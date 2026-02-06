package com.urlshortener.api.service;

import org.springframework.stereotype.Service;

import com.urlshortener.api.mapper.UserMapper;
import com.urlshortener.api.model.dto.UserDTO;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository= userRepository;
        this.userMapper= userMapper;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        return userMapper.toUserDTO(user);
    }

    public UserDTO createNewUser(String username, String password) {
        User newUser = new User(username, password);
        userRepository.save(newUser);
        return userMapper.toUserDTO(newUser);
    }

}
