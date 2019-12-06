package com.example.genx.builder;

import com.example.genx.po.TableField;
import com.example.genx.po.TableInfo;
import com.example.genx.run.DataSourceConfig;
import com.example.genx.run.QuerySQL;
import com.example.genx.run.StrategyConfig;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private List<TableInfo> tableInfoList;

    /**
     * 需要排除的字段
     */
    private static Set<String> excludeFieldSets = Sets.newHashSet("id", "status", "create_time", "create_user", "create_id", "update_time", "update_user", "update_id");

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
                    tableInfo.setKeyAutoIncr(tableInfo.getAllFields().stream().filter(a -> a.isKeyAutoIncr()).findFirst().isPresent());
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
        List<TableField> list = Lists.newArrayList();
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
            if (excludeFlag && excludeFieldSets.contains(field.getName())) {
                continue;
            }
            field.setType(results.getString(querySQL.getFieldType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            field.setLowerCamelName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getName()));
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

    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    public void setTableInfoList(List<TableInfo> tableInfoList) {
        this.tableInfoList = tableInfoList;
    }

    //    public static void main(String[] args) {
//        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
//        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
//
//        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));
//        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
//        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
//    }
}
