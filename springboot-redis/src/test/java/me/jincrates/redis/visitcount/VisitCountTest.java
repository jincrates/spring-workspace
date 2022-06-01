package me.jincrates.redis.visitcount;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisitCountTest {
    static JedisHelper helper;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterEach
    public void testDownAfterClass() throws  Exception {
        helper.destroyPool();
    }

    @Test
    public void testAddVisit() {
        VisitCount visitCount = new VisitCount(helper);
        assertNotNull(visitCount);

        assertTrue(visitCount.addVisit("52") > 0);
        assertTrue(visitCount.addVisit("180") > 0);
        assertTrue(visitCount.addVisit("554") > 0);
    }

    @Test
    public void testGetVisitCount() {
        VisitCount visitCount = new VisitCount(helper);
        assertNotNull(visitCount);

        //이벤트 아이디
        List<String> result = visitCount.getVisitCount("52", "180", "554");
        assertNotNull(result);
        assertEquals(3, result.size());

        long sum = 0;
        for (String count: result) {
            sum += Long.parseLong(count);
        }

        String totalCount = visitCount.getVisitTotalCount();

        assertEquals(String.valueOf(sum), totalCount);
    }
}