package me.jincrates.redis.uniquevisit;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueVisitV2Test {
    static JedisHelper helper;
    private UniqueVisitV2 uniqueVisit;
    private static final int TOTAL_USER = 10000000;

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
        uniqueVisit = new UniqueVisitV2(helper);
        assertNotNull(this.uniqueVisit);

        this.uniqueVisit.visit(7, "20220604");
        this.uniqueVisit.visit(11, "20220604");
        this.uniqueVisit.visit(15, "20220604");
        this.uniqueVisit.visit(TOTAL_USER, "20220604");

        this.uniqueVisit.visit(3, "20220605");
        this.uniqueVisit.visit(7, "20220605");
        this.uniqueVisit.visit(9, "20220605");
        this.uniqueVisit.visit(11, "20220605");
        this.uniqueVisit.visit(15, "20220605");
        this.uniqueVisit.visit(TOTAL_USER, "20220605");

        this.uniqueVisit.visit(7, "20220606");
        this.uniqueVisit.visit(12, "20220606");
        this.uniqueVisit.visit(13, "20220606");
        this.uniqueVisit.visit(15, "20220606");
        this.uniqueVisit.visit(TOTAL_USER, "20220606");
    }

    @Test
    public void testUVSum() {
        String[] dateList1 = {"20220604", "20220605", "20220606"};
        assertEquals(Long.valueOf(3), this.uniqueVisit.getUVSum(dateList1));

        String[] dateList2 = {"20220604", "20220605", "20220606", "20110604"};
        assertEquals(Long.valueOf(2), this.uniqueVisit.getUVSum(dateList2));
    }
}