package com.example.gen.builder;

import com.example.gen.config.DataSourceConfig;
import com.example.gen.config.QuerySQL;
import com.example.gen.config.StrategyConfig;
import com.example.gen.po.TableField;
import com.example.gen.po.TableInfo;
import com.example.gen.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sunx
 * @date 2019-12-31
 * @description
 */
public class ConfigBuilder {
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

    private DataSourceConfig dataSource;

    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;

    public static ConfigBuilder builder() {
        ConfigBuilder builder = new ConfigBuilder();
        builder.querySQL = QuerySQL.MYSQL;
        builder.tableInfoList = new ArrayList<>();
        return builder;
    }

    public ConfigBuilder strategy(StrategyConfig strategy) {
        this.strategy = strategy;
        return this;
    }

    public ConfigBuilder dataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public ConfigBuilder build() {
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConn();
            preparedStatement = connection.prepareStatement(querySQL.getTableCommentsSql());
            ResultSet results = preparedStatement.executeQuery();
            Boolean hasDecimal = false;
            Boolean hasDate = false;
            while (results.next()) {
                String tableName = results.getString(querySQL.getTableName());
                if (StringUtils.isNotBlank(tableName) && validateTableName(tableName)) {
                    hasDecimal = false;
                    hasDate = false;
                    String tableComment = results.getString(querySQL.getTableComment());
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    tableInfo.setComment(tableComment);
                    tableInfo.setFields(getListFields(tableName, true));
                    tableInfo.setAllFields(getListFields(tableName, false));
                    tableInfo.setHasDecimal(false);
                    tableInfo.setHasDate(false);
                    for (TableField fieldInfo : tableInfo.getFields()) {
                        if (fieldInfo.getPropertyName().equals("BigDecimal")) {
                            tableInfo.setHasDecimal(true);
                            hasDecimal = true;
                        }
                        if (fieldInfo.getPropertyName().equals("Date")) {
                            hasDate = true;
                        }
                    }
                    tableInfo.setHasDate(hasDate);
                    tableInfo.setHasDecimal(hasDecimal);
//                    tableInfo.setKeyAutoIncr(tableInfo.getAllFields().stream().filter(a -> a.isKeyAutoIncr()).findFirst().isPresent());
                    tableInfo.setKeyAutoIncr(false);
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
    private List<TableField> getListFields(String tableName, Boolean excludeFlag) throws SQLException {
        List<TableField> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), tableName));
        ResultSet results = preparedStatement.executeQuery();
        while (results.next()) {
            TableField field = new TableField();
            String key = results.getString(querySQL.getFieldKey());
            // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
            boolean isId = StringUtils.isNotBlank(key) && key.toUpperCase().equals("PRI");
            String ext = results.getString(querySQL.getFieldExt());
            // 处理ID
            field.setKeyFlag(isId);
            if (isId && StringUtils.isNotBlank(ext)) {
                field.setKeyAutoIncr(Objects.equals("auto_increment", ext.toLowerCase()));
            }
            // 处理其它信息
            field.setName(results.getString(querySQL.getFieldName()));
            if (excludeFlag && dataSource.getExcludeFieldSets().contains(field.getName())) {
                continue;
            }
            field.setType(results.getString(querySQL.getFieldType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            field.setLowerCamelName(StringUtil.caseFormat(field.getName(), 0));
            field.setUpperCamelName(StringUtil.caseFormat(field.getName(), 1));
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
        if (null == strategy.getInclude() || strategy.getInclude().isEmpty()) {
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

    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }
}
