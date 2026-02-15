package com.urlshortener.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("create")
    public ResponseEntity<ShortUrlDTO> createShortUrl(@RequestBody ShortUrlCreationDTO dto) {
        // To-do: get the user by token/jwt
        // Provisional: hardcoded with User ID=1
        User user = userService.getUserById(1L);
        ShortUrlDTO newShortUrl = shortUrlService.createShortUrl(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShortUrl);
    }

    @GetMapping("userurls")                      //@AuthenticationPrincipal UserDetails userDetails
    public ResponseEntity<List<ShortUrlDTO>> getUserUrls() {
        // To-do: get the user by token/jwt
        // Provisional: hardcoded with User ID=1
        User user = userService.getUserById(1L);
        List<ShortUrlDTO> urlsByUser = shortUrlService.getUrlsByUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(urlsByUser);
    }

    @GetMapping("stats/{code}")                      
    public ResponseEntity<ShortUrlDTO> getShortUrlStats(@PathVariable String code) {
        // To-do: 
        //  - get the user by token/jwt
        // Provisional: hardcoded with User ID=1
        User user = userService.getUserById(1L);
        ShortUrlDTO urlStats = shortUrlService.getUrlStats(code, user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(urlStats);
    }

    @DeleteMapping("delete/{code}")
    public HttpStatus deleteShortUrl(@PathVariable String code) {
        // To-do: 
        //  - get the user by token/jwt
        // Provisional: hardcoded with User ID=1
        User user = userService.getUserById(1L);
        shortUrlService.deleteShortUrl(code, user.getId());
        return HttpStatus.OK;
    }

}
