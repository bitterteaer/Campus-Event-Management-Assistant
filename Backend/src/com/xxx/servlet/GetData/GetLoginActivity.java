package com.xxx.servlet.GetData;

import com.sun.webkit.graphics.WCCamera;
import com.xxx.bean.User;
import com.xxx.service.serviceActiviteData;

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
 * @create 2022/4/12 14:59
 */
@WebServlet("/GetLoginActivity")
public class GetLoginActivity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter writer = response.getWriter();
        writer.write(new serviceActiviteData().getDataAboutThisUser(user).toString());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
