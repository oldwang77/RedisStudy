package com.kuang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.pojo.User;
import com.kuang.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.set("name","kuangshen");
        String name = redisUtil.get("name");
        System.out.println(name);
    }

    @Test
    void contextLoads() {
        // redisTemplate 操作不同的数据类型，api和我们的指令是一样的
        // opsForValue 操作字符串 类似String
        // opsForList 操作List 类似List
        // opsForHah

        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务和基本的CRUD

        // 获取连接对象
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();
        //connection.flushAll();

        redisTemplate.opsForValue().set("mykey","kuangshen");
        System.out.println(redisTemplate.opsForValue().get("mykey"));

    }

    @Test
    public void test() throws JsonProcessingException {
        // 真实开发一般都使用json传递数据
        User user = new User("狂神说", 3);
        String jsonUser = new ObjectMapper().writeValueAsString(user);  //使用json进行序列化
        redisTemplate.opsForValue().set("user",jsonUser);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
