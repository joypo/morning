package com.example.redis.distributed.lock.starter.demo;

import com.example.redis.distributed.lock.config.EnableRedissonLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRedissonLock
@SpringBootApplication
public class RedisDistributedLockStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDistributedLockStarterDemoApplication.class, args);
    }

}
