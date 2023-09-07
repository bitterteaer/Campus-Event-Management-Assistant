package com.xxx.servlet.login;

import com.xxx.bean.Log;
import com.xxx.bean.Teacher;
import com.xxx.service.serviceLog;
import com.xxx.service.serviceLogin;
import com.xxx.service.serviceTeacher;
import com.xxx.util.GetDate;

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
 * @create 2022/3/27 15:43
 */
@WebServlet("/TeacherLogin")
public class TeacherLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Teacher teacher = new Teacher();
        teacher.setPassword(password);
        teacher.setUsername(username);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        Teacher loginTeacher = serviceTeacher.login(teacher);
        if (loginTeacher != null) {
            HttpSession session = request.getSession();
            session.setAttribute("Teacher", loginTeacher);
            System.out.println("教师登录成功");

            //记录log
            Log log = new Log();
            String date = new GetDate().getData();
            log.setDate(date);
            log.setLog("登录");
            log.setUsername(teacher.getUsername());
            serviceLog.setLog(log);

            response.sendRedirect("/TeacherIndex");
//            out.write("success");
        } else {
            System.out.println("教师登录失败");
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
