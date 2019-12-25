package com.example.demo.drools.controller;

import com.example.demo.drools.bean.Person;
import com.example.demo.drools.entity.Rules;
import com.example.demo.drools.service.RulesService;
import com.example.demo.drools.service.RulesTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@RestController
@RequestMapping("drools")
public class DroolsController {

    @Autowired
    private RulesTestService rulesTestService;

    @Autowired
    private RulesService rulesService;

    @GetMapping("all")
    public List<Rules> all() {
        return rulesTestService.findAll();
    }

    @PostMapping("add")
    public String add(@RequestBody Rules rules) {
        rulesTestService.save(rules);
        return "操作成功";
    }

    @PostMapping("test")
    public void test(@RequestBody List<Person> p) {
        Integer id = 3;
        rulesService.test(id, p);
    }

    @PostMapping("test1")
    public void test1(@RequestBody List<Map<String,Object>> p) {
        Integer id = 3;
        rulesService.test1(id, p);
    }
}
