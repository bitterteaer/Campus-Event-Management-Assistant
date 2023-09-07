package com.example.task.bean;

import java.io.Serializable;

public class User implements Serializable {

    public static String name_User = "username";
    public static String telephone_User = "telephone";
    public static String email_User = "email";

    private int iduser;
    private String username;//学生id
    private String password;//学生密码
    private String name;//学生名字
    private String email;//学生邮箱
    private String telephone;//学生联系方式
    private String birthday;//注册时间
    private String sex;//性别
    private String initdate;
    private String classRoom;//学生班别
    private String faculty;//学生院系
    private String imgpath;//头像

    public User(){}
    public User(int iduser, String username, String password, String name, String email, String telephone, String birthday, String sex, String initdate, String classRoom, String faculty, String imgpath) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.sex = sex;
        this.initdate = initdate;
        this.classRoom = classRoom;
        this.faculty = faculty;
        this.imgpath = imgpath;
    }

    @Override
    public String toString() {
        return "{" +
                "\"iduser\":\"" + iduser +"\""+
                ",\"username\":\"" + username+"\""+
                ",\"password\":\"" + password +"\""+
                ",\"name\":\"" + name+"\""+
                ",\"email\":\"" + email +"\""+
                ",\"telephone\":\"" + telephone+"\""+
                ",\"birthday\":\"" + birthday +"\""+
                ",\"sex\":\"" + sex+"\""+
                ",\"initdate\":\"" + initdate+"\""+
                ",\"classRoom\":\"" + classRoom +"\""+
                ",\"faculty\":\"" + faculty+"\""+
                ",\"imgpath\":\"" + imgpath+"\""+
                "}";
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInitdate() {
        return initdate;
    }

    public void setInitdate(String initdate) {
        this.initdate = initdate;
    }
}
