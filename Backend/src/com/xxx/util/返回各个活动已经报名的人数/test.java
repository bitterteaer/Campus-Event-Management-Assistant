package com.xxx.util.返回各个活动已经报名的人数;



import com.xxx.bean.ActivityPeopleNumBean;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author xzy
 * @create 2021/11/30 20:50
 */
public class test {
    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println("12-11".equals("12-11"));
//        System.out.println(new GetDate().getData());
//        System.out.println("/home/ubuntu/apache-tomcat-9.0.54/webapps/ROOT/images2022_04_05_09_16_391122.png".length());

        GetActivityPeopleNumDao getActivityPeopleNumDao = new GetActivityPeopleNumDao();
        List<ActivityPeopleNumBean> list = getActivityPeopleNumDao.execute();

        for (ActivityPeopleNumBean a : list){
            System.out.println(a.toString());
        }
    }
}
