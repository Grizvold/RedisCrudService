# Redis CRUD REST API Service

## Introduction
This is a simple Java-based REST API that performs CRUD operations on a Redis database. This project was created as part of an interview process for Shield.

## Tech Stack
- Language: Java 17
- Web Framework: SparkJava
- Database: Redis
- Build Tool: Maven

## How to Run
Ensure you have Java 17, Maven, and Redis installed on your system.

1. Start your local Redis server:

2. Run the service using Maven:

## API Endpoints
The following endpoints are available:

- **Create**: Send a POST request to `http://localhost:4567/create`, with the key and value as query parameters in the request body.

- **Read**: Send a GET request to `http://localhost:4567/read/{key}`, replacing `{key}` with the key of the value you want to retrieve.

- **Update**: Send a PUT request to `http://localhost:4567/update`, with the key and new value as query parameters in the request body.

- **Delete**: Send a DELETE request to `http://localhost:4567/delete/{key}`, replacing `{key}` with the key of the value you want to delete.

- **Health Check**: Send a GET request to `http://localhost:4567/health` to check the health of your service.