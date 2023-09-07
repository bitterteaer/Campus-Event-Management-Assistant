package com.xxx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzy
 * @create 2021/11/30 18:04
 */
public class GetDate {
    public String getData(){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");//设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式

        String data = df.format(new Date());
//        String[] data = df.format(new Date()).split("-");
//        String fin = data[0]+"/"+data[1]+"/"+data[2];
//        System.out.println(fin);// new Date()为获取当前系统时间

        return data;
    }
    public String getDateToMinute(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");//设置日期格式
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        String data = df.format(new Date());
//        String[] data = df.format(new Date()).split("-");
//        String fin = data[0]+"/"+data[1]+"/"+data[2];
//        System.out.println(fin);// new Date()为获取当前系统时间

        return data;
    }
    public static String changeDate(String date){
        String[] data = date.split("-");
        String end = data[0]+"/"+data[1]+"/"+data[2];

        return end;
    }
    public static String changeTime(String time){
        String[] data = time.split(":");
        String end = data[0]+"："+data[1]+"："+data[2];

        return end;
    }
//    public String getTime(){
//
//    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
