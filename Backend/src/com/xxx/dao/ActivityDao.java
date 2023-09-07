package com.xxx.dao;

import com.xxx.bean.Activity;
import com.xxx.bean.User;
import com.xxx.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * @author xzy
 * @create 2022/3/14 20:21
 */
public class ActivityDao {
    public Connection conn;

    public ActivityDao(){
        //conn = jdbc.getConnection();
        conn = c3p0.getConnection();
    }
    //增
    public int add(Activity activity){
        int Resual = 0;
        String sql = "INSERT INTO `actorg`.`Activity`(`menberTotal`, `activityName`, `ativityPlace`, `ativityTime`, `remark`, `activityId`, `summery`, `imgSrc`, `signUpTime`, `deadlineForSign`, `deadlineForAtivity`, `type`, `theme`,`inChargeID`, `inChargeNB`,`initDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
//        String sql = "INSERT INTO Activity (`inChargeID`, `inChargeNB`, `menberTotal`, `activityName`, `ativityPlace`, `ativityTime`, `remark`,`initDate`) VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,activity.getMenberTotal());
            ps.setString(2,activity.getActivityName());
            ps.setString(3,activity.getAtivityPlace());
            ps.setString(4,activity.getAtivityTime());
            ps.setString(5,activity.getRemark());
            ps.setString(6,activity.getActivityId());
            ps.setString(7,activity.getSummery());
            ps.setString(8,activity.getImgSrc());
            ps.setString(9,activity.getSignUpTime());
            ps.setString(10,activity.getDeadlineForSign());
            ps.setString(11,activity.getDeadlineForAtivity());
            ps.setString(12,activity.getType());
            ps.setString(13,activity.getTheme());
            ps.setString(14,activity.getInChargeID());
            ps.setString(15,activity.getInChargeNB());
            ps.setString(16, activity.getInitDate());

            ps.execute();
            Resual = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Resual;
    }
    //查
    public List select() {
        List list = new ArrayList<Activity>();
        String sql = "SELECT * FROM Activity";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String inChargeID = rs.getString("inChargeID");
                String inChargeNB = rs.getString("inChargeNB");
                String menberTotal = rs.getString("menberTotal");
                String activityName = rs.getString("activityName");
                String ativityPlace = rs.getString("ativityPlace");
                String ativityTime = rs.getString("ativityTime");
                String remark = rs.getString("remark");
                String activityId = rs.getString("activityId");
                String summery = rs.getString("summery");
                String imgSrc = rs.getString("imgSrc");
                String signUpTime = rs.getString("signUpTime");
                String deadlineForSign = rs.getString("deadlineForSign");
                String deadlineForAtivity = rs.getString("deadlineForAtivity");
                String type1 = rs.getString("type");
                String theme = rs.getString("theme");
                String initDate = rs.getString("initDate");

                Activity activity = new Activity();
                activity.setRemark(remark);
                activity.setAtivityTime(ativityTime);
                activity.setAtivityPlace(ativityPlace);
                activity.setActivityName(activityName);
                activity.setInChargeID(inChargeID);
                activity.setInChargeNB(inChargeNB);
                activity.setMenberTotal(menberTotal);
                activity.setActivityId(activityId);
                activity.setSummery(summery);
                activity.setImgSrc(imgSrc);
                activity.setSignUpTime(signUpTime);
                activity.setDeadlineForSign(deadlineForSign);
                activity.setDeadlineForAtivity(deadlineForAtivity);
                activity.setType(type1);
                activity.setTheme(theme);
                activity.setInitDate(initDate);


                list.add(activity);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public Activity selectOne(String id) {
        Activity activity = new Activity();
        String sql = "SELECT * FROM Activity";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("activityId").equals(id)){

                    String inChargeID = rs.getString("inChargeID");
                    String inChargeNB = rs.getString("inChargeNB");
                    String menberTotal = rs.getString("menberTotal");
                    String activityName = rs.getString("activityName");
                    String ativityPlace = rs.getString("ativityPlace");
                    String ativityTime = rs.getString("ativityTime");
                    String remark = rs.getString("remark");
                    String activityId = rs.getString("activityId");
                    String summery = rs.getString("summery");
                    String imgSrc = rs.getString("imgSrc");
                    String signUpTime = rs.getString("signUpTime");
                    String deadlineForSign = rs.getString("deadlineForSign");
                    String deadlineForAtivity = rs.getString("deadlineForAtivity");
                    String type1 = rs.getString("type");
                    String theme = rs.getString("theme");
                    String initDate = rs.getString("initDate");

//                    Activity activity = new Activity();
                    activity.setRemark(remark);
                    activity.setAtivityTime(ativityTime);
                    activity.setAtivityPlace(ativityPlace);
                    activity.setActivityName(activityName);
                    activity.setInChargeID(inChargeID);
                    activity.setInChargeNB(inChargeNB);
                    activity.setMenberTotal(menberTotal);
                    activity.setActivityId(activityId);
                    activity.setSummery(summery);
                    activity.setImgSrc(imgSrc);
                    activity.setSignUpTime(signUpTime);
                    activity.setDeadlineForSign(deadlineForSign);
                    activity.setDeadlineForAtivity(deadlineForAtivity);
                    activity.setType(type1);
                    activity.setTheme(theme);
                    activity.setInitDate(initDate);

                    //System.out.println(u.toString());
                    break;
                }

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activity;
    }


}
