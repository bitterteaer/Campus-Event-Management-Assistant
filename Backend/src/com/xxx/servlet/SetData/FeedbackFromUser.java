package com.xxx.servlet.SetData;

import com.xxx.bean.Feedback;
import com.xxx.bean.User;
import com.xxx.service.serviceFeedback;
import com.xxx.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/4/17 15:48
 */
@WebServlet("/FeedbackFromUser")
public class FeedbackFromUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        String type = request.getParameter("type");
        data = type+":"+data;
        System.out.println(data);

//        System.out.println("test1");
        User u = (User) request.getSession().getAttribute("user");
//        System.out.println("test2");
        Feedback feedback = new Feedback();
        feedback.setData(data);
        if(u == null){
            feedback.setUserName(null);
        }else{
            feedback.setUserName(u.getUsername());
        }
        feedback.setDate(new GetDate().getData());

        System.out.println(feedback.toString());
        serviceFeedback.setData(feedback);

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("success");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public static void main(String[] args) {
        System.out.println(new GetDate().getData());
    }
}
