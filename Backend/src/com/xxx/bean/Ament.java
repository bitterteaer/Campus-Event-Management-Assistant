package com.xxx.bean;

/**
 * @author xzy
 * @create 2021/10/30 16:39
 */
public class Ament {
    private int idannouncement;
    private String username;
    private String data;
    private String date;

    @Override
    public String toString() {
        return "Announcement{" +
                "idannouncement=" + idannouncement +
                ", username='" + username + '\'' +
                ", data='" + data + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getIdannouncement() {
        return idannouncement;
    }

    public void setIdannouncement(int idannouncement) {
        this.idannouncement = idannouncement;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
