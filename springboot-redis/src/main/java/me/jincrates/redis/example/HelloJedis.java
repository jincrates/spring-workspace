package me.jincrates.redis.example;

import redis.clients.jedis.Jedis;

public class HelloJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        
        String result = jedis.set("redis-book", "Hello redis!");
        System.out.println(result);
        System.out.println(jedis.get("redis-book"));
    }
}
