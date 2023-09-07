package com.xxx.servlet.SetData;

import com.xxx.bean.Activity;
import com.xxx.bean.Log;
import com.xxx.service.serviceActiviteData;
import com.xxx.service.serviceLog;
import com.xxx.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author xzy
 * @create 2022/3/14 19:34
 */
@WebServlet("/ActivityData")
public class ActivityData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ActivityData");

        String inChargeID = request.getParameter("inChargeID");
        String inChargeNB = request.getParameter("inChargeNB");
        String ativityPlace = request.getParameter("ativityPlace");
        String activityTheme = request.getParameter("activityTheme");
        String activityType = request.getParameter("activityType");
        String menberTotal = request.getParameter("menberTotal");
        String initDate = request.getParameter("initDate");
        String signUpTime = request.getParameter("signUpTime");
        String deadlineForSign = request.getParameter("deadlineForSign");
        String deadlineForAtivity = request.getParameter("deadlineForAtivity");
        String ativityTime = request.getParameter("ativityTime");
        String summery = request.getParameter("summery");
        String activityName = request.getParameter("activityName");

        Activity activity = new Activity();
        activity.setActivityName(activityName);
        activity.setInChargeID(inChargeID);
        activity.setInChargeNB(inChargeNB);
        activity.setMenberTotal(menberTotal);
        activity.setActivityName(activityName);
        activity.setAtivityPlace(ativityPlace);
        activity.setAtivityTime(ativityTime);
        activity.setSummery(summery);
//        activity.setSummery(summery);
        activity.setType(activityType);
        activity.setTheme(activityTheme);

        activity.setInitDate(GetDate.changeDate(initDate));
        activity.setSignUpTime(GetDate.changeDate(signUpTime));
        activity.setDeadlineForAtivity(deadlineForAtivity);
        activity.setDeadlineForSign(GetDate.changeDate(deadlineForSign));
        activity.setActivityId(Long.toString(new Date().getTime()));

        System.out.println(activity);

        //写入数据库
        serviceActiviteData s = new serviceActiviteData();
        s.setData(activity);

        HttpSession session = request.getSession();
        session.setAttribute("activity",activity);

        //记录log
        Log log = new Log();
        String date1 = new GetDate().getData();
        log.setDate(date1);
        log.setLog("发布活动");
        log.setUsername(inChargeID);
        serviceLog.setLog(log);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
