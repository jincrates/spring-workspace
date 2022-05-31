package me.jincrates.redis.logger;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogReceiverTest {
    static JedisHelper helper;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterEach
    public void tearDownAfterClass() throws Exception {
        helper.destroyPool();
    }

    @Test
    public void testLogger() {
        LogReceiver receiver = new LogReceiver();
        receiver.start();
    }
}