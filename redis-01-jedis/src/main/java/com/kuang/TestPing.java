package com.kuang;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // 1 new Jedis对象即可
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // jedis所有指令就是我们之前学习国的所有指令
        System.out.println(jedis.ping());
    }
}
