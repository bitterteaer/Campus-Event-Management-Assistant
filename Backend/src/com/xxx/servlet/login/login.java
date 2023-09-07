package com.xxx.servlet.login;

import com.xxx.bean.Log;
import com.xxx.bean.User;
import com.xxx.service.serviceLog;
import com.xxx.service.serviceLogin;
import com.xxx.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author xzy
 * @create 2021/10/18 10:39
 */
@WebServlet("/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        String date = df.format(new Date());// new Date()为获取当前系统时间

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("login");
        System.out.println(user);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (serviceLogin.getLogin(user) == 1) {
            session.setAttribute("user", serviceLogin.getData(user));
            System.out.println("登录成功");

            //记录log
            Log log = new Log();
            log.setDate(new GetDate().getData());
            log.setLog("登录");
            log.setUsername(user.getUsername());
            serviceLog.setLog(log);

            out.write("success");
        } else {
            System.out.println("登录失败");
            out.write("error");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
