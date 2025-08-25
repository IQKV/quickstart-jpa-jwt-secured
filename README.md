# 🚀 JWT Auth Example

JWT-based Spring Security REST API with JPA, Liquibase migrations, and production-ready observability (Actuator, Prometheus, Grafana) and code quality tooling (SonarQube, PMD, Checkstyle, SpotBugs, Qulice).

## Technology stack

- Java 21
- Spring Boot (Web, Security, JPA, Actuator)
- MySQL (HikariCP), Liquibase
- Springdoc OpenAPI/Swagger UI
- Prometheus, Grafana
- SonarQube, PMD, Checkstyle, SpotBugs, Qulice

## Features

- JWT authentication flow: register, authenticate, access secured endpoint
- Stateless security with custom `JwtAuthenticationFilter`
- Database migrations with Liquibase
- OpenAPI docs and Swagger UI
- Observability via Actuator and Prometheus metrics; ready-to-use Grafana dashboards

## Prerequisites

- Java 21+
- Git
- Docker (for optional MySQL/Prometheus/Grafana/SonarQube)
- An IDE (IntelliJ IDEA recommended)

## Quickstart

```bash
git clone https://github.com/IQKV/quickstart-jpa-jwt-secured.git
cd quickstart-jpa-jwt-secured
./mvnw spring-boot:run -Dspring-boot.run.profiles=local -P dev
```

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Actuator health: `http://localhost:8080/actuator/health`

To run as a packaged jar:

```bash
./mvnw package
java -jar target/*.jar
```

## Database

This app uses MySQL. You can either:

1. Point to an existing MySQL via environment variables, or
2. Start a local MySQL with Docker:

```bash
docker compose -f compose.yaml up -d mysql
```

Default dev database configuration (can be overridden via env vars):

- URL: `jdbc:mysql://localhost:3306/svc_testing_db?serverTimezone=UTC`
- Username: `svc_testing`
- Password: `svc_testing`

## Environment variables

These map to `src/main/resources/application.yml` and have sensible defaults for local use:

- `DATASOURCE_URL` (default `jdbc:mysql://localhost:3306/svc_testing_db?serverTimezone=UTC`)
- `DATASOURCE_USERNAME` (default `svc_testing`)
- `DATASOURCE_PASSWORD` (default `svc_testing`)
- `DATASOURCE_DRIVER` (default `com.mysql.cj.jdbc.Driver`)
- `SERVER_PORT` (default `8080`)
- `MANAGEMENT_SERVER_PORT` (default `8080`)
- `JWT_TOKEN_SECRET` (default `jwtapptokensecret` – change for non-local use)
- `TIMEOUT_PER_SHUTDOWN` (default `20s`)
- `LOGGING_LEVEL_ROOT` (default `INFO`)

## API overview

Base path: `/api/v1`

- `POST /auth/register` – create a user
- `POST /auth/authenticate` – obtain JWT
- `GET /demo` – secured endpoint, requires `Authorization: Bearer <token>`

### Example: register and authenticate

```bash
# Register
curl -sS -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstname":"John","lastname":"Doe","email":"john.doe@example.com","password":"password"}'

# Authenticate
TOKEN=$(curl -sS -X POST http://localhost:8080/api/v1/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{"email":"john.doe@example.com","password":"password"}' | jq -r '.token')

# Call secured endpoint
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/v1/demo
```

Swagger UI is available at `http://localhost:8080/swagger-ui.html`.

## Observability

Actuator endpoints (selected are enabled):

- `GET /actuator/health` – health and probes
- `GET /actuator/info` – build and git info
- `GET /actuator/metrics` – metrics catalog
- `GET /actuator/prometheus` – Prometheus scrape endpoint

Start local Prometheus and Grafana:

```bash
docker compose -f compose.yaml up -d prometheus grafana
```

- Prometheus: `http://localhost:9090`
- Grafana: `http://localhost:3000` (admin password defaults to `changeme` per compose)

## Code quality (optional)

Start SonarQube locally:

```bash
docker compose -f compose.yaml up -d sonar
```

- SonarQube UI: `http://localhost:9000`

Run full verification locally (includes Qulice profile):

```bash
./mvnw verify -Puse-qulice
```

Minimum code coverage required: **80%**.

## Working in your IDE

1. Open the project via `pom.xml` in your IDE
2. Enable the `local` Spring profile in your Run configuration (or pass `-Dspring-boot.run.profiles=local`)
3. Start the app and navigate to Swagger UI

## Project structure (high level)

- `config/` – security and application configuration
- `dto/` – request/response payloads
- `entity/` – JPA entities
- `repository/` – Spring Data JPA repositories
- `service/` – domain services and JWT utilities
- `web/` – REST controllers
- `resources/db/changelog/` – Liquibase changelogs

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE).

> ### Versioning
>
> Project uses a three-segment [CalVer](https://calver.org/) scheme: `YY.MM.MICRO`.
>
> 1. YY – short year (e.g., 6, 16, 106)
> 2. MM – short month (1…12)
> 3. MICRO – patch segment
