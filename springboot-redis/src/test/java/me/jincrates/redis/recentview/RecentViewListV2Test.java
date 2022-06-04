package me.jincrates.redis.recentview;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RecentViewListV2Test {
    static JedisHelper helper;
    private RecentViewListV2 viewList;
    private static final String TESTER = "123";
    private int listMaxSize;

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
        this.viewList = new RecentViewListV2(helper, TESTER);
        assertNotNull(this.viewList);
        this.listMaxSize = this.viewList.getListMaxSize();
    }

    @Test
    public void testAdd() {
        for (int i = 1; i <= 50; i++) {
            this.viewList.add(String.valueOf(i));
        }
    }

    @Test
    public void checkMaxSize() {
        int storedSize = this.viewList.getRecentViewList().size();
        assertEquals(this.listMaxSize, storedSize);
    }

    @Test
    public void checkRecentSize() {
        int checkSize = 4;
        int redisSize = this.viewList.getRecentViewList(checkSize).size();
        assertEquals(checkSize, redisSize);
    }

    @Test
    public void checkProductNo() {
        this.viewList.add("50");
        assertEquals(this.viewList.getRecentViewList().size(), this.listMaxSize);
        Set<String> itemList = this.viewList.getRecentViewList(5);

        for (String item : itemList) {
            System.out.println(item);
        }

        String[] list = itemList.toArray(new String[0]);
        assertEquals("50", list[0]);
    }
}