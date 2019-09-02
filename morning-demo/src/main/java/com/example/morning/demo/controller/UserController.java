package com.example.morning.demo.controller;

import com.example.morning.demo.base.HttpResult;
import com.example.morning.demo.base.core.PageSearchParam;
import com.example.morning.demo.domain.User;
import com.example.morning.demo.domain.vo.UserVO;
import com.example.morning.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunx
 * @date 2019-08-26
 * @description
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public HttpResult add(@RequestBody User user) {
        userService.save(user);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestParam String id) {
        userService.deleteById(Long.valueOf(id));
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody User user) {
        userService.update(user);
        return HttpResult.success();
    }

    @GetMapping
    public HttpResult<User> detail(@RequestParam String id) {
        return HttpResult.successGenerics(userService.findById(Long.valueOf(id)));
    }

    @PostMapping("page")
    public HttpResult<PageInfo<User>> list(@RequestBody PageSearchParam<UserVO> searchVO) {
        PageHelper.startPage(searchVO.getPage(), searchVO.getSize());
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return HttpResult.successGenerics(pageInfo);
    }
}
