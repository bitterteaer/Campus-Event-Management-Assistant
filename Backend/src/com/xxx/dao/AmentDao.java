package com.xxx.dao;

import com.xxx.bean.Ament;
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
 * @create 2021/10/30 16:54
 */
public class AmentDao {
    public Connection conn;

    public AmentDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //增
    public int add(Ament ament){
        int Resual = 0;
        String sql = "INSERT INTO `announcement` (`username`, `data`, `date`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,ament.getUsername());
            ps.setString(2,ament.getData());
            ps.setString(3,ament.getDate());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }

    //查
    public List<Ament> select() {
        List list = new ArrayList<User>();
        String sql = "SELECT * FROM announcement";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int idannouncement = rs.getInt("idannouncement");
                String username = rs.getString("username");
                String data = rs.getString("data");
                String date = rs.getString("date");

                Ament ament = new Ament();
                ament.setIdannouncement(idannouncement);
                ament.setUsername(username);
                ament.setData(data);
                ament.setDate(date);

                //System.out.println(u.toString());
                list.add(ament);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
