package me.jincrates.redis.logger;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LogWriterTest {
    static JedisHelper helper;
    static LogWriter logger;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
        logger = new LogWriter(helper);
    }

    @AfterEach
    public void tearDownAfterClass() throws Exception {
        helper.destroyPool();
    }

    @Test
    public void testLogger() {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 100; i++) {
            assertTrue(logger.log(i + ", This is test log message") > 0);

            try {
                Thread.sleep(random.nextInt(50));
            }
            catch (InterruptedException e) {
                //do nothing
            }
        }
    }
}