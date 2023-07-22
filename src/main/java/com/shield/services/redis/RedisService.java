package com.shield.services.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import spark.Request;
import spark.Response;

public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
    private Jedis jedis;

    public RedisService() {
        try {
            this.jedis = new Jedis("localhost", 6379);
        } catch (Exception e) {
            logger.error("Error connecting to Redis: " + e.getMessage());
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

        try {
            jedis.set(key, value);
            res.status(201); // Created
            logger.info("Key-value pair created successfully: " + key);
            return "Key-value pair created successfully.";
        } catch (Exception e) {
            logger.error("Error creating key-value pair: " + e.getMessage());
            res.status(500); // Internal Server Error
            return "Error creating key-value pair.";
        }
    }

    public String read(Request req, Response res) {
        String key = req.params(":key");

        try {
            String value = jedis.get(key);

            if (value == null) {
                res.status(404); // Not Found
                return "No value found for key: " + key;
            } else {
                return value;
            }
        } catch (Exception e) {
            logger.error("Error reading key-value pair: " + e.getMessage());
            res.status(500); // Internal Server Error
            return "Error reading key-value pair.";
        }
    }

    public String update(Request req, Response res) {
        String key = req.queryParams("key");
        String value = req.queryParams("value");

        if (key == null || value == null) {
            res.status(400); // Bad Request
            return "Key or value cannot be null.";
        }

        try {
            jedis.set(key, value);
            logger.info("Key-value pair updated successfully: " + key);
            return "Key-value pair updated successfully.";
        } catch (Exception e) {
            logger.error("Error updating key-value pair: " + e.getMessage());
            res.status(500); // Internal Server Error
            return "Error updating key-value pair.";
        }
    }

    public String delete(Request req, Response res) {
        String key = req.params(":key");

        try {
            Long result = jedis.del(key);

            if (result == 1) {
                logger.info("Key deleted successfully: " + key);
                return "Key deleted successfully.";
            } else {
                res.status(404); // Not Found
                return "No value found for key: " + key;
            }
        } catch (Exception e) {
            logger.error("Error deleting key-value pair: " + e.getMessage());
            res.status(500); // Internal Server Error
            return "Error deleting key-value pair.";
        }
    }
}
