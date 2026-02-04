package com.urlshortener.api.mapper;

import com.urlshortener.api.model.dto.ShortUrlCreationDTO;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.entity.ShortUrl;

public class ShortUrlMapper {

    public ShortUrlDTO toShortUrlDTO(ShortUrl entity) {
        return new ShortUrlDTO(
            entity.getoriginalUrl(),
            entity.getShortUrl(),
            entity.getTimestamp(),
            entity.getAccessCount()
        );
    }

    public ShortUrl toShortUrlEntity(Long userId, ShortUrlCreationDTO dto) {
        return new ShortUrl(userId, dto.getoriginalUrl(), null);
    }

}
