package com.xxx.servlet.login;

import com.xxx.bean.Log;
import com.xxx.bean.SuperUser;
import com.xxx.service.serviceLog;
import com.xxx.service.serviceLogin;
import com.xxx.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzy
 * @create 2021/10/24 17:30
 */
@WebServlet("/SuperLogin")
public class SuperLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        SuperUser superUser = new SuperUser();
        superUser.setName(name);
        superUser.setPassword(password);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        String date = simpleDateFormat.format(new Date());

        System.out.println("SuperLogin");
        System.out.println(superUser);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (serviceLogin.getSuperLogin(superUser) == 1) {
            session.setAttribute("superUser", superUser);
            System.out.println("超级用户登录成功");

            //记录log
            Log log = new Log();
            log.setDate(new GetDate().getData());
            log.setLog("登录");
            log.setUsername(superUser.getName());
            serviceLog.setLog(log);

            response.sendRedirect("/indexUrl");
//            out.write("success");
        } else {
            System.out.println("超级用户登录失败");
            out.println("<script>");
            out.println("alert('登录失败');");
            out.println("window.history.back();");
            out.println("</script>");

            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
