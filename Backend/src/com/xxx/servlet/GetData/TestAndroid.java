package com.xxx.servlet.GetData;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xzy
 * @create 2022/5/18 15:44
 */
@WebServlet("/TestAndroid")
public class TestAndroid extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+", "+password);

        PrintWriter out = response.getWriter();
        out.write("success");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public static void main(String[] args) {
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();

        jsonObject.addProperty("name1","data1");
        jsonObject.addProperty("name2","data2");
        jsonObject2.addProperty("name3","data3");
        jsonObject2.addProperty("name4","data4");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);

        for (JsonElement jsonElement:jsonArray){
//            System.out.println(jsonElement);
            JsonElement data = jsonElement.getAsJsonObject().get("name1");
            if(data!=null){
                System.out.println(data.toString());
            }else {
                System.out.println("null");
            }
        }
    }
}
