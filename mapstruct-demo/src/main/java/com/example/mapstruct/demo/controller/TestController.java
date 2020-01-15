package com.example.mapstruct.demo.controller;

import com.example.mapstruct.demo.mapper.UserMapper;
import com.example.mapstruct.demo.po.UserPo;
import com.example.mapstruct.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2020-01-11
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("index")
    public void test() {
        UserVO userVO = new UserVO().setName("张三");

        UserPo po1 = userMapper.toPo(userVO);

        UserPo po2 = new UserPo().setId("12").setName("lisi").setSex("女");
        po2.setStatus("121");

        UserVO vo1 = userMapper.toVO(po2);

        System.out.println(vo1);
        System.out.println(po2);
    }
}
