package com.xxx.service;

import com.xxx.bean.Ament;
import com.xxx.dao.AmentDao;

import java.util.List;

/**
 * @author xzy
 * @create 2021/10/30 16:53
 */
public class serviceAnnouncement {
    public static int setData(Ament ament){
        AmentDao amentDao = new AmentDao();
        return amentDao.add(ament);
    }
    public  static List getData(){
        AmentDao amentDao = new AmentDao();
        return amentDao.select();
    }
}
