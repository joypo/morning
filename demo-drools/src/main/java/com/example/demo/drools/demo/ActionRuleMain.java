package com.example.demo.drools.demo;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-06
 * @description 行为集合规则
 */
@Data
public class ActionRuleMain {
    /**
     * 或且关系
     */
    private Integer andOrFlag;

    private List<ActionRuleSub> subList;
}
