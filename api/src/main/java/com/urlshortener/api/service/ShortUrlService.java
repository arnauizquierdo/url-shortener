package com.urlshortener.api.service;

import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.model.entity.ShortUrl;

public interface ShortUrlService {

    ShortUrlResolvedDTO resolveShortUrl(Long code);
    ShortUrlDTO createShortUrl(String originalUrl, Long userId);

} 