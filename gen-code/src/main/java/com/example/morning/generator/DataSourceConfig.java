package com.example.morning.generator;

import com.example.morning.generator.core.GenConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author sunx
 * @date 2019-08-23
 * @description
 */
public class DataSourceConfig {
    /**
     * 驱动连接的URL
     */
    private String url = GenConstant.JDBC_URL;
    /**
     * 驱动名称
     */
    private String driverName = GenConstant.JDBC_DIVER_CLASS_NAME;
    /**
     * 数据库连接用户名
     */
    private String username = GenConstant.JDBC_USERNAME;
    /**
     * 数据库连接密码
     */
    private String password = GenConstant.JDBC_PASSWORD;

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
}
