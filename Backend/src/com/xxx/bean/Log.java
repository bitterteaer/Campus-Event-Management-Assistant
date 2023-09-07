package com.xxx.bean;

/**
 * @author xzy
 * @create 2021/10/24 12:40
 */
public class Log {
    private int idlog;
    private String username;
    private String date;
    private String log;

    @Override
    public String toString() {
        return "Log{" +
                "idlog=" + idlog +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", log='" + log + '\'' +
                '}';
    }

    public int getIdlog() {
        return idlog;
    }

    public void setIdlog(int idlog) {
        this.idlog = idlog;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
