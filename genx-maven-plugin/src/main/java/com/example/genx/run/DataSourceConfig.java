//package com.example.genx.run;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// * @author sunx
// * @date 2019-08-23
// * @description
// */
//public class DataSourceConfig {
//    /**
//     * 驱动连接的URL
//     */
//    private static final String url = "jdbc:mysql://125.64.5.82:13306/devcsm?createDatabaseIfNotExist=true&useSSL=false&characterEncoding=utf8&useUnicode=true";
//    /**
//     * 驱动名称
//     */
//    private static final String driverName = "com.mysql.jdbc.Driver";
//    /**
//     * 数据库连接用户名
//     */
//    private static final String username = "root";
//    /**
//     * 数据库连接密码
//     */
//    private static final String password = "B$YpKFUUQ2npmwFogu58";
//
//    public Connection getConn() {
//        Connection conn = null;
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//}
