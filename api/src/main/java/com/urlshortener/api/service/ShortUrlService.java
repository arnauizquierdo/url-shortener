package com.urlshortener.api.service;

import java.util.List;

import com.urlshortener.api.model.dto.ShortUrlCreationDTO;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;

public interface ShortUrlService {

    ShortUrlResolvedDTO resolveShortUrl(String code);
    ShortUrlDTO createShortUrl(ShortUrlCreationDTO  dto, User user);
    List<ShortUrlDTO> getUrlsByUser(Long userId);

} 