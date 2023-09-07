package com.example.task.bean;

import java.io.Serializable;

/**
 * @author xzy
 * @create 2022/3/27 15:40
 */
public class Teacher implements Serializable {
    private String username;//教师id
    private String password;//教师密码
    private String fullName;//教师名字
    private String inChargeNB;//教师联系方式

    @Override
    public String toString() {
        return  "{" +
                "\"username\":\"" + username +"\""+
                ",\"password\":\"" + password+"\""+
                ",\"fullName\":\"" + fullName +"\""+
                ",\"inChargeNB\":\"" + inChargeNB+"\""+
                "}";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInChargeNB() {
        return inChargeNB;
    }

    public void setInChargeNB(String inChargeNB) {
        this.inChargeNB = inChargeNB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
