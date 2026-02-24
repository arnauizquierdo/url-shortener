# Url Shortener - API

Backend REST API for shortening URLs, managing user links and secured with JWT.

**Main stack:** Java 21 · Spring Boot · Spring Security (JWT) · MySQL · Docker · Testcontainers, Swagger UI.

## 📑 Contents

- [✨ Key Features](#-key-features)
- [🧰 Tech Stack](#-tech-stack)
- [🧱 Architecture Overview](#-architecture-overview)
- [🔐 Security - JWT Flow](#-security--jwt-flow)
- [📚 Swagger / OpenAPI](#-swagger--openapi)
- [🐳 Docker Setup](#-docker-setup)
- [🧪 Testing](#-testing)
- [🚀 Quickstart](#-quickstart)

## ✨ Key Features

- Stateless authentication and authorization with JWT
- Secure short URL generation
- Public HTTP redirection endpoint
- Layered architecture (Controller / Service / Repository / DTOs)
- Fully dockerized environment (multi-stage build)
- Unit and integration testing (Mockito + Testcontainers)
- Interactive API documentation with Swagger/OpenAPI

## 🧰 Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

### Security
- Spring Security
- JWT (Nimbus)
- BCrypt password hashing

### Database
- MySQL 8

### Testing
- JUnit 5
- Mockito
- Testcontainers

### DevOps
- Docker Compose
- Multi-stage build

### Documentation
- Springdoc OpenAPI (Swagger UI)

## 🧱 Architecture Overview

The application follows a **layered architecture** that promotes separation of concerns, testability and maintainability.

### Architecture Style

- Stateless REST API
- Layered architecture
- DTO pattern to decouple persistence from API contract
- Repository pattern via Spring Data JPA

### Package Structure & Responsibilities

```
com.urlshortener.api
├── config
├── controller
├── exception
├── mapper
├── model
│   ├── dto
│   └── entity
├── repository
├── service
└── util
```
### Architecture Diagram

``` mermaid
flowchart LR

    User[🧑 User]

    Browser[Web Client]

    subgraph SYS["URL Shortener System"]
        direction LR
        
        subgraph DC["Docker Compose"]
            direction LR
            
            subgraph api_container["urlshortener-api"]
                API["Spring Boot REST API<br/>(Stateless, Layered Architecture)"]
            end

            subgraph db_container["urlshortener-db"]
                DB[(MySQL 8.0)]
            end
        end
    end

    User --> Browser
    Browser["Web Client"] -->|"HTTPS (+ Bearer JWT)"| API
    API -->|JPA| DB

    style User fill:#F1E3FD,stroke:#E2C0FC,stroke-width:2px

    style Browser fill:#E8F5E9,stroke:#A5D6A7,stroke-width:2px

    style SYS fill:#FFFFFF,stroke:#000000,stroke-width:2px

    style DC fill:#BBF8FA,stroke:#80DEEA,stroke-width:2px

    style api_container fill:#F7EDD2,stroke:#F5DBAB,stroke-width:2px
    style db_container fill:#F7EDD2,stroke:#F5DBAB,stroke-width:2px

    style API fill:#C8F7C1,stroke:#96EB88,stroke-width:2px
    style DB fill:#C8F7C1,stroke:#96EB88,stroke-width:2px
```

## 🔐 Security / JWT Flow

The application uses **stateless authentication with JSON Web Tokens (JWT)** implemented via Spring Security OAuth2 Resource Server.

### Authentication flow

1. Client sends credentials to `POST /login`
2. Server validates username and password
3. A signed JWT is generated
4. Client includes the token in subsequent requests: `Authorization: Bearer + <jwt-token>`

5. Spring Security validates the token on each *protected* request

### Endpoint protection

| Public endpoints | Protected endpoints |
|------------------|---------------------|
| `/login`         | URL creation        |
| `/register`      | URL deletion        |
| `/{code}` (URL redirection) | URL access stats |
| Swagger endpoints | User URLs listing  |

### Key security components

- ✅ `SecurityFilterChain`
- ✅ `JwtEncoder` / `JwtDecoder`
- ✅ `BCryptPasswordEncoder`
- ✅ `Custom AuthService`

## 📚 Swagger / OpenAPI

The API is documented using **Springdoc OpenAPI** and exposed through Swagger UI for easy exploration and testing.

Swagger provides:
 
- Built-in request execution  
- JWT Bearer authentication support  
- DTO schemas and request/response models  

Once the application is running, the documentation is available at:

<p align="center">
  <code>http://localhost:8080/swagger-ui/index.html</code>
</p>

## 🐳 Docker Setup

The application is fully containerized using Docker and orchestrated with Docker Compose to provide a reproducible local environment.

### Overview

- A **multi-stage Dockerfile** is used to build a lightweight runtime image for the Spring Boot API.
- Docker Compose coordinates the API and MySQL services.
- Services communicate through an **internal Docker network** (*db not exposed externally*).


### Services

- **urlshortener-api**
  - Built via multi-stage Docker build
  - Exposes port `8080`

- **urlshortener-db**
  - MySQL 8 instance
  - Persistent volume

### Environment configuration

Using environment variables, including: database connection, JWT secret and expiration and credentials.

(*See 'Quickstart' section*).

## 🧪 Testing

The project includes **both unit and integration tests** to validate business logic and ensure the application behaves correctly in a realistic environment.

### Unit tests

- Unit tests focus on the service layer using **JUnit 5** and **Mockito**.  
- External dependencies are mocked to verify business rules in isolation.

### Integration tests

Integration tests run against a real MySQL instance using **Testcontainers**.

- Real database behavior  
- Isolated and reproducible test environments  

Testcontainers automatically start and take down the database container during the test lifecycle, ensuring clean and reliable executions.

## 🚀 Quickstart

Get the project running locally in a few simple steps.

### 1. Clone the repository

```bash
git clone https://github.com/arnauizquierdo/url-shortener
cd url-shortener
```
### 2. Set up environment variables

Create a .env file based on the provided example:

```bash
cp .env.example .env
```

Update the values as needed, including:

- Database connection (DB_URL, DB_USER, DB_PASSWORD)

- JWT configuration (JWT_SECRET, JWT_EXPIRATION)

- Other environment-specific settings

<small> The .env file is loaded into the Docker containers and referenced in application.yml, keeping sensitive data out of the code.</small>

### 3. Review application.yml

The ```application.yml``` file contains default Spring Boot configurations.
Make sure to verify:

- Datasource URL
- Username, password

### 4. Start the application with Docker Compose

```docker compose up --build -d```

This command will:

- Build the API Docker image
- Launch the MySQL container (accessible only internally)
- Create a Docker internal network for API ↔ DB communication
- Start the Spring Boot API on port 8080

### 5. Access the application

API base URL: ```http://localhost:8080```

Swagger UI: ```http://localhost:8080/swagger-ui/index.html```
