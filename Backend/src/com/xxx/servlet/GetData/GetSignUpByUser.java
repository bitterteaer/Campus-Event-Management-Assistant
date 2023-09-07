package com.xxx.servlet.GetData;

import com.xxx.service.serviceSignUp;
import sun.awt.windows.WLightweightFramePeer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/4/3 16:11
 */
@WebServlet("/GetSignUpByUser")
public class GetSignUpByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println(username+" in GetSignUpByUser");

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

//        System.out.println(serviceSignUp.getDataByUser(username).toString());
        writer.write(serviceSignUp.getDataByUser(username).toString());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
