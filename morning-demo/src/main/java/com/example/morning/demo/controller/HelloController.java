package com.example.morning.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2019-08-24
 * @description
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("index")
    public String hello() {
        return "hello world";
    }
}
