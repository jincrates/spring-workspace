package me.jincrates.redis.visitcount;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisitCountOfDayTest {
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
        assertTrue(visitCount.addVisit("52") > 0);
        assertTrue(visitCount.addVisit("180") > 0);
        assertTrue(visitCount.addVisit("554") > 0);

        VisitCountOfDay visitCountOfDay = new VisitCountOfDay(helper);
        assertTrue(visitCountOfDay.addVisit("52") > 0);
        assertTrue(visitCountOfDay.addVisit("180") > 0);
        assertTrue(visitCountOfDay.addVisit("554") > 0);
    }

    @Test
    public void testGetVisitCountByDate() {
        String[] dateList = {"20220601", "20220602", "20220603", "20220604"};
        VisitCountOfDay visitCountOfDay = new VisitCountOfDay(helper);
        List<String> result = visitCountOfDay.getVisitCountByDateDate("52", dateList);
        assertNotNull(result);
        System.out.println(result);
        assertEquals(4, result.size());
    }
}