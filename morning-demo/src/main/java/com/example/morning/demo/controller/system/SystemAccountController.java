package com.example.morning.demo.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2019-08-21
 * @description
 */
@RestController
@RequestMapping("system/account")
@Api(tags = "系统管理")
public class SystemAccountController {

    @PostMapping("login")
    @ApiOperation("登录")
    public String login() {
        return "登录成功";
    }
}
