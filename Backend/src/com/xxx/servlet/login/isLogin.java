package com.xxx.servlet.login;

import com.google.gson.Gson;
import com.xxx.bean.User;
import com.xxx.service.serviceLogin;

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
 * @create 2021/10/24 1:00
 */
@WebServlet("/isLogin")
public class isLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(user!=null && serviceLogin.getLogin(user)==1){
            String json = new Gson().toJson(user);
            out.write(json);
        }
        else {
//            User user1 = new User();
//            user1.setName("未登录");
//            String json = new Gson().toJson(user1);
//            out.write(json);
            out.write("error");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
