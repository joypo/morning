package com.example.morning.generator.po;

import lombok.Data;

import java.util.List;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
public class TableInfo {
    private String name;
    private String comment;
    private String lowerCamelName;
    private String upperCamelName;
    /**
     * 是否有日期格式的字段
     */
    private boolean hasDate;

    /**
     * 是否有decimal的字段
     */
    private boolean hasDecimal;
    /**
     * 所有字段
     */
    private List<TableField> allFields;

    /**
     * 字段 排除了公共字段
     */
    private List<TableField> fields;
}
