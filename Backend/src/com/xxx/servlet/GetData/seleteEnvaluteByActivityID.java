package com.xxx.servlet.GetData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.xxx.service.serviceEvaluate.seleteByActID;

/**
 * @author xzy
 * @create 2022/4/19 15:35
 */
@WebServlet("/seleteEnvaluteByActivityID")
public class seleteEnvaluteByActivityID extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actID = request.getParameter("actID");

        String data = seleteByActID(actID);

        response.setContentType("html/text");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        writer.write(data);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

//    public static void main(String[] args) {
////        System.out.println(seleteByActID("10001"));
////    }
}
