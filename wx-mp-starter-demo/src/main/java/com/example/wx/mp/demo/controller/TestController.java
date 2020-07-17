package com.example.wx.mp.demo.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunx
 * @date 2020-03-31
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("getAccessToken")
    public List<WxUserTag> getAccessToken() throws Exception {
        return wxMpService.getUserTagService().tagGet();
    }
}
