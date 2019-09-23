package com.example.id.generator.demo.controller;

import com.example.id.generator.UidGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunx
 * @date 2019-09-23
 * @description
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @Resource
    private UidGenerator cachedUidGenerator;

    @GetMapping("id")
    public String getId(){
        long uid = cachedUidGenerator.getUID();
        return String.valueOf(uid);
    }
}
