package com.urlshortener.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.service.ShortUrlService;

@RestController
@RequestMapping("/api/urls")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("shorten/{code}")
    public ResponseEntity<ShortUrlResolvedDTO> resolveShortUrl(@PathVariable Long code) {
        return ResponseEntity.ok(shortUrlService.resolveShortUrl(code));
    }

}
