package com.xxx.dao;

import com.xxx.bean.Log;
import com.xxx.bean.User;
import com.xxx.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2021/10/24 12:44
 */
public class LogDao {
    public Connection conn;

    public LogDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    public int add(Log log){
        int Resual = 0;
        String sql = "INSERT INTO log (`username`, `date`, `log`) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,log.getUsername());
            ps.setString(2,log.getDate());
            ps.setString(3,log.getLog());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //查
    public List<User> select() {
        List list = new ArrayList<User>();
        String sql = "SELECT * FROM log";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int idlog = rs.getInt("idlog");
                String username = rs.getString("username");
                String date = rs.getString("date");
                String log = rs.getString("log");

                Log l = new Log();
                l.setIdlog(idlog);
                l.setUsername(username);
                l.setDate(date);
                l.setLog(log);

                //System.out.println(u.toString());
                list.add(l);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //改
    public int update(Log log){
        int Resual = 0;
        String sql = "UPDATE log SET `date` = ? WHERE (`idlog` = ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,log.getDate());
            ps.setInt(2,log.getIdlog());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
}
