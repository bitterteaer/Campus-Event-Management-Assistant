package com.xxx.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class c3p0 {
//    private static ComboPooledDataSource datesource = ;
    private static Connection conn;

    static {
        try {
            conn = new ComboPooledDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private c3p0(){
    }

    public static Connection getConnection(){
        return conn;
    }

}
