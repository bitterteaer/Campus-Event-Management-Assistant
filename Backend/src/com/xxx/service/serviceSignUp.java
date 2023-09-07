package com.xxx.service;

import com.xxx.bean.Activity;
import com.xxx.bean.ActivityPeopleNumBean;
import com.xxx.bean.SignUp;
import com.xxx.bean.User;
import com.xxx.dao.ActivityDao;
import com.xxx.dao.SignUpDao;
import com.xxx.util.GetDate;
import com.xxx.util.返回各个活动已经报名的人数.GetActivityPeopleNumDao;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

/**
 * @author xzy
 * @create 2022/3/28 22:04
 */
public class serviceSignUp {
    public static void setData(SignUp signUp){
        new SignUpDao().add(signUp);
    }
    public static List getData(){
        return new SignUpDao().select();
    }

    public static int deleteData(String activityID, String userID){
        return new SignUpDao().delete(userID,activityID);
//        System.out.println("");
    }
    public static String SuccessAndGetSignUpStates(String activityID){
        //总人数
        ActivityDao activityDao = new ActivityDao();
        List<Activity> activityList = activityDao.select();

        Activity thisAct = null;
        for(Activity a : activityList){
            if(a.getActivityId().equals(activityID)){
                thisAct = a;
            }
        }
        int allNum = Integer.parseInt(thisAct.getMenberTotal());

        //报名人数
        int thisNum = 0;
        GetActivityPeopleNumDao getActivityPeopleNumDao = null;
        try {
            getActivityPeopleNumDao = new GetActivityPeopleNumDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ActivityPeopleNumBean> list = null;
        try {
            list = getActivityPeopleNumDao.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ActivityPeopleNumBean a : list){
            if(a.getActivityID().equals(activityID)){
                thisNum = a.getNum();
            }
//            System.out.println(a.toString());
        }

        return "{" +
                "\"allMemberNum\":\"" + allNum +"\""+
                ",\"HasSignUp\":\"" + thisNum +"\""+
                ",\"states\":\"" + "取消报名成功"+"\""+
                "}";
    }

    public static List getDataByUser(String username){
        List endList = new ArrayList<SignUp>();
        List l = new SignUpDao().select();
//        System.out.println(l);
        for (int i = 0; i < l.size(); i++) {
            SignUp signUp = (SignUp) l.get(i);
//            System.out.println(signUp.getUsername()+username);
            if(signUp.getUsername().equals(username)){
                endList.add(signUp);
//                return signUp;
            }
        }

//        System.out.println(endList.toString());
        return endList;
    }

//    public static void main(String[] args) {
//        serviceSignUp.getDataByUser("3");
//    }
    public static String GetSignUpNumByActID(String ActID){
        GetActivityPeopleNumDao getActivityPeopleNumDao = null;
        try {
            getActivityPeopleNumDao = new GetActivityPeopleNumDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ActivityPeopleNumBean> list = null;
        try {
            list = getActivityPeopleNumDao.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ActivityPeopleNumBean numer = null;
        for (ActivityPeopleNumBean a : list){
//            System.out.println(a.toString());
            if(a.getActivityID().equals(ActID)){
                numer = a;
                break;
            }
        }

//        System.out.println(numer.toString());
        return numer.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(GetSignUpNumByActID("10001"));
//    }
    public static String SignUpBackNum(String ID, User u){
        //总人数
        ActivityDao activityDao = new ActivityDao();
        List<Activity> activityList = activityDao.select();

        Activity thisAct = null;
        for(Activity a : activityList){
            if(a.getActivityId().equals(ID)){
                thisAct = a;
            }
        }
        int allNum = Integer.parseInt(thisAct.getMenberTotal());

        //报名人数
        int thisNum = 0;
        GetActivityPeopleNumDao getActivityPeopleNumDao = null;
        try {
            getActivityPeopleNumDao = new GetActivityPeopleNumDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ActivityPeopleNumBean> list = null;
        try {
            list = getActivityPeopleNumDao.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ActivityPeopleNumBean a : list){
            if(a.getActivityID().equals(ID)){
                thisNum = a.getNum();
            }
//            System.out.println(a.toString());
        }


        if(allNum > thisNum){
            SignUp signUp = new SignUp();
            signUp.setActivityName(thisAct.getActivityName());
            signUp.setActivityID(thisAct.getActivityId());
            signUp.setUsername(Integer.toString(u.getIduser()));
            signUp.setTime(new GetDate().getData());

            serviceSignUp.setData(signUp);

            return "{" +
                    "\"allMemberNum\":\"" + allNum +"\""+
                    ",\"HasSignUp\":\"" + Integer.toString(thisNum+1) +"\""+
                    ",\"states\":\"" + "报名成功"+"\""+
                    "}";
        }

        return "{" +
                "\"allMemberNum\":\"" + allNum +"\""+
                ",\"HasSignUp\":\"" + Integer.toString(thisNum)+"\""+
                ",\"states\":\"" + "报名失败"+"\""+
                "}";
    }
}
