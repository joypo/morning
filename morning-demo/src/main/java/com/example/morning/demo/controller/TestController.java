package com.example.morning.demo.controller;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.service.UidGenService;
import com.example.morning.demo.base.HttpResult;
import com.example.morning.demo.domain.Test;
import com.example.morning.demo.domain.User;
import com.example.morning.demo.exception.BizRuntimeException;
import com.example.morning.demo.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestService testService;

    @Resource
    private UidGenerator defaultUidGenerator;

    @Resource
    private UidGenerator cachedUidGenerator;

    /**
     * @return
     * @description
     * @params
     * @date
     */
    @GetMapping("tt")
    public void test() {

    }

    @PostMapping("t1")
    public List<User> list() {
        List<User> list = new ArrayList<>();
//        list.add(User.builder().id(1L).nickName("张三").sex(0).build());
//        list.add(User.builder().id(2L).nickName("李四").sex(1).build());
        return list;
    }

    @GetMapping("t2")
    public HttpResult test1(String id) {
        if (Objects.equals("1", id)) {
            throw new BizRuntimeException("出错了");
        }
//        HttpResult re = new HttpResult(GlobalErrorEnum.SYSTEM_ERROR, "失败", User.builder().id(2L).nickName("李四").sex(1).build());
        return null;
    }

    @GetMapping("t3")
    public String test2(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 200, TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get(key).toString();
    }

    @GetMapping("t4")
    public List<Test> test3() {
        return testService.list();
    }

    @GetMapping("t5")
    public String getUid() {
        return "id=" + defaultUidGenerator.getUID();
    }
}
