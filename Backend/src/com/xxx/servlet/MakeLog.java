package com.xxx.servlet;

import com.xxx.bean.User;
import com.xxx.service.serviceLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzy
 * @create 2021/10/24 14:04
 */
@WebServlet("/MakeLog")
public class MakeLog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String log = request.getParameter("log");
        HttpSession session = request.getSession();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间

        User user = (User) session.getAttribute("user");
        com.xxx.bean.Log log1 = new com.xxx.bean.Log();
        log1.setUsername(user.getUsername());
        log1.setDate(date);
        log1.setLog(log);
        serviceLog.setLog(log1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
