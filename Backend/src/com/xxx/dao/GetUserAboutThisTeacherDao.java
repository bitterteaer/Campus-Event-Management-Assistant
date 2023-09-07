package com.xxx.dao;
import com.xxx.bean.User;
import com.xxx.util.countLog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public class GetUserAboutThisTeacherDao {
    public GetUserAboutThisTeacherDao() throws IOException {
    }

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/xxx/dao/mybaits-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    public List<User> execute(String inChaerge) throws IOException, ParseException {
        //第一步：获取到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory= countLog.getSqlSessionFactory();
        //第二步：从SqlSessionFactory对象中获取到SqlSession对象
        SqlSession openSession=sqlSessionFactory.openSession();
        //第三步：从SqlSession获取相应接口的实现类对象
        //会为接口自动创建一个代理对象，代理对象去实现增删改查方法
        LogMapper mapper =openSession.getMapper(LogMapper.class);
        List<User>  hehe = mapper.getUserAboutThisTeacher(inChaerge);
        return hehe;
    }

}
