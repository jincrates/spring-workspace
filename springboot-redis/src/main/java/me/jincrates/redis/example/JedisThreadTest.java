package me.jincrates.redis.example;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

//천만 건의 데이터를 다중 스레드로 전송하는 예제
public class JedisThreadTest {
    //전체 데이터 건수
    private static final float TOTAL_OP = 10000000f;
    private static final float THREAD = 5;  //프로그램 실행시 시작할 스레드 개수를 지정

    public static void main(String[] args) {
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        jedisPoolConfig.setMaxTotal(500); //poll에서 할당 할 수 있는 최대 연결 수

        JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 10000);

        final long start = now();
        //자바 프로그램이 종료될 때 실행되는 이벤트 스레드를 등록
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                long elapsed = now() - start;

                //전체 실행시간과 초당 전송 건소를 출력
                System.out.println("스레드 개수 " + THREAD + "개");
                System.out.println("초당 처리 건수 " + (TOTAL_OP / elapsed * 1000f) + "개");
                System.out.println("소요 시간 " + (elapsed / 1000f) + "초");
            }
        });

        JedisThreadTest test = new JedisThreadTest();
        for (int i = 0; i < THREAD; i++) {
            //지정된 스레드 개수만큼 스레드를 생성한다. 생성하는 스레드에 연결 출과 인덱스를 인자로 지정
            test.makeWorker(pool, i).start();
        }
    }

    private Thread makeWorker(final JedisPool pool, final int idx) {
        //실제 데이터를 전송할 스레드를 생성
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String key, value;
                Jedis jedis = pool.getResource();

                //지정된 스레드 인덱스에 해당하는 키를 생성하고 아니면 실행하지 않음
                //이 부분은 각 스레드가 천만 개의 요청을 공평하게 나누어 전송하게 한다.
                for (int i = 1; i <= TOTAL_OP; i++) {
                    if (i % THREAD == idx) {
                        key = value = String.valueOf("key" + (100000000 + i));
                        jedis.set(key, value);
                    }
                }

                pool.close();
            }
        });

        return thread;
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
