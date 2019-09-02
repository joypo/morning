package com.example.morning.demo.controller;

import com.example.morning.demo.base.HttpResult;
import com.example.morning.demo.base.core.PageSearchParam;
import com.example.morning.demo.domain.AuthUser;
import com.example.morning.demo.domain.search.AuthUserSearchVO;
import com.example.morning.demo.service.AuthUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunx
 * @date 2019/08/30
 * @description 用户Controller
 */
@RestController
@RequestMapping("authUser")
@Slf4j
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    @PostMapping
    public HttpResult add(@RequestBody AuthUser authUser) {
        authUserService.save(authUser);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestParam String id) {
        authUserService.deleteById(Long.valueOf(id));
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody AuthUser authUser) {
        authUserService.update(authUser);
        return HttpResult.success();
    }

    @GetMapping
    public HttpResult<AuthUser> detail(@RequestParam String id) {
        return HttpResult.successGenerics(authUserService.findById(Long.valueOf(id)));
    }

    @PostMapping("page")
    public HttpResult<PageInfo<AuthUser>> list(@RequestBody PageSearchParam<AuthUserSearchVO> searchVO) {
        PageHelper.startPage(searchVO.getPage(), searchVO.getSize());
        List<AuthUser> list = authUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return HttpResult.successGenerics(pageInfo);
    }
}
