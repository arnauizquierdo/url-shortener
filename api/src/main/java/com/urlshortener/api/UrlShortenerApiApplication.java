package com.urlshortener.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.repository.UserRepository;

@SpringBootApplication
public class UrlShortenerApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(UrlShortenerApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApiApplication.class, args);
	}

	//Test a few users
	@Bean
    public CommandLineRunner demo(UserRepository repository1, ShortUrlRepository repository2) {
        return (args) -> {

            repository1.save(new User("User1", "123"));
            repository1.save(new User("User2", "321"));

			logger.info("-------------------------------");
            logger.info("FindAll():");
			logger.info("-------------------------------");
            repository1.findAll().forEach(user -> {
                logger.info(user.toString());
            });
			logger.info("-------------------------------");
            logger.info("FindById():");
			logger.info("-------------------------------");
            User user = repository1.findById(1L);
			logger.info(user.toString());
            logger.info("\n\n");

            logger.info("Creating some shortUrl:\n");
            repository2.save(new ShortUrl(user.getId(), "https://es.wikipedia.org", "https://ca.wikipedia.org"));
            repository2.save(new ShortUrl(user.getId(), "https://bbc.com", "https://324.cat"));
            repository2.findByUserId(2L).forEach(shortUrl -> {
                logger.info("\n" + shortUrl.toString() + "\n");
            });

        };
    }

}
