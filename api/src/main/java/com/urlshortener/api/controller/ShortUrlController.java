package com.urlshortener.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.model.dto.ShortUrlCreationDTO;
import com.urlshortener.api.model.dto.ShortUrlDTO;
import com.urlshortener.api.model.dto.ShortUrlResolvedDTO;
import com.urlshortener.api.service.ShortUrlService;
import com.urlshortener.api.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/urls")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;
    private final UserService userService;

    public ShortUrlController(ShortUrlService shortUrlService, UserService userService) {
        this.shortUrlService = shortUrlService;
        this.userService = userService;
    }

    @GetMapping("s/{code}")
    public ResponseEntity<ShortUrlResolvedDTO> resolveShortUrl(@PathVariable String code) {
        return ResponseEntity.ok(shortUrlService.resolveShortUrl(code));
    }

    @PostMapping("create")
    public ResponseEntity<ShortUrlDTO> createShortUrl(@RequestBody ShortUrlCreationDTO dto) {
        // To-do: get the user by token/jwt
        // Provisional: hardcoded with User ID=1
        User user = userService.getUserById(1L);
        ShortUrlDTO newShortUrl = shortUrlService.createShortUrl(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShortUrl);
    }

}
