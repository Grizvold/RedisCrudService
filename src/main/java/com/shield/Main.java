package com.shield;

import com.shield.services.redis.RedisService;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        RedisService redisService = new RedisService();

        get("/health", (req, res) -> "Service is up and running");
        get("/read/:key", (req, res) -> redisService.read(req, res));

        post("/create", (req, res) -> redisService.create(req, res));

        put("/update", (req, res) -> redisService.update(req, res));

        delete("/delete/:key", (req, res) -> redisService.delete(req, res));
    }
}
