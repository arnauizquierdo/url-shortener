package com.urlshortener.api.service;

public interface TokenService {

    String generateToken(String subject);

}
