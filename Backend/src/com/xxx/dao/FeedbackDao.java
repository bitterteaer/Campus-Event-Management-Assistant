package com.xxx.dao;

import com.xxx.bean.Feedback;
import com.xxx.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2022/2/19 14:28
 */
public class FeedbackDao {
    public Connection conn;

    public FeedbackDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }

    //增
    public int add(Feedback feedback){
        int Resual = 0;
        String sql = "INSERT INTO feedback (`data`, `username`, `date`) VALUES (?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,feedback.getData());
            ps.setString(2,feedback.getUserName());
            ps.setString(3,feedback.getDate());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }

    //查
    public List<Feedback> select() {
        List list = new ArrayList<Feedback>();
        String sql = "SELECT * FROM feedback";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String idfeedback = rs.getString("idfeedback");
                String username = rs.getString("username");
                String data = rs.getString("data");
                String date = rs.getString("date");

                Feedback feedback = new Feedback();
                feedback.setIdfeedback(idfeedback);
                feedback.setData(data);
                feedback.setDate(date);
                feedback.setUserName(username);

                //System.out.println(u.toString());
                list.add(feedback);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
