# Spring Boot REST API

A simple REST API for managing posts, built with Spring Boot. This project is a practice migration from NestJS to Spring Boot.

## Requirements

- Java 8 or later

## Run

```bash
./gradlew bootRun
```

## API

All endpoints use the `/api` prefix:

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/posts` | List all posts |
| `GET` | `/posts/{id}` | Get a post |
| `POST` | `/posts` | Create a post |
| `PUT` | `/posts/{id}` | Update a post |
| `DELETE` | `/posts/{id}` | Delete a post |

Posts require a non-empty `title` and `text`.

## Test

```bash
./gradlew test
```
