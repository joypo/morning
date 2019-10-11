package com.example.rate.limiter.controller;

import com.example.rate.limiter.annotation.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2019-10-10
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Limit(period = 60, count = 10)
    @GetMapping("index")
    public String index() {
        return "成功";
    }

    @GetMapping("test")
    public String test() {
        return "成功test";
    }
}
