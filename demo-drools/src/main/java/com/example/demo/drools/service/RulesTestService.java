package com.example.demo.drools.service;

import com.example.demo.drools.dao.RulesDao;
import com.example.demo.drools.entity.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Service
public class RulesTestService {

    @Autowired
    private RulesDao rulesDao;

    public List<Rules> findAll() {
        return rulesDao.findAll();
    }

    public void save(Rules rules) {
        rules.setVisible(1);
        rules.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        rulesDao.save(rules);
    }
}
