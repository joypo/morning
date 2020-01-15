package com.example.demo.drools.demo;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-06
 * @description
 */
@Data
public class ActionRuleSub {
    /**
     * 或且关系
     */
    private Integer andOrFlag;

    private List<ActionRuleItem> items;
}
