package com.xxx.dao;

import com.xxx.bean.Teacher;
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
 * @create 2022/3/27 15:45
 */
public class TeacherDao {
    public Connection conn;

    public TeacherDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }

    //查
    public List<Teacher> select() {
        List list = new ArrayList<Teacher>();
        String sql = "SELECT * FROM teacher";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String inChargeNB = rs.getString("inChargeNB");

                Teacher teacher = new Teacher();
                teacher.setUsername(username);
                teacher.setPassword(password);
                teacher.setFullName(fullName);
                teacher.setInChargeNB(inChargeNB);

                //System.out.println(u.toString());
                list.add(teacher);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    //查
    public Teacher selectOne(String id) {
        Teacher teacher = new Teacher();
        String sql = "SELECT * FROM teacher";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("username").equals(id)){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String inChargeNB = rs.getString("inChargeNB");

//                    Teacher teacher = new Teacher();
                    teacher.setUsername(username);
                    teacher.setPassword(password);
                    teacher.setFullName(fullName);
                    teacher.setInChargeNB(inChargeNB);

                    //System.out.println(u.toString());
//                    list.add(teacher);
                    break;
                }else{
                    teacher.setUsername("none");
                }

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
}
