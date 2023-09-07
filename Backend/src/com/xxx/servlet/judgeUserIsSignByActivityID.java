package com.xxx.servlet;

import com.xxx.bean.Activity;
import com.xxx.bean.User;
import com.xxx.service.serviceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/4/19 15:08
 */
@WebServlet("/judgeUserIsSignByActivityID")
public class judgeUserIsSignByActivityID extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String actID = request.getParameter("actID");
        Activity activity = new Activity();
        activity.setActivityId(actID);

        boolean resule = serviceUser.judgeTheAct(user,activity);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter writer = response.getWriter();
        if(resule == true){
            writer.write("success");
        }else{
            writer.write("error");
        }

        writer.flush();
        writer.close();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

//    {"id1":0,"id2":2}
}
