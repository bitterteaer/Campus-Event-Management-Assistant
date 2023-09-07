package com.xxx.service;

import com.xxx.bean.Activity;
import com.xxx.bean.SignUp;
import com.xxx.bean.Teacher;
import com.xxx.bean.User;
import com.xxx.dao.ActivityDao;
import com.xxx.dao.SignUpDao;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xzy
 * @create 2022/3/14 20:14
 */
public class serviceActiviteData {
    ActivityDao activityDao = new ActivityDao();
    SignUpDao signUpDao = new SignUpDao();

    public List getData(){
        List list = activityDao.select();
        return list;
    }
    public Activity getOneData(String id){
        Activity a = activityDao.selectOne(id);
        return a;
    }
    public void setData(Activity activity){
        activityDao.add(activity);
    }

//    public static void main(String[] args) {
//        System.out.println(new serviceActiviteData().getData().toString());
//
//    }
//    public List GetOneUserData(String userID){
//        ActivityDao activityDao = new ActivityDao();
//
//    }
    public List getDataAboutThisUser(User user){
        List activityList = activityDao.select();
        List signUpList = signUpDao.select();
        List thisUserSignUpList = new ArrayList<SignUp>();
        List endList = new ArrayList<Activity>();

//        System.out.println("signUpList"+signUpList);
//        System.out.println("user"+user);
        for (int i = 0; i < signUpList.size(); i++) {
            SignUp signUp = (SignUp) signUpList.get(i);
//            System.out.println(signUp.getUsername()+"&&"+user.getIduser());
            if(signUp.getUsername().equals(Integer.toString(user.getIduser()))){
//                System.out.println("is add it");
                thisUserSignUpList.add(signUp);
            }
        }
//        System.out.println("thisUserSignUpList"+thisUserSignUpList);
//        System.out.println("activityList"+activityList);
        for (int i = 0; i < thisUserSignUpList.size(); i++) {
            SignUp signUp = (SignUp) thisUserSignUpList.get(i);
            for (int j = 0; j < activityList.size(); j++) {
                Activity activity = (Activity) activityList.get(j);
                if(signUp.getActivityID().equals(activity.getActivityId())){
                    endList.add(activity);
                }
            }
        }

        return endList;
    }

    public static List getActivityAboutLoginTeacher(Teacher teacher){
        ActivityDao activityDao = new ActivityDao();
        List<Activity> activityList = activityDao.select();
        List endList = new ArrayList();

        for(Activity activity : activityList){
            if(activity.getInChargeID().equals(teacher.getUsername())){
                endList.add(activity);
            }
        }

        return endList;
    }

    public static void main(String[] args) {
//        Activity activity = new Activity();
//        activity.setInChargeID("teacher1");
//        activity.setActivityId("11111");
//        new serviceActiviteData().setData(activity);
    }
}
