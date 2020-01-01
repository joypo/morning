package com.example.genx.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sunx
 * @date 2019-12-31
 * @description
 */
public class DataSourceConfig {
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 表前缀
     */
    private String prefix;

    /**
     * 需要排除的字段
     */
    private String[] excludeFields;

    private Set<String> excludeFieldSets = new HashSet<>();

    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public String getUrl() {
        return url;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPrefix() {
        return prefix;
    }

    public String[] getExcludeFields() {
        return excludeFields;
    }

    public Set<String> getExcludeFieldSets() {
        if (null != excludeFields && excludeFields.length > 0) {
            Set<String> set = new HashSet<>();
            for (String item : excludeFields) {
                set.add(item);
            }
            return set;
        }
        return new HashSet<>();
    }
}
