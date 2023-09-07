package com.xxx.util;

import com.xxx.bean.Echarts;
import com.xxx.bean.LogResult;
import com.xxx.dao.LogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * @author 李呵呵
 * @create 2022/3/29 15:56
 */
 class initCount {
    public static void main(String[] args) throws ParseException {
        try {
            countLog.execute(7);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class countLog {
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/xxx/dao/mybaits-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void execute(int beforeDayNum) throws IOException, ParseException {
        //第一步：获取到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory= countLog.getSqlSessionFactory();
        //第二步：从SqlSessionFactory对象中获取到SqlSession对象
        SqlSession openSession=sqlSessionFactory.openSession();
        //第三步：从SqlSession获取相应接口的实现类对象
        //会为接口自动创建一个代理对象，代理对象去实现增删改查方法
        LogMapper mapper =openSession.getMapper(LogMapper.class);

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String today = simpleDateFormat.format(new Date());
            String pastDay = PastDayUtile.getPastDay(7);
            System.out.println(today);
            System.out.println(pastDay);
            List<LogResult> loginTotal = mapper.getLogResultByLogAndTime("登录",today,pastDay);
            List<LogResult> registerTotal = mapper.getLogResultByLogAndTime("注册",today,pastDay);
            List<LogResult> publishTotal = mapper.getLogResultByLogAndTime("发布活动",today,pastDay);
            List<LogResult> joinTotal = mapper.getLogResultByLogAndTime("参加活动",today,pastDay);
            System.out.println("获取到登录，并按日期排序");
            for(LogResult a : loginTotal){
                System.out.println(a.toString());
            }
            System.out.println();

            System.out.println("获取到注册，并按日期排序");
            for(LogResult a : registerTotal){
                System.out.println(a.toString());
            }
            System.out.println();

            System.out.println("获取到发布活动，并按日期排序");
            for(LogResult a : publishTotal){
                System.out.println(a.toString());
            }
            System.out.println();

            System.out.println("获取到参加活动，并按日期排序");
            for(LogResult a : joinTotal){
                System.out.println(a.toString());
            }

            List<Echarts> echartsList = new ArrayList<>();
            ListIterator<LogResult> loginTotalIterato = loginTotal.listIterator();
            ListIterator<LogResult> registerTotallIterato = registerTotal.listIterator();
            ListIterator<LogResult> publishTotalIterato = publishTotal.listIterator();
            ListIterator<LogResult> joinTotalIterato = joinTotal.listIterator();

            Integer login = 0,register=0,putActivite=0,attendActivite=0;

            for(int i=0 ; i<=beforeDayNum ;i++){
                if(loginTotalIterato.hasNext()){
                    LogResult logResult = loginTotalIterato.next();
                    if( PastDayUtile.getPastDay(i).equals(logResult.getDate())){
                        login += logResult.getTotal();
                    }else{
                        logResult = loginTotalIterato.previous();
                    }
                }

                if(registerTotallIterato.hasNext()){
                    LogResult logResult = registerTotallIterato.next();
                    if( PastDayUtile.getPastDay(i).equals(logResult.getDate())){
                        register += logResult.getTotal();
                    }else{
                        logResult = registerTotallIterato.previous();
                    }
                }

                if(publishTotalIterato.hasNext()){
                    LogResult logResult = publishTotalIterato.next();
                    if( PastDayUtile.getPastDay(i).equals(logResult.getDate())){
                        putActivite += logResult.getTotal();
                    }else{
                        logResult = publishTotalIterato.previous();
                    }
                }

                if(joinTotalIterato.hasNext()){
                    LogResult logResult = joinTotalIterato.next();
                    if( PastDayUtile.getPastDay(i).equals(logResult.getDate())){
                        attendActivite += logResult.getTotal();
                    }else{
                        logResult = joinTotalIterato.previous();
                    }
                }
                System.out.println("总数");
                System.out.println("登录"+login);
                System.out.println("注册"+register);
                System.out.println("发布活动"+putActivite);
                System.out.println("参加活动"+attendActivite);
                System.out.println(mapper.updateEcharts(8-i,login,register,putActivite,attendActivite,
                        login+register+putActivite+attendActivite, PastDayUtile.getPastDay(i)));
                System.out.println("fdsafdsfsdafasdfsadfdsaf");
                openSession.commit();
                login = 0;register = 0;putActivite = 0;attendActivite = 0;
            }
            openSession.close();

//            List<LogResult> loginTotal = mapper.getLogResultByLog("登录");
//            List<LogResult> registerTotal = mapper.getLogResultByLog("注册");
//            List<LogResult> publishTotal = mapper.getLogResultByLog("发布活动");
//            List<LogResult> joinTotal = mapper.getLogResultByLog("参加活动");
//
//            System.out.println("获取到登录，并按日期排序");
//            for(LogResult a : loginTotal){
//                System.out.println(a.toString());
//            }
//            System.out.println();
//
//            System.out.println("获取到注册，并按日期排序");
//            for(LogResult a : registerTotal){
//                System.out.println(a.toString());
//            }
//            System.out.println();
//
//            System.out.println("获取到发布活动，并按日期排序");
//            for(LogResult a : publishTotal){
//                System.out.println(a.toString());
//            }
//            System.out.println();
//
//            System.out.println("获取到参加活动，并按日期排序");
//            for(LogResult a : joinTotal){
//                System.out.println(a.toString());
//            }
        }
        finally {
            openSession.close();
        }
    }
}



