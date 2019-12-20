package com.example.master.slave.controller;

import com.example.master.slave.entity.User;
import com.example.master.slave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-18
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("save")
    public void save() {
        userService.insert();
    }
}
