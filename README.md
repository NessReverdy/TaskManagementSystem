# Task management system

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Spring Security](https://img.shields.io/badge/Security-Spring_Security-green)
![JWT](https://img.shields.io/badge/Auth-JWT-yellow)
![Gradle](https://img.shields.io/badge/Build-Gradle-darkgreen)
![Docker](https://img.shields.io/badge/Container-Docker-blue)

Task Management System — a microservices REST API application for managing tasks and projects.

The project is being developed using Spring Boot and provides:

- user registration and authentication,
- JWT token handling,
- project and task management,
- a role-based access control system.

## Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- JWT Authentication
- Gradle
- Redis

## Current Functionality

- User registration
- User authentication
- JWT access/refresh tokens
- Secured endpoints

## Authentication

The application uses JWT authentication.

After successful registration/login, the application returns to the user:

- access token;
- refresh token.

Access token is used for authorized requests.  
Refresh token is stored in the database and can be revoked.

# API Endpoints

## Auth

### Registration

```http
POST /auth/register
```

Request body:

```json
{
  "username": "alice",
  "password": "12345678",
  "role": "USER"
}
```

Response:

```json
{
  "accessToken": "jwt-access-token",
  "refreshToken": "jwt-refresh-token"
}
```

### Authorization

```http
POST /auth/login
```

Request body:

```json
{
  "username": "alice",
  "password": "12345678"
}
```

Response:

```json
{
  "accessToken": "jwt-access-token",
  "refreshToken": "jwt-refresh-token" 
}
```

### Refresh access token

```http
POST /auth/refresh
```

Request body:

```json
{
  "refreshToken": "jwt-refresh-token"
}
```

Response:

```json
{
  "accessToken": "jwt-access-token",
  "refreshToken": "jwt-refresh-token" 
}
```

## User (Requires access token)

### Create user
```http
POST /users
```

Request:

```http
{
    "id": 2,
    "username": "user9",
    "password":"1234",
    "role": "USER"
}
```

Responce:

```http
{
    "id": 2,
    "username": "user9",
    "role": "USER"
}
```

### Get user by ID

```http
GET /users/{id}
```

Response:

```json
{
    "id": 2,
    "username": "user9",
    "role": "USER"
}
```

### Get all users

```http
GET /users
```

Response:

```json
[
    {
        "id": 1,
        "username": "user1",
        "role": "USER"
    },
    {
        "id": 2,
        "username": "user9",
        "role": "USER"
    }
]
```

### Delete user by ID (only for admins or users who change their data)

```http
DELETE /users/admin/{id}
```

Response: 204 No Content

### Update user (only for admins or users who change their data)

```http
PATCH /users/{id}/update
```

Request body:

```json
{
  "username": "alice",
  "password": "12345678",
  "role": "USER"
}
```

Response:

```json
{
    "id": 1,
    "username": "user2",
    "role": "USER"
}
```

### Change user role (only for admin)

```http
PATCH /users/admin/{id}/role
```

Request:

```json
{
  "role": "USER"
}
```

Response:

```json
[
    {
        "id": 1,
        "username": "user2",
        "role": "USER"
    }
]
```

### Get all users by role

```http
GET /users/all
```

Request:

```json
{
  "role": "USER"
}
```

Response:

```json
[
    {
        "id": 1,
        "username": "user2",
        "role": "ADMIN"
    }
]
```

# Run project

1. Clone the repository

```bash
git clone https://github.com/nessreverdy/taskmanagementsystem.git
```

2. Run Docker

```bash
docker compose up
```

3. Run the application

```bash
./gradlew bootRun
```

4. The following files must be present: .env, application.properties, application.yml. Examples of these files are
   available in the repository.

# TODO

In the future, the system is planned to be extended with additional functionality and new features.

## Authorization and Security

- Token revocation system
- User activity logging
- Two-factor authentication (2FA)

## User Management

- Role-based access control system

## Projects and Tasks

- Task priorities
- Task statuses
- Task comments
- Project participants
- Task filtering and sorting
- Task search functionality

## API and Documentation

- Swagger/OpenAPI documentation
- Improved error handling
- Improved request validation

## Testing

- Unit tests
- Integration tests
- Security tests
- API testing
