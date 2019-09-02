package com.example.morning.demo.service.impl;

import com.example.morning.demo.dao.TestDao;
import com.example.morning.demo.domain.Test;
import com.example.morning.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunx
 * @date 2019-08-21
 * @description
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> list() {
        try {
            return testDao.test1();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
