package com.xxx.service;

import com.google.gson.Gson;
import com.google.zxing.qrcode.encoder.QRCode;
import com.xxx.bean.*;
import com.xxx.dao.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2021/10/24 21:18
 */
public class serviceUser {
    public static String getData(){
        userDao ud = new userDao();
        List<User> list = (List) ud.select();

        String json = new Gson().toJson(list);

        return json;
    }
    public static void SetImgPath(User u){
        userDao ud = new userDao();
        ud.updateImg(u);
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setUsername("101");
        System.out.println(serviceUser.UserAboutThisTeacher(teacher).toString());
    }
    public static List UserAboutThisTeacher(Teacher teacher){
        GetUserAboutThisTeacherDao getUserAboutThisTeacherDao = null;
        try {
            getUserAboutThisTeacherDao = new GetUserAboutThisTeacherDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> UserList = null;
        try {
            UserList = getUserAboutThisTeacherDao.execute(teacher.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        for (User a : UserList){
//            System.out.println(a);
//        }
        System.out.println("one ----------------------");
        System.out.println(UserList);

        List endList = new ArrayList();
        List<SignUp> signupList = new SignUpDao().select();
        for (int i = 0; i < UserList.size(); i++) {
            UserAndSignAboutThisTeacher thisTeacher = new UserAndSignAboutThisTeacher();
            User user = UserList.get(i);

            thisTeacher.setBirthday(user.getBirthday());
            thisTeacher.setClassRoom(user.getClassRoom());
            thisTeacher.setEmail(user.getEmail());
            thisTeacher.setFaculty(user.getFaculty());
            thisTeacher.setIduser(user.getIduser());
            thisTeacher.setImgpath(user.getImgpath());
            thisTeacher.setInitdate(user.getInitdate());
            thisTeacher.setName(user.getName());
            thisTeacher.setSex(user.getSex());
            thisTeacher.setTelephone(user.getTelephone());
//            thisTeacher.setTime();
            for (SignUp signUp : signupList){
                if(signUp.getUsername().equals(Integer.toString(user.getIduser()))){
                    thisTeacher.setActivityName(signUp.getActivityName());
                    thisTeacher.setTime(signUp.getTime());
                }
            }

            endList.add(thisTeacher);
        }

        //获取签到状态
//        List endendlist = new ArrayList();
        List<qrCode> rqlist = new QrCodeData().select();
        System.out.println("----------------------------");
        System.out.println(rqlist);

        for(Object uat : endList){
            UserAndSignAboutThisTeacher a = (UserAndSignAboutThisTeacher) uat;
            a.setQrStates("未签到");
            for(qrCode q : rqlist){
                if(q.getData().equals(a.getActivityName())){
                    a.setQrStates("已签到");
                }
            }
        }

        System.out.println(endList);
        System.out.println("----------------------------");
        return endList;
    }

//    public static void main(String[] args) {
//        Teacher teacher = new Teacher();
//        teacher.setUsername("teacher1");
//        UserAboutThisTeacher(teacher);
//    }
    public static boolean judgeTheAct(User user, Activity activity){
        List<SignUp> list = new SignUpDao().select();
        List<SignUp> thisUserSign = new ArrayList();

        for(SignUp signUp : list){
            if(signUp.getUsername().equals(Integer.toString(user.getIduser()))){
                thisUserSign.add(signUp);
            }
        }

        boolean result = false;
        for(SignUp signUp : thisUserSign){
            if(signUp.getActivityID().equals(activity.getActivityId())){
                result = true;
                break;
            }
        }

        return result;
    }
}
