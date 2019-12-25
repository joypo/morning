package com.example.demo.drools.controller;

import com.example.demo.drools.entity.User;
import com.example.demo.drools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("index")
    public List<User> index() {
        userService.save();
        return userService.findAll();
    }
}
