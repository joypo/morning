package com.example.demo.drools.demo;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-06
 * @description 行为明细item
 */
@Data
public class ActionRuleItem {
    /**
     * 或且关系，默认且
     */
    private Integer andOrFlag;

    /**
     * 达标数量  为0时表示未做过 默认为1
     */
    private Integer number;

    /**
     * 筛选指标集合
     */
    private List<Index> indexSet;
}
