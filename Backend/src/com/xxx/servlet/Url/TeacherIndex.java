package com.xxx.servlet.Url;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xzy
 * @create 2022/3/27 16:11
 */
@WebServlet("/TeacherIndex")
public class TeacherIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("layui/examples/layout-admin-check.html");
//        requestDispatcher.forward(request,response);
        response.sendRedirect("layui/examples/layout-admin-teacher.html");

//        request.getSession().getServletContext().getRequestDispatcher("/layui/examples/layout-admin.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
