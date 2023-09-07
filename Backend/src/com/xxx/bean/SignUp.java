package com.xxx.bean;


/**
 * @author xzy
 * @create 2022/3/28 22:04
 */
public class SignUp {
    private int signupID;
    private String activityID;
    private String activityName;
    private String username;
    private String time;

    @Override
    public String toString() {
        return "{" +
                "\"activityID\":\"" + activityID + '\"' +
                ",\"activityName\":\"" + activityName + '\"' +
                ", \"username\":\"" + username + '\"' +
                ", \"time\":\"" + time + '\"' +
                ", \"signupID\":\"" + signupID + '\"' +
                '}';
    }

    public int getSignupID() {
        return signupID;
    }

    public void setSignupID(int signupID) {
        this.signupID = signupID;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public static void main(String[] args) {
//        System.out.println(new SignUp().toString());
//    }
}
