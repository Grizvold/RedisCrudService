# Redis CRUD REST API Service

## Introduction
This project is a simple Java-based REST API microservice that performs CRUD operations on a Redis database. It has been containerized with Docker and is orchestrated via Kubernetes. 

## Tech Stack
- Language: Java 17
- Web Framework: SparkJava
- Database: Redis
- Build Tool: Maven
- Containerization: Docker
- Orchestration: Kubernetes

## Requirements
- Java 17
- Maven
- Docker
- Kubernetes
- A running instance of Redis

## Local Setup
To set up this project on your local machine, you will need to have Docker, Maven, Java 17, and a Redis instance installed. Follow these steps:

1. Clone this repository and navigate into the cloned directory.
2. Start your local Redis server.
3. Build the project with Maven: `mvn clean package`
4. Build a Docker image: `docker build -t redis-crud-service .`
5. Run the service inside a Docker container: `docker run -p 4567:4567 redis-crud-service`

## Deployment on Kubernetes
The project also includes a `deployment.yaml` file for deploying the application to a Kubernetes cluster. The Kubernetes setup includes a service that exposes the application on a NodePort.

## API Endpoints
The application provides the following endpoints:

- **Create**: Send a POST request to `http://localhost:4567/create`, with `key` and `value` parameters in the request body.
- **Read**: Send a GET request to `http://localhost:4567/read/{key}`, replacing `{key}` with the key of the value you want to retrieve.
- **Update**: Send a PUT request to `http://localhost:4567/update`, with `key` and `new_value` parameters in the request body.
- **Delete**: Send a DELETE request to `http://localhost:4567/delete/{key}`, replacing `{key}` with the key of the value you want to delete.
- **Health Check**: Send a GET request to `http://localhost:4567/health` to check the health status of the service.

These endpoints correspond to Create, Read, Update, and Delete (CRUD) operations for key-value pairs in the Redis database.
