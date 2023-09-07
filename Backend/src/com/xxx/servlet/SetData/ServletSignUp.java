package com.xxx.servlet.SetData;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.xxx.bean.Log;
import com.xxx.bean.SignUp;
import com.xxx.service.serviceLog;
import com.xxx.service.serviceSignUp;
import com.xxx.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/3/30 20:12
 */
@WebServlet("/ServletSignUp")
public class ServletSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityID = request.getParameter("activityID");
        String activityName = request.getParameter("activityName");
        String username = request.getParameter("username");

        SignUp signUp = new SignUp();
        signUp.setActivityName(activityName);
        signUp.setActivityID(activityID);
        signUp.setUsername(username);
        signUp.setTime(new GetDate().getData());

        serviceSignUp.setData(signUp);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter out = response.getWriter();
        out.write("报名成功");
        out.flush();
        out.close();

        //记录log
        Log log = new Log();
        String date1 = new GetDate().getData();
        log.setDate(date1);
        log.setLog("参加活动");
        log.setUsername(username);
        serviceLog.setLog(log);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
