package com.xxx.servlet;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.xxx.bean.Feedback;
import com.xxx.service.serviceFeedback;

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
 * @create 2022/2/19 15:39
 */
@WebServlet("/feedback")
public class feedback extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = (List) serviceFeedback.getData();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        out.write(list.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public static void main(String[] args) {
        List list = (List) serviceFeedback.getData();
        System.out.println(list);
    }
}
