package com.xxx.servlet;

import com.xxx.bean.Ament;
import com.xxx.bean.SuperUser;
import com.xxx.service.serviceAnnouncement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xzy
 * @create 2021/10/30 16:34
 */
@WebServlet("/Announcement")
public class Announcement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SuperUser superUser = (SuperUser) session.getAttribute("superUser");

        String data = request.getParameter("data");
        System.out.println(data);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String date = simpleDateFormat.format(new Date());

        Ament ament = new Ament();
        ament.setData(data);
        ament.setDate(date);
        ament.setUsername(superUser.getName());

        serviceAnnouncement.setData(ament);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
