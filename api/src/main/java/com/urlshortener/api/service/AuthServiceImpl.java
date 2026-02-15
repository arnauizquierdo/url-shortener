package com.urlshortener.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urlshortener.api.model.dto.UserCreationDTO;
import com.urlshortener.api.model.dto.TokenRequestDTO;
import com.urlshortener.api.model.dto.TokenResponseDTO;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final TokenService tokenService;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(TokenService tokenService, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public TokenResponseDTO login(TokenRequestDTO request) {
        final UserDetails userDetails; // <-
        try {
            userDetails = userDetailsService.loadUserByUsername(request.username());
        } catch (UsernameNotFoundException e) {
            log.error("User not found: {}", request.username());
            throw new RuntimeException("Invalid credentials");
        }

        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            log.error("Password mismatch for user: {}", request.username());
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenService.generateToken(userDetails.getUsername());

        return new TokenResponseDTO(token);
    }

    public void register(UserCreationDTO request) {
        // Validar que l'email no existeix
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Crear nou usuari
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        
        userRepository.save(user);
        log.info("User registered successfully: {}", request.getUsername());
    }
}
