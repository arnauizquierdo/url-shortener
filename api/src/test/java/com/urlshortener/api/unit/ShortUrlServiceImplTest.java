package com.urlshortener.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.urlshortener.api.mapper.ShortUrlMapper;
import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.service.ShortUrlServiceImpl;
import com.urlshortener.api.util.ShortUrlCodeGenerator;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ShortUrlServiceImplTest {

    @Mock
    ShortUrlRepository shortUrlRepository;

    @Mock
    ShortUrlMapper shortUrlMapper;

    @Mock
    ShortUrlCodeGenerator codeGenerator;

    @InjectMocks
    ShortUrlServiceImpl shortUrlService;

    public User USER_PREPARED = new User("arnau", "pass");
    public ShortUrl SHORTURL_PREPARED = new ShortUrl(USER_PREPARED, "https://google.com", "abc123");

    @Test
    void resolveShortUrlIncrementAccessCount() {
        when(shortUrlRepository.findByCode("abc123")).thenReturn(Optional.of(SHORTURL_PREPARED));
        
        shortUrlService.resolveShortUrl("abc123");
        shortUrlService.resolveShortUrl("abc123");

        assertEquals(2L, SHORTURL_PREPARED.getAccessCount());
    }

    @Test
    void createShortUrl() {
        // To-do
    }

}
