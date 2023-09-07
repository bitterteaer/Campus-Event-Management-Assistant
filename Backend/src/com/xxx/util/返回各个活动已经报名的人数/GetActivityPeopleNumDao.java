package com.xxx.util.返回各个活动已经报名的人数;

import com.xxx.bean.ActivityPeopleNumBean;
import com.xxx.dao.LogMapper;
import com.xxx.util.countLog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GetActivityPeopleNumDao {
    public GetActivityPeopleNumDao() throws IOException {
    }

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/xxx/dao/mybaits-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    public List<ActivityPeopleNumBean> execute() throws IOException {
        //第一步：获取到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory= countLog.getSqlSessionFactory();
        //第二步：从SqlSessionFactory对象中获取到SqlSession对象
        SqlSession openSession=sqlSessionFactory.openSession();
        //第三步：从SqlSession获取相应接口的实现类对象
        //会为接口自动创建一个代理对象，代理对象去实现增删改查方法
        com.xxx.dao.LogMapper mapper =openSession.getMapper(com.xxx.dao.LogMapper.class);
        List<ActivityPeopleNumBean> activityPeopleNumBeanList = mapper.getActivityPeopleNum();
        List<String> zeroActivityName = GetActivityPeopleNumDao.execute2();
        for(String a : zeroActivityName){
            activityPeopleNumBeanList.add(new ActivityPeopleNumBean(a,0));
        }
        return activityPeopleNumBeanList;
    }

    public static List<String> execute2() throws IOException {
        SqlSessionFactory sqlSessionFactory= countLog.getSqlSessionFactory();
        //第二步：从SqlSessionFactory对象中获取到SqlSession对象
        SqlSession openSession=sqlSessionFactory.openSession();
        //第三步：从SqlSession获取相应接口的实现类对象
        //会为接口自动创建一个代理对象，代理对象去实现增删改查方法
        com.xxx.dao.LogMapper mapper =openSession.getMapper(LogMapper.class);
        List<String> ActivityName = mapper.getZeroActivityPeopleNum();
        return ActivityName;
    }
}
