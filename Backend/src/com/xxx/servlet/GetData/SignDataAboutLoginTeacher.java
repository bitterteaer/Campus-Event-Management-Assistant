package com.xxx.servlet.GetData;

import com.xxx.bean.Teacher;
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
 * @create 2022/4/11 21:23
 */
@WebServlet("/SignDataAboutLoginTeacher")
public class SignDataAboutLoginTeacher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("Teacher");

//        Teacher teacher1 = new Teacher();
//        teacher1.setUsername("101");

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(serviceUser.UserAboutThisTeacher(teacher).toString());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
