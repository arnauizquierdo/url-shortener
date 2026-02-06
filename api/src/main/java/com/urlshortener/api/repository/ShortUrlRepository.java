package com.urlshortener.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlshortener.api.model.entity.ShortUrl;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrl, Long> {

    List<ShortUrl> findByUserId(Long userId);

    Optional<ShortUrl> findById(long id);
}
