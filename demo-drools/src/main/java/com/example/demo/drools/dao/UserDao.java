package com.example.demo.drools.dao;

import com.example.demo.drools.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
public interface UserDao {
    @Select("select * from tb_user where 1=1")
    List<User> findAll();

    void save(User user);
}
