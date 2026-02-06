package com.urlshortener.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.urlshortener.api.mapper.ShortUrlMapper;
import com.urlshortener.api.model.dto.ShortUrlCreationDTO;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.repository.UserRepository;
import com.urlshortener.api.util.ShortUrlCodeGenerator;

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
    private final ShortUrlCodeGenerator codeGenerator;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository, UserRepository userRepository, ShortUrlMapper shortUrlMapper, ShortUrlCodeGenerator codeGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.userRepository = userRepository;
        this.shortUrlMapper = shortUrlMapper;
        this.codeGenerator = codeGenerator;
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

    @Transactional
    public ShortUrlDTO createShortUrl(ShortUrlCreationDTO dto, User user) {
        String uniqueCode = generateUniqueCode();
        ShortUrl newShortUrl = shortUrlMapper.toShortUrlEntity(user, dto);
        newShortUrl.setCode(uniqueCode);
        return shortUrlMapper.toShortUrlDTO(shortUrlRepository.save(newShortUrl));
    }

    private String generateUniqueCode() {
        String code;
        int attempts = 0;
        int maxAttempts = 10; // Set a try limit
        do {
            code = codeGenerator.generate();
            attempts++;
            if (attempts > maxAttempts) {
                throw new RuntimeException("Failed to generate unique code after " + maxAttempts + " attempts");
            }
        } while (shortUrlRepository.existsByCode(code));
        return code;
    }

} 