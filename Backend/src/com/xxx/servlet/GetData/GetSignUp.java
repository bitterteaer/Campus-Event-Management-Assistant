package com.xxx.servlet.GetData;

import com.xxx.service.serviceSignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author xzy
 * @create 2022/3/31 21:54
 */
@WebServlet("/GetSignUp")
public class GetSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String
        List list = serviceSignUp.getData();

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        writer.write(list.toString());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
