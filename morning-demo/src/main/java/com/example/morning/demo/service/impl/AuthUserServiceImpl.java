package com.example.morning.demo.service.impl;

import com.example.morning.demo.base.core.AbstractService;
import com.example.morning.demo.dao.AuthUserDao;
import com.example.morning.demo.domain.AuthUser;
import com.example.morning.demo.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author sunx
 * @date 2019/08/30
 * @description 用户ServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthUserServiceImpl extends AbstractService<AuthUser> implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;
}
