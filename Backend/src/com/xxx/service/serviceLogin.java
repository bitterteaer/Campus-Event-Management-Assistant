package com.xxx.service;

import com.xxx.bean.SuperUser;
import com.xxx.bean.User;
import com.xxx.dao.userDao;

/**
 * @author xzy
 * @create 2021/10/15 15:25
 */
public class serviceLogin {
    //登录
    public static int getLogin(User user) {
        userDao ud = new userDao();
        return ud.login(user);
    }

    public static User getData(User u) {
        userDao ud = new userDao();
        return ud.selectOne(u.getUsername());
    }

    public static int getSuperLogin(SuperUser superUser){
        userDao ud = new userDao();
        return ud.superLogin(superUser);
    }
}
