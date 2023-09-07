package com.xxx.bean;

/**
 * @author xzy
 * @create 2022/2/19 14:25
 */
public class Feedback {
    private String idfeedback;
    private String username;
    private String data;
    private String date;

    @Override
    public String toString() {
        return "{" +
                "\"idfeedback\":" +"\"" +idfeedback +"\"" +
                ",\"username\":" +"\"" +username +"\"" +
                ", \"data\":" +"\"" + data +"\"" +
                ", \"date\":" +"\"" + date +"\"" +
                '}';
    }

    public String getIdfeedback() {
        return idfeedback;
    }

    public void setIdfeedback(String idfeedback) {
        this.idfeedback = idfeedback;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
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
