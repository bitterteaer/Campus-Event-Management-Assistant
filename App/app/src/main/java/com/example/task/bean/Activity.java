package com.example.task.bean;

import java.io.Serializable;

public class Activity implements Serializable {
    private String activityId;//活动id
    private String inChargeID;//负责人工号
    private String inChargeNB;//负责人手机号
    private String menberTotal;//活动人数
    private String havaSignUp;//活动已报名人数

    private String summery;//活动简介
    private String imgSrc;//活动图片路径
    private String activityName;//活动名
    private String ativityPlace;//活动地点
    //日期
    private String initDate;//具体日期 yyyy-mm-dd
    private String signUpTime;//报名开始时间 yyyy-mm-dd
    private String deadlineForSign;//报名截至时间 yyyy-mm-dd
    private String ativityTime;//活动开始时间 hh-mm
    private String deadlineForAtivity;//活动截至时间 hh-mm

    private String remark;//活动备注
    private String type;//活动类型
    private String theme;//活动主题

    @Override
    public String toString() {
        return "{" +
                "\"activityId\":\"" + activityId + '\"' +
                ", \"inChargeID\":\"" + inChargeID + '\"' +
                ", \"inChargeNB\":\"" + inChargeNB + '\"' +
                ", \"menberTotal\":\"" + menberTotal + '\"' +
                ", \"havaSignUp\":\"" + havaSignUp + '\"' +
                ", \"summery\":\""+ summery + '\"' +
                ", \"imgSrc\":\""+ imgSrc + '\"' +
                ", \"activityName\":\"" + activityName + '\"' +
                ", \"ativityPlace\":\"" + ativityPlace + '\"' +
                ", \"signUpTime\":\"" + signUpTime + '\"' +
                ", \"deadlineForSign\":\"" + deadlineForSign + '\"' +
                ", \"ativityTime\":\"" + ativityTime + '\"' +
                ", \"deadlineForAtivity\":\"" + deadlineForAtivity + '\"' +
                ", \"remark\":\"" + remark + '\"' +
                ", \"initDate\":\"" + initDate + '\"' +
                ", \"type\":\"" + type + '\"' +
                ", \"theme\":\"" + theme + '\"' +
                '}';
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(String signUpTime) {
        this.signUpTime = signUpTime;
    }

    public String getDeadlineForSign() {
        return deadlineForSign;
    }

    public void setDeadlineForSign(String deadlineForSign) {
        this.deadlineForSign = deadlineForSign;
    }

    public String getDeadlineForAtivity() {
        return deadlineForAtivity;
    }

    public void setDeadlineForAtivity(String deadlineForAtivity) {
        this.deadlineForAtivity = deadlineForAtivity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getHavaSignUp() {
        return havaSignUp;
    }

    public void setHavaSignUp(String havaSignUp) {
        this.havaSignUp = havaSignUp;
    }

    public String getInChargeID() {
        return inChargeID;
    }

    public void setInChargeID(String inChargeID) {
        this.inChargeID = inChargeID;
    }

    public String getInChargeNB() {
        return inChargeNB;
    }

    public void setInChargeNB(String inChargeNB) {
        this.inChargeNB = inChargeNB;
    }

    public String getMenberTotal() {
        return menberTotal;
    }

    public void setMenberTotal(String menberTotal) {
        this.menberTotal = menberTotal;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAtivityPlace() {
        return ativityPlace;
    }

    public void setAtivityPlace(String ativityPlace) {
        this.ativityPlace = ativityPlace;
    }

    public String getAtivityTime() {
        return ativityTime;
    }

    public void setAtivityTime(String ativityTime) {
        this.ativityTime = ativityTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
