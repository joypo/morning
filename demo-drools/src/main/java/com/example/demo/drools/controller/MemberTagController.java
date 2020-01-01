package com.example.demo.drools.controller;

import com.example.demo.drools.service.MemberTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-27
 * @description
 */
@RestController
@RequestMapping("tag")
public class MemberTagController {
    @Autowired
    private MemberTagService memberTagService;

    @GetMapping("test")
    public List<Integer> test() {
        return memberTagService.validateTag();
    }
}
