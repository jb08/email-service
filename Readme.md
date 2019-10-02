# Demo App API

A simple restful API built using Java (Spring framework).

Requirements:
- Java 11
- Docker

App Dependencies:
- Postgres

## Running locally

Tests can be run with `./gradlew test`.

```
docker-compose -f local-dev.yml up
./gradlew build && java -jar build/libs/gs-spring-boot-0.1.0.jar
```

Alternatively, use the IntelliJ run configuration.

Navigate to localhost:8080/swagger-ui.html, and explore there (or in curl or Postman).
