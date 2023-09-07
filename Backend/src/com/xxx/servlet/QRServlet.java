package com.xxx.servlet;

import com.xxx.bean.Activity;
import com.xxx.util.提取活动二维码.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@WebServlet("/QRServlet")
public class QRServlet extends HttpServlet {

    public QRServlet() {
        super();
        //  Auto-generated constructor stub
    }

    public void init() throws ServletException{
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("QRServlet.post()");
        HttpSession session = request.getSession();
        Activity activity = (Activity) session.getAttribute("activity");
//        String qrstring = request.getParameter("qrstring");
        String qrstring = activity.getActivityName();
        String uploadDir = request.getSession().getServletContext().getRealPath("/");
        System.out.println("二维码的信息为："+activity.toString());
        if(qrstring != null){
            try {
                new QRCodeUtil().createQRCode(activity.getActivityName(), 300, uploadDir+qrstring+".jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("图片的地址为： "+uploadDir+qrstring+".jpg");
            File file = new File(uploadDir+qrstring+".jpg");//本地图片
            BufferedImage image= ImageIO.read(file);
            response.setContentType("image/jpg");
            ImageIO.write(image,"jpg",response.getOutputStream());
            response.getOutputStream().close();
//            String json = uploadDir+qrstring+".jpg";
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();

//            out.write(json);
//            out.flush();
//            out.close();
            System.out.println("success01");
        }else{
            System.out.println("error01");
        }

    }

    public void destroy()
    {
        // 什么也不做
    }
}
