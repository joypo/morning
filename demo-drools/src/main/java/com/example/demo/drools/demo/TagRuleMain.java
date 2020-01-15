package com.example.demo.drools.demo;

import lombok.Data;

/**
 * @author sunx
 * @date 2020-01-06
 * @description
 */
@Data
public class TagRuleMain {
    /**
     * 或且关系
     */
    private Integer andOrFlag;

    /**
     * 用户属性
     */
    private MemberPropertyRuleMain memberPropertyRuleMain;

    /**
     * 行为规则
     */
    private ActionRuleMain actionRuleMain;
}
