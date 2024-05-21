# Infrastructure

To set up the environment, you'll need to create a file named ".env" in the project's root directory. Fill this file
with the following content:

```
POSTGRES_USER=your_user
POSTGRES_PASSWORD=your_password
POSTGRES_URL=jdbc:postgresql://localhost:5432/courses
```

Once the environment variables are configured, start the PostgreSQL instance by running the docker-compose file
located in the same directory:

```
docker compose up -d
```

## Migrations

The project uses Flyway to manage database schema versions. When you run the application, Flyway will automatically
apply any pending migrations, potentially creating the "course" table with some sample data.

# Endpoints

## [GET] /app/course

Should return a list of courses that can be filtered by name and/or category according to the values defined in the
body of the request.

## [POST] /app/course

Should create a course. Body request example:

```
{
	"name": "Java Spring Boot",
	"category": "Introduction"
}
```

## [PUT] /app/course/{id}

Should update a course by its id. Body request example:
```
{
    "name": "Java Spring Boot",
    "category": "Intermediate"
}
```

## [DELETE] /app/course/{id}

Should physically delete a course by its id.

## [DELETE] /app/course/{id}/active

Should toggle the status of a course by its by id between ACTIVE or INACTIVE.