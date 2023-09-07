package com.xxx.service;

import com.xxx.bean.Log;
import com.xxx.dao.LogDao;

import java.util.List;

/**
 * @author xzy
 * @create 2021/10/24 12:45
 */
public class serviceLog {
    public static int setLog(Log log){
        LogDao logDao = new LogDao();
        return logDao.add(log);
    }

    public static List getData(){
        LogDao logDao = new LogDao();
        return logDao.select();
    }
}
