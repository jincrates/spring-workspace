package me.jincrates.redis.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//천만 건의 데이터를 전송하는 예제
public class RedisInsertTest {
    //전체 데이터 건수
    private static final float TOTAL_OP = 10000000f;

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        String key, value;
        long start = now();
        long loopTime = now();

        for (int i = 1; i <= TOTAL_OP; i++) {
            key = value = String.valueOf("key" + (100000000 + i));
            jedis.set(key, value);
        }

        long elapsed = now() - start;
        System.out.println("초당 처리 건수 " + (TOTAL_OP / elapsed * 1000f));
        System.out.println("소요 시간 " + (elapsed / 1000f) + "초");
    }



    private static long now() {
        return System.currentTimeMillis();
    }
}
