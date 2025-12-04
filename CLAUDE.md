# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Gestor Ordas Saruman** is a Spring Boot 3.5.7 application for managing enemy data ("enemigos"). It provides a REST API and a static HTML frontend for CRUD operations on enemy records stored in a PostgreSQL database.

## Build and Run Commands

### Running the Application
```bash
# Run the Spring Boot application
mvnw spring-boot:run

# Or compile and run
mvnw clean install
java -jar target/gestorOrdasSaruman-0.0.1-SNAPSHOT.jar
```

The application starts on the default port (8080) and serves:
- Frontend: `http://localhost:8080/`
- REST API: `http://localhost:8080/api/enemigo`

### Testing
```bash
# Run all tests
mvnw test

# Run a single test class
mvnw test -Dtest=GestorOrdasSarumanApplicationTests
```

### Build
```bash
# Compile and package without running tests
mvnw clean package -DskipTests

# Full build with tests
mvnw clean install
```

## Architecture

### Layer Structure
The application follows a standard Spring Boot layered architecture:

1. **Controller Layer** (`dam.saruman.controller`)
   - `HomeController`: Serves the static HTML frontend at root path
   - `EnemigoController`: REST API endpoints at `/api/enemigo` for CRUD operations

2. **Service Layer** (`dam.saruman.service`)
   - `EnemigoService`: Business logic for enemy management, including partial update handling

3. **Repository Layer** (`dam.saruman.repository`)
   - `EnemigoRepository`: JPA repository extending `JpaRepository<Enemigo, Long>`
   - Custom query: `findByNombre` using `@Query` annotation

4. **Entity Layer** (`dam.saruman.entity`)
   - `Enemigo`: JPA entity mapped to `enemigos` table with fields: id, nombre, genero, pais, afiliacion, activo

### REST API Endpoints
- `GET /api/enemigo` - List all enemies
- `GET /api/enemigo/{id}` - Get enemy by ID
- `POST /api/enemigo` - Create new enemy
- `PUT /api/enemigo/{id}` - Update enemy (supports partial updates)
- `DELETE /api/enemigo/{id}` - Delete enemy by ID

### Frontend
Static resources in `src/main/resources/static/`:
- `index.html` - Main UI with table and form
- `script.js` - Frontend JavaScript for API calls
- `styles2.css` - Styling

## Database Configuration

PostgreSQL database connection is configured in `src/main/resources/application.properties`:
- Database: `ENEMIGOS`
- Default credentials: `postgres/admin` on `localhost:5432`
- Hibernate DDL: `update` mode (auto-creates/updates schema)
- SQL logging is enabled for development

**Note**: Before running the application, ensure PostgreSQL is running and the `ENEMIGOS` database exists.

## Key Implementation Details

### Partial Updates
The `EnemigoService.actualizarEnemigo` method supports partial updates - only non-null fields in the request body are updated on the entity.

### Entity Validation
- `pais` and `afiliacion` fields have `nullable = false` constraint
- `id` is auto-generated using `IDENTITY` strategy

### Spring Configuration
- Java 17 is required
- Spring Boot DevTools enabled for development with auto-restart
- Static resource caching disabled in development mode
- Hibernate formats SQL output for readability
