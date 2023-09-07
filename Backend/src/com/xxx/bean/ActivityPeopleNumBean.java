package com.xxx.bean;

public class ActivityPeopleNumBean {
    String activityID;
    Integer Num;

    public String getActivityID() {
        return activityID;
    }

    public Integer getNum() {
        return Num;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setNum(Integer num) {
        Num = num;
    }

    @Override
    public String toString() {
        return "{" +
                "\"activityID\":\"" + activityID + '\"' +
                ", \"Num\":\"" + Num + '\"' +
                '}';
    }

    public ActivityPeopleNumBean(String activityID, Integer num) {
        this.activityID = activityID;
        Num = num;
    }

    public ActivityPeopleNumBean(){}

    public static void main(String[] args) {
        System.out.println(new ActivityPeopleNumBean().toString());
    }
}
