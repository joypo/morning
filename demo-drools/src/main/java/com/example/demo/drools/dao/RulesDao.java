package com.example.demo.drools.dao;

import com.example.demo.drools.entity.Rules;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
public interface RulesDao {
    @Select("select * from drools_rule where 1=1")
    List<Rules> findAll();

    void save(Rules rules);

    @Select("select * from drools_rule where id=#{0}")
    Rules getById(Integer id);

    void setRule(@Param("name") String name, @Param("rule") String rule);

    @Delete("delete from drools_rule where id=#{0}")
    void deleteRule(Integer id);

    void updateRule(Integer id, String name, String rule);
}
