package com.xxx.servlet;

import com.xxx.bean.Log;
import com.xxx.bean.User;
import com.xxx.service.serviceLog;
import com.xxx.service.serviceRegister;
import com.xxx.util.GetDate;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzy
 * @create 2021/10/18 14:27
 */
@WebServlet("/register")
public class register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in register");
        User u = new User();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String date = simpleDateFormat.format(new Date());

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");

        u.setUsername(username);
        u.setPassword(password);
        u.setName(name);
        u.setEmail(email);
        u.setTelephone(telephone);
        u.setBirthday(birthday);
        u.setSex(sex);
        u.setInitdate(date);

        System.out.println("register");
        System.out.println(u);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (serviceRegister.getRegister(u) == 1) {
            System.out.println("register success");
            out.write("success");

            //记录log
            Log log = new Log();
            String date1 = new GetDate().getData();
            log.setDate(date1);
            log.setLog("注册");
            log.setUsername(username);
            serviceLog.setLog(log);

        } else {
            System.out.println("register error");
            out.write("error");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
