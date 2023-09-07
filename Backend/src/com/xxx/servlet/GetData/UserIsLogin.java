package com.xxx.servlet.GetData;

import com.xxx.bean.User;

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
 * @create 2022/3/30 20:42
 */
@WebServlet("/UserIsLogin")
public class UserIsLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if(u == null){
            out.write("error");
            out.flush();
            out.close();
        }else{
            out.write(u.toString());
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
