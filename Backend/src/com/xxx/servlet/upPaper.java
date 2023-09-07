package com.xxx.servlet;

import com.mysql.cj.xdevapi.Session;
import com.xxx.bean.Feedback;
import com.xxx.bean.User;
import com.xxx.service.serviceFeedback;

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
 * @create 2022/1/30 16:53
 */
@WebServlet("/upPaper")
public class upPaper extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Feedback feedback = new Feedback();

        String text = request.getParameter("text");
//        System.out.println(text);
        feedback.setData(text);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        feedback.setDate(date);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            feedback.setUserName(user.getUsername());
        }else {
            feedback.setUserName(null);
        }

        //存入数据库
        serviceFeedback.setData(feedback);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}

