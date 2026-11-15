# Student REST API (Spring Boot + ArrayList)

A beginner-friendly CRUD API. Student records are stored in an in-memory `ArrayList`; no database is used. Data is cleared when the application stops.

## Requirements
- JDK 21+
- Maven 3.6+ (or use an IDE with Maven support)

## Run
From this directory:

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## Endpoints

| Method | URL | Purpose |
|---|---|---|
| POST | `/students` | Add a student |
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get a student by ID |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete a student |

### Example POST body

```json
{
  "id": 1,
  "name": "Rahul",
  "email": "rahul@gmail.com",
  "course": "Java"
}
```

Send JSON with `Content-Type: application/json`. Duplicate IDs return `409 Conflict`; missing IDs return `404 Not Found`.
