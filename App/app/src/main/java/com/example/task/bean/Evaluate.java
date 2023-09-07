package com.example.task.bean;

public class Evaluate {
    private String evaluateID;
    private String userID;
    private String activityID;
    private String data;
    private String date;

    @Override
    public String toString() {
        return "{" +
                "\"evaluateID\":\"" + evaluateID + '\"' +
                ", \"userID\":\"" + userID + '\"' +
                ", \"activityID\":\"" + activityID + '\"' +
                ", \"data\":\"" + data + '\"' +
                ", \"date\":\"" + date + '\"' +
                '}';
    }

    public String getEvaluateID() {
        return evaluateID;
    }

    public void setEvaluateID(String evaluateID) {
        this.evaluateID = evaluateID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
