package com.example.gen.po;

import java.util.List;

/**
 * @author sunx
 * @date 2019-08-23
 * @description 表信息
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public List<TableField> getAllFields() {
        return allFields;
    }

    public void setAllFields(List<TableField> allFields) {
        this.allFields = allFields;
    }

    public boolean isHasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean isHasDecimal() {
        return hasDecimal;
    }

    public void setHasDecimal(boolean hasDecimal) {
        this.hasDecimal = hasDecimal;
    }

    public boolean isKeyAutoIncr() {
        return keyAutoIncr;
    }

    public void setKeyAutoIncr(boolean keyAutoIncr) {
        this.keyAutoIncr = keyAutoIncr;
    }
}
