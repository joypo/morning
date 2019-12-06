package com.example.morning.demo.service.impl;

import com.example.morning.demo.base.core.AbstractService;
import com.example.morning.demo.dao.TbTest1Dao;
import com.example.morning.demo.domain.TbTest1;
import com.example.morning.demo.service.TbTest1Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author sunx
 * @date 2019/12/06
 * @description 测试ServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbTest1ServiceImpl extends AbstractService<TbTest1> implements TbTest1Service {
    @Resource
    private TbTest1Dao tbTest1Dao;
}
