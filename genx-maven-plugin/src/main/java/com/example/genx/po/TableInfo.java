package com.example.genx.po;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 表信息
 */
@Data
public class TableInfo {
    /**
     * 表名
     */
    private String name;

    /**
     * 表备注信息
     */
    private String comment;

    /**
     * 字段 排除了公共字段
     */
    private List<TableField> fields;

    /**
     * 所有字段
     */
    private List<TableField> allFields;

    /**
     * 是否有日期格式的字段
     */
    private boolean hasDate;

    /**
     * 是否有decimal的字段
     */
    private boolean hasDecimal;

    /**
     * 主键自增
     */
    private boolean keyAutoIncr;
}
