package com.urlshortener.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.repository.UserRepository;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UrlShortenerApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(UrlShortenerApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApiApplication.class, args);
	}
	
    @Bean
    public CommandLineRunner demo(UserRepository repository1, ShortUrlRepository repository2) {
        
        return (args) -> {

        };
    }
    

}
