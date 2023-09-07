package com.xxx.bean;

/**
 * @author xzy
 * @create 2021/10/24 17:32
 */
public class SuperUser {
    private String name;//管理员id
    private String password;//管理员密码
    private String fullName;//管理员姓名
    private String phone;//管理员联系方式

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"fullName\":\"" + fullName + '\"' +
                ", \"phone\":\"" + phone + '\"' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
