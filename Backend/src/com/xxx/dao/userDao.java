package com.xxx.dao;

import com.xxx.bean.SuperUser;
import com.xxx.bean.User;
import com.xxx.util.c3p0;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2021/10/14 16:17
 */
public class userDao {
    private Connection conn;

    public userDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //增
    public int add(User user){
        int Resual = 0;
        String sql = "INSERT INTO user (`username`, `password`, `name`, `email`, `telephone`, `birthday`, `sex`, `initdate`) VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getTelephone());
            ps.setString(6,user.getBirthday());
            ps.setString(7,user.getSex());
            ps.setString(8,user.getInitdate());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //删
    public int delete(User user){
        int Resual = 0;
        String sql = "DELETE FROM user WHERE (iduser = ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getIduser());

            Resual = 1;
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //改
    public int update(User user){
        int Resual = 0;
        String sql = "UPDATE user SET `password` = ? WHERE (`iduser` = ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getPassword());
            ps.setInt(2,user.getIduser());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //头像上传
    public int updateImg(User user){
        int Resual = 0;
        String sql = "UPDATE user SET `imgpath` = ? WHERE (`username` = ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getImgpath());
            ps.setString(2,user.getUsername());

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
        String sql = "SELECT * FROM user";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int iduser = rs.getInt("iduser");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String telephone = rs.getString("telephone");
                String birthday = rs.getString("birthday");
                String sex = rs.getString("sex");
                String initdate = rs.getString("initdate");
                String classRoom = rs.getString("classRoom");
                String faculty = rs.getString("faculty");
                String imgpath = rs.getString("imgpath");

                User u = new User();
                u.setIduser(iduser);
                u.setInitdate(initdate);
                u.setUsername(username);
                u.setPassword(password);
                u.setName(name);
                u.setEmail(email);
                u.setTelephone(telephone);
                u.setBirthday(birthday);
                u.setSex(sex);
                u.setClassRoom(classRoom);
                u.setFaculty(faculty);
                u.setImgpath(imgpath);

                //System.out.println(u.toString());
                list.add(u);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int login(User u){
        int Result = 0;
        String sql = "SELECT username, password FROM user";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString("username").equals(u.getUsername()) &&
                        rs.getString("password").equals(u.getPassword())){

                    System.out.println("success");
                    Result = 1;
                    break;
                }
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Result;
    }
    public int superLogin(SuperUser u){
        int Result = 0;
        String sql = "SELECT name, password FROM superuser";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString("name").equals(u.getName()) &&
                        rs.getString("password").equals(u.getPassword())){

                    System.out.println("success");
                    Result = 1;
                    break;
                }
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Result;
    }

    public User selectOne(String username) {
        User u = new User();
        String sql = "SELECT * FROM user";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

//            private int iduser;
//            private String username;//学生id
//            private String password;//学生密码
//            private String name;//学生名字
//            private String email;//学生邮箱
//            private String telephone;//学生联系方式
//            private String birthday;//注册时间
//            private String sex;//性别
//            private String initdate;
//            private String classRoom;//学生班别
//            private String faculty;//学生院系
//            private String imgpath;//头像

            while (rs.next()) {
                if(rs.getString("username").equals(username)){

                    int iduser = rs.getInt("iduser");
//                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String telephone = rs.getString("telephone");
                    String birthday = rs.getString("birthday");
                    String sex = rs.getString("sex");
                    String initdate = rs.getString("initdate");
                    String classRoom = rs.getString("classRoom");
                    String faculty = rs.getString("faculty");
                    String imgpath = rs.getString("imgpath");

                    u.setIduser(iduser);
                    u.setUsername(username);
                    u.setPassword(password);
                    u.setName(name);
                    u.setEmail(email);
                    u.setTelephone(telephone);
                    u.setBirthday(birthday);
                    u.setSex(sex);
                    u.setInitdate(initdate);
                    u.setClassRoom(classRoom);
                    u.setFaculty(faculty);
                    u.setImgpath(imgpath);

                    //System.out.println(u.toString());
                    break;
                }

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
}
