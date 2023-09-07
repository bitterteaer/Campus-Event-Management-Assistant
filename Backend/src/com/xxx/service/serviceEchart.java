package com.xxx.service;

import com.xxx.dao.EchartDao;

import java.util.List;

/**
 * @author xzy
 * @create 2021/10/30 1:07
 */
public class serviceEchart {
    static public String getData(){
        List list = new EchartDao().selectInNum(10);
        String json = list.toString();
        return json;
    }
}
