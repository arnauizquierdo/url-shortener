package com.urlshortener.api.mapper;

import org.springframework.stereotype.Component;
import com.urlshortener.api.model.dto.ShortUrlCreationDTO;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;

@Component
public class ShortUrlMapper {

    public ShortUrlDTO toShortUrlDTO(ShortUrl entity) {
        return new ShortUrlDTO(
            entity.getOriginalUrl(),
            entity.getCode(),
            entity.getTimestamp(),
            entity.getAccessCount()
        );
    }

    public ShortUrlResolvedDTO toShortUrlResolvedDTO(ShortUrl entity) {
        return new ShortUrlResolvedDTO(entity.getOriginalUrl());
    }

    public ShortUrl toShortUrlEntity(User user, ShortUrlCreationDTO dto) {
        return new ShortUrl(user, dto.getOriginalUrl(), null);
    }

}
