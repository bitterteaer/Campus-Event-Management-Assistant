package com.xxx.servlet.SetData;

import com.xxx.bean.Evaluate;
import com.xxx.bean.User;
import com.xxx.service.serviceEvaluate;
import com.xxx.util.GetDate;

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
 * @create 2022/4/5 20:43
 */
@WebServlet("/servletEvaluate")
public class servletEvaluate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

//        String userID = request.getParameter("userID");
        String activityID = request.getParameter("activityID");
        String data = request.getParameter("data");
        String date = new GetDate().getDateToMinute();

        Evaluate evaluate = new Evaluate();
        evaluate.setUserID(Integer.toString(u.getIduser()));
        evaluate.setActivityID(activityID);
        evaluate.setData(data);
        evaluate.setDate(date);

        serviceEvaluate.setData(evaluate);

        PrintWriter writer = response.getWriter();
        writer.write("success");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
