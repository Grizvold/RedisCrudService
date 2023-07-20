package com.shield.services.redis;

import redis.clients.jedis.Jedis;
import spark.Request;
import spark.Response;

public class RedisService {
    private Jedis jedis;

    public RedisService() {
        try {
            this.jedis = new Jedis("localhost", 6379);
        } catch (Exception e) {
            System.err.println("Error connecting to Redis: " + e.getMessage());
            System.exit(1);
        }
    }

    public String create(Request req, Response res) {
        String key = req.queryParams("key");
        String value = req.queryParams("value");

        if (key == null || value == null) {
            res.status(400); // Bad Request
            return "Key or value cannot be null.";
        }

        jedis.set(key, value);
        res.status(201); // Created
        return "Key-value pair created successfully.";
    }
    public String read(Request req, Response res) {
        String key = req.params(":key");
        String value = jedis.get(key);

        if (value == null) {
            res.status(404); // Not Found
            return "No value found for key: " + key;
        } else {
            return value;
        }
    }

    public String update(Request req, Response res) {
        String key = req.queryParams("key");
        String value = req.queryParams("value");

        if (key == null || value == null) {
            res.status(400); // Bad Request
            return "Key or value cannot be null.";
        }

        jedis.set(key, value);
        return "Key-value pair updated successfully.";
    }

    public String delete(Request req, Response res) {
        String key = req.params(":key");
        Long result = jedis.del(key);

        if (result == 1) {
            return "Key deleted successfully.";
        } else {
            res.status(404); // Not Found
            return "No value found for key: " + key;
        }
    }
}
