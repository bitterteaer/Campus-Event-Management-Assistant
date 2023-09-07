package com.xxx.servlet.GetData;

import com.xxx.service.serviceSignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

/**
 * @author xzy
 * @create 2022/4/19 20:21
 */
@WebServlet("/SignUpNumByActID")
public class SignUpNumByActID extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ActID = request.getParameter("actID");
//        System.out.println(ActID);

        String number = serviceSignUp.GetSignUpNumByActID(ActID);
//        System.out.println(number);

        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text");
        PrintWriter writer = response.getWriter();

        writer.write(number);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

//    public static void main(String[] args) {
//        System.out.println(serviceSignUp.GetSignUpNumByActID("10001"));
//    }
}
