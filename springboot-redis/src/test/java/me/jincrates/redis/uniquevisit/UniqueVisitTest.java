package me.jincrates.redis.uniquevisit;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueVisitTest {
    static JedisHelper helper;
    private UniqueVisit uniqueVisit;
    private static final int VISIT_COUNT = 1000;
    private static final int TOTAL_USER = 10000000;
    private static final String TEST_DATE = "19500101";
    static final Random random = new Random();

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterEach
    public void testDownAfterClass() throws  Exception {
        helper.destroyPool();
    }

    @BeforeEach
    public void setUp() throws Exception {
        uniqueVisit = new UniqueVisit(helper);
        assertNotNull(uniqueVisit);
    }

    @Test
    public void testRandomPV() {
        int pv = uniqueVisit.getPVCount(getToday());
        for (int i = 0; i < VISIT_COUNT; i++) {
            uniqueVisit.visit(random.nextInt(TOTAL_USER));
        }
    }

    @Test
    public void testInvalidVP() {
        assertEquals(0, uniqueVisit.getUVCount(TEST_DATE));
        assertEquals(Long.valueOf(0), uniqueVisit.getUVCount(TEST_DATE));
    }

    @Test
    public void testPV() {
        int result = uniqueVisit.getPVCount(getToday());
        uniqueVisit.visit(65487);

        assertEquals(result + 1, uniqueVisit.getUVCount(getToday()));
    }

    @Test
    public void testUV() {
        uniqueVisit.visit(65487);
        Long result = uniqueVisit.getUVCount(getToday());
        uniqueVisit.visit(65487);

        assertEquals(result, uniqueVisit.getUVCount(getToday()));
    }

    private String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}