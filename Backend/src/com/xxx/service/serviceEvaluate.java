package com.xxx.service;

import com.xxx.bean.Evaluate;
import com.xxx.bean.EvaluateAndUser;
import com.xxx.bean.User;
import com.xxx.dao.EvaluateDao;
import com.xxx.dao.userDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2022/4/5 20:42
 */
public class serviceEvaluate {
    public static void setData(Evaluate evaluate){
        new EvaluateDao().add(evaluate);
    }
    public static List getData(){
        //EvaluateAndUser
        List endList = new ArrayList();
        List list = new EvaluateDao().select();
        List userList = new userDao().select();

        for (int i = 0; i < list.size(); i++) {
            Evaluate evaluate = (Evaluate) list.get(i);

            EvaluateAndUser evaluateAndUser = new EvaluateAndUser();
            evaluateAndUser.setActivityID(evaluate.getActivityID());
            evaluateAndUser.setData(evaluate.getData());
            evaluateAndUser.setDate(evaluate.getDate());
            evaluateAndUser.setEvaluateID(evaluate.getEvaluateID());
            evaluateAndUser.setUserID(evaluate.getUserID());
//            evaluateAndUser.setName();
            for (int j = 0; j < userList.size(); j++) {
                User user = (User) userList.get(j);
                if(Integer.toString(user.getIduser()).equals(evaluate.getUserID())){
                    evaluateAndUser.setName(user.getName());
                    break;
                }
            }

            endList.add(evaluateAndUser);
        }

        return endList;
    }

//    public static void main(String[] args) {
//        System.out.println(serviceEvaluate.getData());
//    }

    public static String seleteByActID(String ActID){
        List<Evaluate> list = new EvaluateDao().select();
        List<User> users = new userDao().select();

        List<Evaluate> thisActEvaluateList = new ArrayList();
        List endList = new ArrayList();

        for(Evaluate evaluate : list){
            if(evaluate.getActivityID().equals(ActID)){
                thisActEvaluateList.add(evaluate);
            }
        }

        for(Evaluate evaluate : thisActEvaluateList){
            for (User u : users){
                if(Integer.toString(u.getIduser()).equals(evaluate.getUserID())){
                    EvaluateAndUser evaluateAndUser = new EvaluateAndUser();

                    evaluateAndUser.setName(u.getName());
                    evaluateAndUser.setUserID(evaluate.getUserID());
                    evaluateAndUser.setEvaluateID(evaluate.getEvaluateID());
                    evaluateAndUser.setDate(evaluate.getDate());
                    evaluateAndUser.setData(evaluate.getData());
                    evaluateAndUser.setActivityID(evaluate.getActivityID());
                    endList.add(evaluateAndUser);
                    break;
                }
            }
        }

        return endList.toString();
    }
}
