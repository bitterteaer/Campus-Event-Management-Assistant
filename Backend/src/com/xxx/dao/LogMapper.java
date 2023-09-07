package com.xxx.dao;


import com.xxx.bean.ActivityPeopleNumBean;
import com.xxx.bean.Log;
import com.xxx.bean.LogResult;
import com.xxx.bean.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    Integer getTaotal();

    @MapKey("idlog")
    List<Log> getLogByDate(String date);

    List<LogResult> getLogResultByLog(String log);

    List<LogResult> getLogResultByLogAndTime(@Param("log") String log,
                                             @Param("today") String today,
                                             @Param("pastDay") String pastDay);

    int updateEcharts(@Param("idecharts") int idecharts,
                      @Param("login") int login,
                      @Param("register") int register,
                      @Param("putActivate") int putActivate,
                      @Param("attendActivate") int attendActivate,
                      @Param("all") int all,
                      @Param("date") String date);

    List<User> getUserAboutThisTeacher(String inChargeID);

    List<ActivityPeopleNumBean>  getActivityPeopleNum();

    List<String> getZeroActivityPeopleNum();
}
