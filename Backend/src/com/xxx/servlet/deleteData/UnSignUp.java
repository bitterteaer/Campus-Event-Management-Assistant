package com.xxx.servlet.deleteData;

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
 * @create 2022/4/1 16:04
 */
@WebServlet("/UnSignUp")
public class UnSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityID = request.getParameter("activityID");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

//        User u = new User();
//        u.setIduser(3);

        String data = null;
        if(u == null){
            data = "{" +
                    "\"allMemberNum\":\"" + 0 +"\""+
                    ",\"HasSignUp\":\"" + 0 +"\""+
                    ",\"states\":\"" + "未登录"+"\""+
                    "}";
        }else {
            if(serviceSignUp.deleteData(activityID,Integer.toString(u.getIduser())) == 1){
                data = serviceSignUp.SuccessAndGetSignUpStates(activityID);
            }else {
                data = "{" +
                        "\"allMemberNum\":\"" + 0 +"\""+
                        ",\"HasSignUp\":\"" + 0 +"\""+
                        ",\"states\":\"" + "取消报名失败"+"\""+
                        "}";
            }
        }

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(data);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
