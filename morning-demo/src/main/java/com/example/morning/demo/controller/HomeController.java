package com.example.morning.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunx
 * @date 2019-08-16
 * @description
 */
@RestController
@RequestMapping("home")
@Api(tags = "主页")
public class HomeController {

    @GetMapping("index")
    @ApiOperation("cesh")
    public String index(HttpServletRequest request) {
        String s = "23";
        return "index";
    }
}
