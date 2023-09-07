package com.xxx.dao;

import com.xxx.bean.Evaluate;
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
 * @create 2022/4/5 20:36
 */
public class EvaluateDao {
    private Connection conn;
    public EvaluateDao(){
        conn = c3p0.getConnection();
    }

    //增
    public int add(Evaluate evaluate){
        int Resual = 0;
        String sql = "INSERT INTO evaluate (`data`, `date`, `evaluateID`, `userID`, `activityID`) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,evaluate.getData());
            ps.setString(2,evaluate.getDate());
            ps.setString(3,evaluate.getEvaluateID());
            ps.setString(4,evaluate.getUserID());
            ps.setString(5,evaluate.getActivityID());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }

    //查
    public List<Evaluate> select() {
        List list = new ArrayList<Evaluate>();
        String sql = "SELECT * FROM evaluate";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String evaluateID = rs.getString("evaluateID");
                String userID = rs.getString("userID");
                String activityID = rs.getString("activityID");
                String date = rs.getString("date");
                String data = rs.getString("data");

                Evaluate evaluate = new Evaluate();
                evaluate.setEvaluateID(evaluateID);
                evaluate.setUserID(userID);
                evaluate.setActivityID(activityID);
                evaluate.setDate(date);
                evaluate.setData(data);

                //System.out.println(u.toString());
                list.add(evaluate);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
