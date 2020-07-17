package com.example.demo.netty.websocket.controller;

import com.example.demo.netty.websocket.config.BizConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2020/7/16
 */
@RestController
public class SampleController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * redis生产者测试
     *
     * @param data
     * @return
     */
    @GetMapping("/send1")
    String send1(String data) {
        redisTemplate.convertAndSend(BizConfiguration.TOPIC_PATTERN, data);
        return "success";
    }

    /**
     * redis生产者测试
     *
     * @param data
     * @return
     */
    @GetMapping("/send2")
    String send2(String data) {
        redisTemplate.convertAndSend("testkafka1", data);
        return "success";
    }
}
