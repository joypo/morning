package com.example.morning.demo.controller;

import com.example.morning.demo.base.HttpResult;
import com.example.morning.demo.base.core.PageSearchParam;
import com.example.morning.demo.domain.TbTest1;
import com.example.morning.demo.domain.search.TbTest1SearchVO;
import com.example.morning.demo.service.TbTest1Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunx
 * @date 2019/12/06
 * @description 测试Controller
 */
@RestController
@RequestMapping("tbTest1")
@Slf4j
public class TbTest1Controller {

    @Resource
    private TbTest1Service tbTest1Service;

    @PostMapping
    public HttpResult add(@RequestBody TbTest1 tbTest1) {
        tbTest1Service.save(tbTest1);
        return HttpResult.success();
    }

    @DeleteMapping
    public HttpResult delete(@RequestParam String id) {
        tbTest1Service.deleteById(Long.valueOf(id));
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult update(@RequestBody TbTest1 tbTest1) {
        tbTest1Service.update(tbTest1);
        return HttpResult.success();
    }

    @GetMapping
    public HttpResult<TbTest1> detail(@RequestParam String id) {
        return HttpResult.successGenerics(tbTest1Service.findById(Long.valueOf(id)));
    }

    @PostMapping("page")
    public HttpResult<PageInfo<TbTest1>> list(@RequestBody PageSearchParam<TbTest1SearchVO> searchVO) {
        PageHelper.startPage(searchVO.getPage(), searchVO.getSize());
        List<TbTest1> list = tbTest1Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return HttpResult.successGenerics(pageInfo);
    }
}
