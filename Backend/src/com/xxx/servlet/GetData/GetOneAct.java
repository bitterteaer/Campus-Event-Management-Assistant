package com.xxx.servlet.GetData;

import com.xxx.bean.Activity;
import com.xxx.service.serviceActiviteData;

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
 * @create 2022/3/30 20:23
 */
@WebServlet("/GetOneAct")
public class GetOneAct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Activity activity = new serviceActiviteData().getOneData(id);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter out = response.getWriter();

        out.write(activity.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
