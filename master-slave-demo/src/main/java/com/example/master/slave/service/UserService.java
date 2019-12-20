package com.example.master.slave.service;

import com.example.master.slave.annotation.Master;
import com.example.master.slave.annotation.Slave;
import com.example.master.slave.dao.UserDao;
import com.example.master.slave.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-18
 * @description
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Slave
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Master
    public void insert() {
        User u = new User();
        u.setPwd("1234");
        u.setUserName("user" + Math.random());
        userDao.save(u);
    }
}
