package me.jincrates.redis.like;

import me.jincrates.redis.example.JedisHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LikePostingTest {
    static JedisHelper helper;
    private LikePosting likePosting;
    private static Random random = new Random();
    private static final int POSTING_COUNT = 20;
    private static final int TESTER = random.nextInt(10000000);
    private static final String[] POSTLIST = new String[POSTING_COUNT];

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
        for (int i = 0; i < POSTING_COUNT; i++) {
            POSTLIST[i] = String.valueOf(i + 1);
        }
    }

    @AfterEach
    public void testDownAfterClass() throws  Exception {
        helper.destroyPool();
    }

    @BeforeEach
    public void setUp() throws Exception {
        this.likePosting = new LikePosting(helper);
        assertNotNull(this.likePosting);
    }

    @Test
    public void testLike() {
        String postingNo = String.valueOf(random.nextInt(POSTING_COUNT));
        if (this.likePosting.isLiked(postingNo, String.valueOf(TESTER))) {
            this.likePosting.unlike(postingNo, String.valueOf((TESTER)));
        }
        assertTrue(this.likePosting.like(postingNo, String.valueOf(TESTER)));
    }

    @Test
    public void testUnLike() {
        String postingNo = String.valueOf(random.nextInt(POSTING_COUNT));
        if (this.likePosting.isLiked(postingNo, String.valueOf(TESTER))) {
            this.likePosting.unlike(postingNo, String.valueOf((TESTER)));
        } else {
            assertTrue(this.likePosting.like(postingNo, String.valueOf(TESTER)));
            assertTrue(this.likePosting.unlike(postingNo, String.valueOf(TESTER)));
        }
    }

    @Test
    public void testGetLikeCount() {
        String postingNo = String.valueOf(random.nextInt(POSTING_COUNT));
        if (this.likePosting.isLiked(postingNo, String.valueOf(TESTER))) {
            this.likePosting.unlike(postingNo, String.valueOf((TESTER)));
        }

        Long prevCount = this.likePosting.getLikeCount(postingNo);
        this.likePosting.like(postingNo, String.valueOf(TESTER));
        assertEquals(this.likePosting.getLikeCount(postingNo), Long.valueOf(prevCount + 1));
    }

    @Test
    public void testGetLikeCountList() {
        List<Long> countList = this.likePosting.getLikeCountList(POSTLIST);
        assertEquals(countList.size(), POSTING_COUNT);
    }

    @Test
    public void testIsLiked() {
        String postingNo = String.valueOf(random.nextInt(POSTING_COUNT));
        this.likePosting.like(postingNo, String.valueOf((TESTER)));
        assertTrue(this.likePosting.isLiked(postingNo, String.valueOf(TESTER)));
    }

    @Test
    public void testDeleteLikeInfo() {
        String postingNo = "A1234567890";
        this.likePosting.like(postingNo, String.valueOf(TESTER));
        assertTrue(this.likePosting.deleteLikeInfo(postingNo));
    }

    @Test
    public void testRandomLike() {
        for (int i = 0; i < POSTING_COUNT; i++) {
            String sudoRandomUser = String.valueOf(random.nextInt(10000000));
            this.likePosting.like(String.valueOf(i), sudoRandomUser);
        }
    }

}