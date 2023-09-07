package com.xxx.bean;

public class LogResult {
    private String date;
    private String log;
    private Integer total;

    public LogResult(String date, String log, Integer total) {
        this.date = date;
        this.log = log;
        this.total = total;
    }

    //必须要添加无参的构造函数
    public LogResult() {
    }

    @Override
    public String toString() {
        return "LogResult{" +
                "date='" + date + '\'' +
                ", log='" + log + '\'' +
                ", total=" + total +
                '}';
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
