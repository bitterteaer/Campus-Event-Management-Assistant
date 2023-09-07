package com.xxx.bean;

/**
 * @author xzy
 * @create 2021/10/30 0:35
 */
public class Echarts {
    private String idecharts;
    private String login;//登录
    private String register;//注册
    private String putActivate;//发布活动
    private String attendActivate;//参加活动
    private String all;//全部人数
    private String date;//日期

    @Override
    public String toString() {
        return "{" +
                "\"idecharts\":" +"\"" +idecharts +"\"" +
                ",\"login\":" +"\"" +login +"\"" +
                ", \"register\":" +"\"" + register +"\"" +
                ", \"putActivate\":" +"\"" + putActivate +"\"" +
                ", \"attendActivate\":" +"\"" + attendActivate +"\"" +
                ", \"all\":" +"\"" + all +"\"" +
                ", \"date\":" +"\"" + date +"\"" +
                '}';
    }

    public String getIdecharts() {
        return idecharts;
    }

    public void setIdecharts(String idecharts) {
        this.idecharts = idecharts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getPutActivate() {
        return putActivate;
    }

    public void setPutActivate(String putActivate) {
        this.putActivate = putActivate;
    }

    public String getAttendActivate() {
        return attendActivate;
    }

    public void setAttendActivate(String attendActivate) {
        this.attendActivate = attendActivate;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
