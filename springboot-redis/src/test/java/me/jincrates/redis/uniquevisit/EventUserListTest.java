package me.jincrates.redis.uniquevisit;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EventUserListTest {
    static JedisHelper helper;
    private static EventUserList eventUserList;
    private static UniqueVisitV2 uniqueVisitV2;
    private static final int TOTAL_USER = 10000000;
    private static final int DEST_USER = 10000;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
        EventUserListTest.eventUserList = new EventUserList(helper);
        uniqueVisitV2 = new UniqueVisitV2(helper);
    }

    @AfterEach
    public void testDownAfterClass() throws  Exception {
        helper.destroyPool();
    }

    @BeforeEach
    public void setUp() throws Exception {
        assertNotNull(EventUserListTest.eventUserList);
    }

    @Test
    public void testSetUp() {
        Random random = new Random();
        for (int i = 0; i < DEST_USER; i++) {
            int tempuser = random.nextInt(TOTAL_USER);
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220608");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220609");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220610");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220611");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220612");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220613");
            EventUserListTest.uniqueVisitV2.visit(tempuser, "20220614");
        }
    }
    
    @Test
    public void initLuaScript() {
        assertEquals("21fdff62111580b7d4c4829294727e12a19520be", eventUserList.initLuaScript());
    }
    
    @Test
    public void runLuaScript() {
        String[] dateList1 = {"20220608", "20220609", "20220610", "20220611", "20220612", "20220613", "20220614"};
        Long count = EventUserListTest.uniqueVisitV2.getUVSum(dateList1);

        List<String> list = (List<String>) EventUserListTest.eventUserList.getEventUserList();
        assertEquals(count, Long.valueOf(list.size()));
        
        for (String item : list) {
            System.out.println(item);
        }
    }
}