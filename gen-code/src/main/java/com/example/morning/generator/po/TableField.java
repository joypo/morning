package com.example.morning.generator.po;

import lombok.Data;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 表明细信息
 */
@Data
public class TableField {
    /**
     * 是否是主键
     */
    private boolean keyFlag;
    /**
     * 列名 user_name
     */
    private String name;
    /**
     * 类型 varchar
     */
    private String type;
    /**
     * 备注信息
     */
    private String comment;

    /**
     * 字段名驼峰命名 userName
     */
    private String lowerCamelName;

    /**
     * 数据库类型对应的字段类型 String
     */
    private String propertyName;
}
