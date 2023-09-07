package com.xxx.dao;

import com.xxx.bean.Echarts;
import com.xxx.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2021/11/30 19:06
 */
public class EchartDao {
    public Connection conn;

    public EchartDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //查
    public List selectInNum(int num) {
        List list = new ArrayList();
//        String sql = "SELECT * FROM temperature";
        String sql = "select * from echarts order by idecharts desc;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                if(count >= num)break;

                String idecharts = rs.getString("idecharts");
                String login = rs.getString("login");
                String register = rs.getString("register");
                String putActivate = rs.getString("putActivate");
                String attendActivate = rs.getString("attendActivate");
                String all = rs.getString("all");
                String date = rs.getString("date");

                Echarts echarts = new Echarts();
                echarts.setIdecharts(idecharts);
                echarts.setLogin(login);
                echarts.setRegister(register);
                echarts.setPutActivate(putActivate);
                echarts.setAttendActivate(attendActivate);
                echarts.setAll(all);
                echarts.setDate(date);

//                System.out.println(telephone.toString());
                list.add(echarts);

                count++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //查最后一个数据项
    public Echarts selectLastOne() {
        Echarts echarts = new Echarts();
//        List list = new ArrayList();
//        String sql = "SELECT * FROM temperature";
        String sql = "select * from echarts order by idecharts desc;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String idecharts = rs.getString("idecharts");
                String login = rs.getString("login");
                String register = rs.getString("register");
                String putActivate = rs.getString("putActivate");
                String attendActivate = rs.getString("attendActivate");
                String all = rs.getString("all");
                String date = rs.getString("date");

                echarts.setIdecharts(idecharts);
                echarts.setLogin(login);
                echarts.setRegister(register);
                echarts.setPutActivate(putActivate);
                echarts.setAttendActivate(attendActivate);
                echarts.setAll(all);
                echarts.setDate(date);

//                System.out.println(telephone.toString());
//                list.add(echarts);

                break;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return echarts;
    }
}
