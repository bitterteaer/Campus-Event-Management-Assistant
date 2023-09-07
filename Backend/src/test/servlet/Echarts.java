package test.servlet;

import com.google.gson.Gson;
import test.bean.EchartData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @create 2021/10/29 23:48
 */
@WebServlet("/Echarts")
public class Echarts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EchartData echartData = new EchartData();
        echartData.setData(new int[]{1, 2, 3, 4, 5, 6});
        EchartData echartData2 = new EchartData();
        echartData2.setData(new int[]{1, 2, 3, 4, 5, 6});
        EchartData echartData3 = new EchartData();
        echartData3.setData(new int[]{1, 2, 3, 4, 5, 6});
        EchartData echartData4 = new EchartData();
        echartData4.setData(new int[]{1, 2, 3, 4, 5, 6});

        List list = new ArrayList();
        list.add(echartData);
        list.add(echartData2);
        list.add(echartData3);
        list.add(echartData4);

        String json = new Gson().toJson(list);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        out.write(json);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
