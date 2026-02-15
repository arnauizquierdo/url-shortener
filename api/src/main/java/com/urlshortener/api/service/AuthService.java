package com.urlshortener.api.service;

import com.urlshortener.api.model.dto.TokenRequestDTO;
import com.urlshortener.api.model.dto.TokenResponseDTO;
import com.urlshortener.api.model.dto.UserCreationDTO;

public interface AuthService {

    public TokenResponseDTO login(TokenRequestDTO request);
    public void register(UserCreationDTO request);

}
