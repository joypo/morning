package com.example.morning.demo.service.impl;

import com.example.morning.demo.base.core.AbstractService;
import com.example.morning.demo.dao.UserDao;
import com.example.morning.demo.domain.User;
import com.example.morning.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author sunx
 * @date 2019-08-26
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserDao userDao;
}
