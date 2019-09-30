package com.example.redis.distributed.lock.starter.demo.controller;

import com.example.redis.distributed.lock.RedissonLock;
import com.example.redis.distributed.lock.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2019-09-30
 * @description
 */
@RestController
@RequestMapping("page")
@Slf4j
public class PageController {

    @Autowired
    RedissonLock redissonLock;

    @GetMapping("test")
    @DistributedLock(value = "redis:lock:1",expireSeconds = 10)
    public String test() throws InterruptedException {
        log.info("开始");
        Thread.sleep(30000);
        return "成功";
    }
}
