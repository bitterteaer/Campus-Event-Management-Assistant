package com.xxx.service;

import com.xxx.bean.User;
import com.xxx.dao.userDao;

/**
 * @author xzy
 * @create 2021/10/18 14:40
 */
public class serviceRegister {
    public static int getRegister(User u) {
        userDao ud = new userDao();
        return ud.add(u);
    }
}
