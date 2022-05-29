package me.jincrates.redis.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//제디스와 파이프라인
public class PipelineDataJedis {
    //작성할 데이터 건수: 천만 건
    private static final int TOTAL_OPERATIONS = 10000000;

    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();

        long start = System.currentTimeMillis();
        
        String key, value;
        Pipeline p = jedis.pipelined();
        for (int i = 0; i <= TOTAL_OPERATIONS; i++) {
            key = value = String.valueOf("key" + (100000000 + i));
            p.set(key, value);
        }
        p.sync();
        
        jedis.disconnect();
        
        long elapsed = now() - start;
        System.out.println("초당 처리 건수 " + (TOTAL_OPERATIONS / elapsed * 1000f));
        System.out.println("소요 시간 " + (elapsed / 1000f) + "초");
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
