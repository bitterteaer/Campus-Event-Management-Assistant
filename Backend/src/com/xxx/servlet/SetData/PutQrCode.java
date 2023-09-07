package com.xxx.servlet.SetData;

import com.xxx.bean.User;
import com.xxx.bean.qrCode;
import com.xxx.service.serviceQrCode;
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
 * @create 2022/3/20 16:15
 */
@WebServlet("/PutQrCode")
public class PutQrCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String qrData = request.getParameter("qrData");
//        System.out.println(qrData);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            out.write("签到失败：用户未登录");
        } else{
            String date = new GetDate().getData();
            qrCode q = new qrCode();
            q.setData(qrData);
            q.setDate(date);
            q.setUser(user.getUsername());
            new serviceQrCode().setData(q);

            out.write("签到成功："+user.getName());
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

//    public static void main(String[] args) {
//        System.out.println("{\"activityId\":\"null\", \"inChargeID\":\"111\", \"inChargeNB\":\"111\", \"menberTotal\":\"22\", \"havaSignUp\":\"null\", \"summery\":\"null\", \"imgSrc\":\"null\", \"activityName\":\"333\", \"ativityPlace\":\"33\", \"signUpTime\":\"null\", \"deadlineForSign\":\"null\", \"ativityTime\":\"44\", \"deadlineForAtivity\":\"null\", \"remark\":\"33\", \"type\":\"null\", \"theme\":\"null\"}".length());
//    }
}
