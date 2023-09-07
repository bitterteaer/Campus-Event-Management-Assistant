package com.xxx.dao;

import com.xxx.bean.SignUp;
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
 * @create 2022/3/28 22:07
 */
public class SignUpDao {
    public Connection conn;

    public SignUpDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //增
    public int add(SignUp signUp){
        int Resual = 0;
        String sql = "INSERT INTO signup (`activityName`, `username`,`time`, `activityID`) VALUES (?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,signUp.getActivityName());
            ps.setString(2,signUp.getUsername());
            ps.setString(3,signUp.getTime());
            ps.setString(4,signUp.getActivityID());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //删
    public int delete(String userID, String activityID){
        int Resual = 0;

        String sql1 = "DELETE FROM signup WHERE username=? AND activityID=?;";
//        String sql = "DELETE FROM signup WHERE (signupID = ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, userID);
            ps.setString(2, activityID);
//            ps.setInt(2, signUp.getUsername());

            Resual = 1;
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //查 报名信息
    public List<SignUp> select() {
        List list = new ArrayList<SignUp>();
        String sql = "SELECT * FROM signup";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String activityID = rs.getString("activityID");
                String activityName = rs.getString("activityName");
                String username = rs.getString("username");
                String time = rs.getString("time");

                SignUp s = new SignUp();
                s.setTime(time);
                s.setUsername(username);
                s.setActivityID(activityID);
                s.setActivityName(activityName);

                //System.out.println(u.toString());
                list.add(s);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

//    public static void main(String[] args) {
//        System.out.println(new SignUpDao().select());
//    }
}
