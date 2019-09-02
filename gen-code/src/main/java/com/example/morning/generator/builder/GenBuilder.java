package com.example.morning.generator.builder;

import com.example.morning.generator.DataSourceConfig;
import com.example.morning.generator.QuerySQL;
import com.example.morning.generator.StrategyConfig;
import com.example.morning.generator.po.TableField;
import com.example.morning.generator.po.TableInfo;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author sunx
 * @date 2019-08-23
 * @description
 */
public class GenBuilder {

    /**
     * SQL连接
     */
    private Connection connection;

    /**
     * SQL语句类型
     */
    private QuerySQL querySQL;

    /**
     * 生成策略
     */
    private StrategyConfig strategy;

    /**
     * 数据库表信息
     */
    @Getter
    private List<TableInfo> tableInfoList;

    public static GenBuilder builder() {
        GenBuilder builder = new GenBuilder();
        builder.connection = new DataSourceConfig().getConn();
        builder.querySQL = QuerySQL.MYSQL;
        builder.tableInfoList = Lists.newArrayList();
        return builder;
    }

    public GenBuilder strategy(StrategyConfig strategy) {
        this.strategy = strategy;
        return this;
    }

    public GenBuilder build() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(querySQL.getTableCommentsSql());
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                String tableName = results.getString(querySQL.getTableName());
                if (StringUtils.isNotBlank(tableName) && validateTableName(tableName)) {
                    String tableComment = results.getString(querySQL.getTableComment());
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    tableInfo.setComment(tableComment);
                    tableInfo.setFields(getListFields(tableName));
                    tableInfo.setHasDecimal(false);
                    tableInfo.setHasDate(false);
                    for (TableField fieldInfo : tableInfo.getFields()) {
                        if (fieldInfo.getPropertyName().equals("BigDecimal")) {
                            tableInfo.setHasDecimal(true);
                            break;
                        }
                    }
                    for (TableField fieldInfo : tableInfo.getFields()) {
                        if (fieldInfo.getPropertyName().equals("Date")) {
                            tableInfo.setHasDate(true);
                            break;
                        }
                    }
                    this.tableInfoList.add(tableInfo);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 根据表名获取字段详情信息
     *
     * @param tableName
     * @return
     */
    private List<TableField> getListFields(String tableName) throws SQLException {
        List<TableField> list = Lists.newArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), tableName));
        ResultSet results = preparedStatement.executeQuery();
        while (results.next()) {
            TableField field = new TableField();
            String key = results.getString(querySQL.getFieldKey());
            // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
            boolean isId = StringUtils.isNotBlank(key) && key.toUpperCase().equals("PRI");
            // 处理ID
            field.setKeyFlag(isId);
            // 处理其它信息
            field.setName(results.getString(querySQL.getFieldName()));
            field.setType(results.getString(querySQL.getFieldType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            field.setLowerCamelName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, field.getName()));
            field.setPropertyName(getPropertyNameByType(field.getType()));
            list.add(field);
        }
        return list;
    }

    /**
     * 验证当前表是否需要生成代码
     *
     * @param tableName
     * @return
     */
    private Boolean validateTableName(String tableName) {
        Boolean flag = Objects.equals(false, strategy.getIncludeFlag());
        if (CollectionUtils.isEmpty(strategy.getInclude())) {
            return !flag;
        }
        return strategy.getInclude().contains(tableName) | flag;
    }

    private String getPropertyNameByType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return "Date";
        } else if (t.contains("text")) {
            return "String";
        } else if (t.contains("bit")) {
            return "Boolean";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double")) {
            return "Double";
        } else if (t.contains("json") || t.contains("enum")) {
            return "String";
        }
        return "String";
    }
}
