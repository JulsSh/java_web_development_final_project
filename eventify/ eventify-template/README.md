# Eventify

Event management project using Spring Boot and PostgreSQL.

## Tech Stack

- Docker
- Docker Compose
- Java 17+ (or higher)
- Gradle
- Spring Boot 3+

## Getting Started

### OpenAPI Specification
**Important!** For successful frontend-backend integration, follow the OpenAPI specification described in the `api/openapi.yaml` file.

### Starting the database
The project root contains a `docker-compose.yml` file that will help you run a PostgreSQL DB in a Docker container (so you don't need to install a DB server locally).
Use the following command to start it:

```bash
docker-compose up -d --force-recreate
```

**Important:** If you have previously run the container without the init script, make sure to use the `--force-recreate` flag to recreate the container with the new schema.

#### Command parameters:
- `up -d` - start containers in background mode
- `--force-recreate` - recreate containers if needed (overwrites existing ones)

#### Alternative commands:

**Normal start (without recreating):**
```bash
docker-compose up -d
```

**Start with recreating and rebuilding:**
```bash
docker-compose up -d --force-recreate --build
```

After starting, the DB will be available locally with the following parameters:

- **Host:** localhost
- **Port:** 5432
- **Database:** eventify_db
- **User:** postgres
- **Password:** postgres


**Stop containers:**
```bash
docker-compose down
```

**Stop with data removal:**
```bash
docker-compose down -v
```

### Check status

To check container status:

```bash
docker-compose ps
```

To view logs:

```bash
docker-compose logs postgres
```

### Connect to the database

To connect to the database via psql:

```bash
docker exec -it eventify-postgres psql -U postgres -d eventify_db
```
