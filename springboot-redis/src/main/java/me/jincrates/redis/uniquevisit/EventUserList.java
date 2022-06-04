package me.jincrates.redis.uniquevisit;

import me.jincrates.redis.example.JedisHelper;
import redis.clients.jedis.Jedis;

//루아 스크립트 등록과 실행
public class EventUserList {
    private final Jedis jedis;
    private String sh1;

    public EventUserList(JedisHelper helper) {
        this.jedis = helper.getConnection();
    }

    public String initLuaScript() {
        StringBuilder builder = new StringBuilder();
        builder.append("local function hasbit(x, p) ");
        builder.append("return x % (p + p) >= p ");
        builder.append("end ");
        builder.append("local mask = {128, 64, 32, 16, 8, 4, 2, 1} ");
        builder.append("local len = redis.call('strlen', 'uv:event') ");
        builder.append("local userList = redis.call('get', 'uv:event') ");
        builder.append("local table = {} ");
        builder.append("local idx = 1 ");
        builder.append("for i = 1, len, 1 do ");
        builder.append("    if string.byte(userList, i) ~= 0 then ");
        builder.append("        for j = 1, 8, 1 do ");
        builder.append("            if hasbit(string.byte(userList, i), mask[j]) then ");
        builder.append("                table[idx] = tostring((i-1) * 8 + (j - 1)) ");
        builder.append("                idx = idx + 1 ");
        builder.append("            end ");
        builder.append("        end ");
        builder.append("    end ");
        builder.append("end ");
        builder.append("return table ");

        return this.sh1 = this.jedis.scriptLoad(builder.toString());
    }

    /**
     * 레디스 서버에 등록된 루아 스크립트를 실행한다.
     * @return 루아 스크립트의 실행 결과
     */
    public Object getEventUserList() {
        initLuaScript();
        return this.jedis.evalsha(this.sh1);
    }
}
