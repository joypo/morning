package com.example.gen.po;


/**
 * @author sunx
 * @date 2019-08-23
 * @description 表明细信息
 */
public class TableField {
    /**
     * 是否是主键
     */
    private boolean keyFlag;
    /**
     * 主键是否自增
     */
    private boolean keyAutoIncr;
    /**
     * 列名 user_name
     */
    private String name;
    /**
     * 字段名驼峰命名 userName
     */
    private String lowerCamelName;

    /**
     * 字段名驼峰命名 UserName
     */
    private String upperCamelName;

    /**
     * 类型 varchar
     */
    private String type;
    /**
     * 备注信息
     */
    private String comment;

    /**
     * 数据库类型对应的字段类型 String
     */
    private String propertyName;

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isKeyAutoIncr() {
        return keyAutoIncr;
    }

    public void setKeyAutoIncr(boolean keyAutoIncr) {
        this.keyAutoIncr = keyAutoIncr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getLowerCamelName() {
        return lowerCamelName;
    }

    public void setLowerCamelName(String lowerCamelName) {
        this.lowerCamelName = lowerCamelName;
    }

    public String getUpperCamelName() {
        return upperCamelName;
    }

    public void setUpperCamelName(String upperCamelName) {
        this.upperCamelName = upperCamelName;
    }
}
