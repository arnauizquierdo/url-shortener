package com.urlshortener.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.urlshortener.api.model.dto.TokenRequestDTO;
import com.urlshortener.api.model.dto.TokenResponseDTO;
import com.urlshortener.api.repository.UserRepository;
import com.urlshortener.api.service.AuthServiceImpl;
import com.urlshortener.api.service.CustomUserDetailsService;
import com.urlshortener.api.service.TokenService;

import com.urlshortener.api.exception.InvalidCredentialsException;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    TokenService tokenService;

    @Mock
    CustomUserDetailsService userDetailsService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl authService;

    public TokenRequestDTO REQUEST_PREPARED = new TokenRequestDTO("arnau", "1234");

    @Test
    void loginShouldReturnTokenWhenCredentialsValid() {
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername("arnau")).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encoded");
        when(passwordEncoder.matches("1234", "encoded")).thenReturn(true);
        when(userDetails.getUsername()).thenReturn("arnau");
        when(tokenService.generateToken("arnau")).thenReturn("mocked-token");

        TokenResponseDTO response = authService.login(REQUEST_PREPARED);

        assertEquals("mocked-token", response.token());
    }

    @Test
    void loginShouldReturnErrorWhenCredentialsInvalid() {
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername("arnau")).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encoded");
        when(passwordEncoder.matches("1234", "encoded")).thenReturn(false);
        
        assertThrowsExactly(InvalidCredentialsException.class, () -> authService.login(REQUEST_PREPARED));
        verify(tokenService, never()).generateToken(any());
    }

}