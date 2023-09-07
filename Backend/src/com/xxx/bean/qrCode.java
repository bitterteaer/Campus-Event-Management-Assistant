package com.xxx.bean;

/**
 * @author xzy
 * @create 2022/3/20 16:52
 */
public class qrCode {
    String data;
    String date;
    String user;

    @Override
    public String toString() {
        return "{" +
                "\"data\""+":"+ data  +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

//    public static void main(String[] args) {
//        System.out.println(new PutQrCode().toString());
//    }
}
