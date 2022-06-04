package me.jincrates.redis.uniquevisit;

import me.jincrates.redis.example.JedisHelper;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class UniqueVisitV2 {
    private final Jedis jedis;
    private static final String KEY_PAGE_VIEW = "page:view:";
    private static final String KEY_UNIQUE_VISITOR = "unique:visitors:";

    public UniqueVisitV2(JedisHelper helper) {
        this.jedis = helper.getConnection();
    }

    /**
     * 특정 사용자의 순 방문 횟수와 누적 방문 횟수를 증가시킨다.
     * @param userNo 사용자 번호
     */
    public void visit(int userNo) {
        Pipeline p = this.jedis.pipelined();
        p.incr(KEY_PAGE_VIEW + getToday());
        p.setbit(KEY_UNIQUE_VISITOR + getToday(), userNo, true);
        p.sync();
    }

    /**
     * 테스트를 위하여 주어진 날짜의 순 방문 횟수와 누적 방문 횟수를 증가시킨다.
     * @param userNo 사용자 번호
     * @param date 테스트를 위한 날짜
     */
    public void visit(int userNo, String date) {
        Pipeline p = this.jedis.pipelined();
        p.incr(KEY_PAGE_VIEW + date);
        p.setbit(KEY_UNIQUE_VISITOR + date, userNo, true);
        p.sync();
    }

    /**
     * 요청된 날짜의 누적 방문자 수를 조회한다.
     * @param date 조회 대상 날짜
     * @return 누적 방문자 수
     */
    public int getPVCount(String date) {
        int result = 0;
        try {
            result = Integer.parseInt(jedis.get(KEY_PAGE_VIEW + date));
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    /**
     * 요청된 날짜의 순 방문자 수를 조회한다.
     * @param date 조회 대상 날짜
     * @return 순 방문자 수
     */
    public Long getUVCount(String date) {
        return jedis.bitcount(KEY_UNIQUE_VISITOR + date);
    }

    /**
     * 주어진 기간의 순 방문자 수를 계산한다.
     * @param dateList 순 방문자를 구할 기간
     * @return 주어진 기간의 순 방문자 수
     */
    public Long getUVSum(String[] dateList) {
        String[] keyList = new String[dateList.length];
        int i = 0;
        for (String item : dateList) {
            keyList[i] = KEY_UNIQUE_VISITOR + item;
            i++;
        }

        jedis.bitop(BitOP.AND, "uv:event", keyList);
        return jedis.bitcount("uv:event");
    }

    private String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
