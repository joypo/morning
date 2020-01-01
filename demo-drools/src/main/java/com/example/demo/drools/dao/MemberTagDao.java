package com.example.demo.drools.dao;

import com.example.demo.drools.entity.MemberTag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunx
 * @date 2019-12-27
 * @description
 */
public interface MemberTagDao {
    @Select("select * from dmp_member_tag where 1=1")
    List<MemberTag> findAll();
}
