package com.xxx.servlet.GetData;

import com.xxx.bean.Teacher;
import com.xxx.service.serviceTeacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/3/31 21:31
 */
@WebServlet("/GetOneTeacher")
public class GetOneTeacher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Teacher teacher = new serviceTeacher().getOneData(id);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter writer = response.getWriter();
        writer.write(teacher.toString());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
