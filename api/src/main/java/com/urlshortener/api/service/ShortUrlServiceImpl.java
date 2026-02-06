package com.urlshortener.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.urlshortener.api.mapper.ShortUrlMapper;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

/*
    - Lògica real al ShortUrlService -

    El Service hauria de fer com a mínim:

        Buscar la ShortUrl pel codi

        Comprovar que existeix

        Incrementar accessCount

        Guardar

        Retornar DTO

    - @Transactional al ShortUrlService - 
    
*/

@Service
public class ShortUrlServiceImpl implements ShortUrlService{

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;
    private final ShortUrlMapper shortUrlMapper;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository, UserRepository userRepository, ShortUrlMapper shortUrlMapper) {
        this.shortUrlRepository = shortUrlRepository;
        this.userRepository = userRepository;
        this.shortUrlMapper = shortUrlMapper;
    }

    @Transactional
    public ShortUrlResolvedDTO resolveShortUrl(Long code) {
        ShortUrl url = shortUrlRepository.findById(code)
            .orElseThrow(() -> 
                new EntityNotFoundException("ShortUrl not found with code " + code)
            );
        url.setAccessCount(url.getAccessCount() + 1);
        return shortUrlMapper.toShortUrlResolvedDTO(url);
    }

    public ShortUrlDTO createShortUrl(String originalUrl, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> 
                new EntityNotFoundException("User not found with id " + userId)
            );
        ShortUrl newShortUrl = new ShortUrl(user, originalUrl, "salamandra");
        return shortUrlMapper.toShortUrlDTO(shortUrlRepository.save(newShortUrl));
    }

    
} 