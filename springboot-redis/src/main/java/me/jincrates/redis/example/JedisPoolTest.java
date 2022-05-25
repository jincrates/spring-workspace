package me.jincrates.redis.example;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class JedisPoolTest {

    public static void main(String[] args) {
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        jedisPoolConfig.setMaxTotal(20); //poll에서 할당 할 수 있는 최대 연결 수

        JedisPool pool = new JedisPool("127.0.0.1", 6379);
        Jedis firstClient = pool.getResource();
        firstClient.hset("info:진크", "이름", "진크");
        firstClient.hset("info:진크", "생일", "1994-04-20");

        Jedis secondClient = pool.getResource();
        Map<String, String> result = secondClient.hgetAll("info:진크");
        System.out.println("이름 : " + result.get("이름"));
        System.out.println("생일 : " + result.get("생일"));

        pool.returnResource(firstClient);
        pool.returnResource(secondClient);
        pool.destroy();
    }
}
