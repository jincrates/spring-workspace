package me.jincrates.redis.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
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
        System.out.println(logger);

        for (int i = 0; i < 100; i++) {
            assertTrue(logger.log("This is test log message 1") > 0);

            try {
                Thread.sleep(random.nextInt(50));
            }
            catch (InterruptedException e) {
                //do nothing
            }
        }
    }
}