package com.shield;

import com.shield.services.redis.RedisService;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        RedisService redisService = new RedisService();

        // Get port from environment variable, default to 4567 if not present
        String portEnv = System.getenv("PORT");
        int portNumber = (portEnv == null) ? 4567 : Integer.parseInt(portEnv);

        port(portNumber); // set the port SparkJava will use

        get("/health", (req, res) -> "Service is up and running");

        post("/create", (req, res) -> redisService.create(req, res));

        get("/read/:key", (req, res) -> redisService.read(req, res));

        put("/update/:key", (req, res) -> redisService.update(req, res));

        delete("/delete/:key", (req, res) -> redisService.delete(req, res));
    }
}
