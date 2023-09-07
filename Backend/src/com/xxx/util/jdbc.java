package com.xxx.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author xzy
 * @create 2021/10/18 13:00
 */
public class jdbc {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/store?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "x1575280002";

    //执行sql语句
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        jdbc j = new jdbc();
        System.out.println(j.getConnection());
    }
}
