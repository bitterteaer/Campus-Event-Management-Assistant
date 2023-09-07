package com.example.task.bean;

/**
 * @author xzy
 * @create 2022/4/12 17:38
 */
public class UserAndSignAboutThisTeacher {
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

    private int signupID;//报名id
    private String activityID;//活动id
    private String activityName;//活动名
//    private String username;
    private String time;//报名时间
    private String qrStates;//签到状态

    @Override
    public String toString() {
        return "{" +
                "\"iduser\":\"" + iduser + '\"' +
                ", \"username\":\"" + username + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"telephone\":\"" + telephone + '\"' +
                ", \"birthday\":\"" + birthday + '\"' +
                ", \"sex\":\"" + sex + '\"' +
                ", \"initdate\":\"" + initdate + '\"' +
                ", \"classRoom\":\"" + classRoom + '\"' +
                ", \"faculty\":\"" + faculty + '\"' +
                ", \"imgpath\":\"" + imgpath + '\"' +
                ", \"signupID\":\"" + signupID +'\"'+
                ", \"activityID\":\"" + activityID + '\"' +
                ", \"activityName\":\"" + activityName + '\"' +
                ", \"qrStates\":\"" + qrStates + '\"' +
                ", \"time\":\"" + time + '\"' +
                '}';
    }

    public String getQrStates() {
        return qrStates;
    }

    public void setQrStates(String qrStates) {
        this.qrStates = qrStates;
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

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static void main(String[] args) {
        System.out.println(new UserAndSignAboutThisTeacher().toString());
    }
}
