package com.example.demo.drools.service;

import com.example.demo.drools.dao.UserDao;
import com.example.demo.drools.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void save() {
        User u = new User();
        u.setPwd("123");
        u.setUserName("user" + new Random().nextInt());
        userDao.save(u);
    }
}
