package com.urlshortener.api.integration;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.urlshortener.api.model.entity.ShortUrl;
import com.urlshortener.api.model.entity.User;
import com.urlshortener.api.repository.ShortUrlRepository;
import com.urlshortener.api.repository.UserRepository;
import com.urlshortener.api.service.TokenService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShortUrlRepository shortUrlRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        shortUrlRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateShortUrlSuccessfully() throws Exception {
        User user = new User("arnau", passwordEncoder.encode("1234"));
        userRepository.save(user);

        String token = tokenService.generateToken("arnau");

        given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body("""
                    {
                        "originalUrl": "https://google.com"
                    }
                """)
        .when()
            .post("/api/urls/create")
        .then()
            .statusCode(201)
            .body("originalUrl", equalTo("https://google.com"));
    }

    @Test
    void shouldReturn401WhenNoTokenProvided() {

        // with /api/urls/create
        given()
            .contentType(ContentType.JSON)
            .body("""
                    {
                    "originalUrl": "https://google.com"
                    }
                """)
        .when()
            .post("/api/urls/create")
        .then()
            .statusCode(401);

        // with /api/urls/myurls
        given()
        .when()
            .get("/api/urls/myurls")
        .then()
            .statusCode(401);

    }

    @Test
    void shouldReturn404WhenDeletingUrlFromAnotherUser() {
        // Creating two users
        User owner = new User("owner", passwordEncoder.encode("1234"));
        User attacker = new User("attacker", passwordEncoder.encode("1234"));
        owner = userRepository.save(owner);
        attacker = userRepository.save(attacker);

        // Creating a valid shortUrl for Owner
        ShortUrl ownerUrl = new ShortUrl(owner, "https://google.com", "abc123");
        shortUrlRepository.save(ownerUrl);

        assertThat(shortUrlRepository.findByCode("abc123")).isPresent();

        // 'Attaker' try to delete Owner's shortUrl, and get 404 even if the token is valid itself
        String attackerToken = tokenService.generateToken("attacker");
        given()
            .header("Authorization", "Bearer " + attackerToken)
        .when()
            .delete("/api/urls/delete/{code}", "abc123")
        .then()
            .statusCode(404);
    }

    @Test
    void shouldResolvePublicShortUrlSuccessfully() {

        // Creating new ShortUrl
        User user = new User("publicuser", passwordEncoder.encode("1234"));
        user = userRepository.save(user);
        ShortUrl shortUrl = new ShortUrl(user, "https://example.com/public", "pub123");
        shortUrlRepository.save(shortUrl);

        assertThat(shortUrlRepository.findByCode("pub123")).isPresent();

        given()
            .baseUri("http://localhost:" + RestAssured.port)
        .when()
            .get("/short/pub123")
        .then()
            .statusCode(200)
            .body(
                "originalUrl", equalTo("https://example.com/public")
            );
    }

    @Test
    void shouldReturnUserUrlsSuccessfully() {

        User user = new User("urlsuser", passwordEncoder.encode("1234"));
        user = userRepository.save(user);

        // Creating user's urls
        ShortUrl url1 = new ShortUrl(user, "https://google.com", "code1");
        ShortUrl url2 = new ShortUrl(user, "https://www.netflix.com", "code2");
        shortUrlRepository.save(url1);
        shortUrlRepository.save(url2);

        // Creating user's token
        String token = tokenService.generateToken("urlsuser");

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/api/urls/myurls")
        .then()
            .statusCode(200)
            .body("size()", equalTo(2))
            .body("[0].originalUrl", equalTo("https://google.com"))
            .body("[1].originalUrl", equalTo("https://www.netflix.com"));
    }


}