package com.example.demo11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2020/8/13
 */
@RestController
public class PlayController {

    @GetMapping("paly")
    public String paly() throws InterruptedException {
        Thread.sleep(8000);
        return "hey,play with me!";
    }
}
