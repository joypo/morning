package com.example.demo.drools.demo;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-06
 * @description 指标设置
 */
@Data
public class Index {
    /**
     * 指标编码
     */
    private String indexCode;

    /**
     * 指标类型
     */
    private Integer indexType;

    /**
     * 筛选类型
     */
    private Integer filterType;

    /**
     * 筛选明细
     */
    private List<Object> filterValue;
}
