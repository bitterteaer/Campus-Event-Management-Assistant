package com.xxx.servlet;

import com.google.gson.Gson;
import com.xxx.bean.User;
import com.xxx.service.serviceSignUp;

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
 * @create 2022/4/25 15:58
 */
@WebServlet("/SignUpBackNum")
public class SignUpBackNum extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getParameter("ID");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

//        User u = new User();
//        u.setIduser(3);

        String data;
        if(u == null){
            data = "{" +
                    "\"allMemberNum\":\"" + 0 +"\""+
                    ",\"HasSignUp\":\"" + 0 +"\""+
                    ",\"states\":\"" + "未登录"+"\""+
                    "}";
        }else {
            data = serviceSignUp.SignUpBackNum(ID,u);
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter writer = response.getWriter();
        writer.write(data);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
