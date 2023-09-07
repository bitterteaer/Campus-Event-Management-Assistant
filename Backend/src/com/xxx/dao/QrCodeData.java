package com.xxx.dao;

import com.xxx.bean.User;
import com.xxx.bean.qrCode;
import com.xxx.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2022/3/20 16:52
 */
public class QrCodeData {
    public Connection conn;

    public QrCodeData(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //增
    public int add(qrCode q){
        int Resual = 0;
        String sql = "INSERT INTO `actorg`.`QrData` (`data`,`date`,`user`) VALUES (?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,q.getData());
            ps.setString(2,q.getDate());
            ps.setString(3,q.getUser());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //查
    public List<qrCode> select() {
        List list = new ArrayList<qrCode>();
        String sql = "SELECT * FROM QrData";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
//                int iduser = rs.getInt("iduser");
                String data = rs.getString("data");
                String date = rs.getString("date");
                String user = rs.getString("user");
//                String email = rs.getString("email");

                qrCode u = new qrCode();
                u.setUser(user);
                u.setDate(date);
                u.setData(data);

                //System.out.println(u.toString());
                list.add(u);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
