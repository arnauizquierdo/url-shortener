package com.urlshortener.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.service.ShortUrlService;

@RestController
@RequestMapping("/short")
public class ResolveUrlController {

    private final ShortUrlService shortUrlService;

    public ResolveUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("{code}")
    public ResponseEntity<ShortUrlResolvedDTO> resolveShortUrl(@PathVariable String code) {
        return ResponseEntity.ok(shortUrlService.resolveShortUrl(code));
    }

}
