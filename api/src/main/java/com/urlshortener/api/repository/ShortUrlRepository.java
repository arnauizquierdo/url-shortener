package com.urlshortener.api.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.urlshortener.api.model.entity.ShortUrl;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, Long> {

    List<ShortUrl> findByUserId(Long userId);

    ShortUrl findById(long id);

}
