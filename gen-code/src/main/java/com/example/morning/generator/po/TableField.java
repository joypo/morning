package com.example.morning.generator.po;

import lombok.Data;

/**
 * @author sunx
 * @date 2020-01-08
 */
@Data
public class TableField {
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    /**
     * user_name
     */
    private String name;
    /**
     * 小写驼峰 userName
     */
    private String lowerCamelName;
    /**
     * 大写驼峰 UserName
     */
    private String upperCamelName;
    private String type;
    private String propertyName;
    private String comment;
}
