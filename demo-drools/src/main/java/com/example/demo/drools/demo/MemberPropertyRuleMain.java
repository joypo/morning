package com.example.demo.drools.demo;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-06
 * @description 用户属性规则
 */
@Data
public class MemberPropertyRuleMain {
    /**
     * 或且关系
     */
    private Integer andOrFlag;

    /**
     * 筛选指标集合
     */
    private List<Index> indexSet;
}
