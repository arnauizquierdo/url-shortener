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

    @Override
    public UserDTO getUserDTO(User user) {
        return userMapper.toUserDTO(user);
    }    

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found with username " + username));
        return user;
    }

}
