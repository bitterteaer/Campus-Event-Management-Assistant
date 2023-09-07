package com.xxx.servlet;

import com.xxx.util.提取活动二维码.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xzy
 * @create 2022/3/27 15:06
 */
@WebServlet("/GetQrImg")
public class GetQrImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qrstring =request.getParameter("qrstring");
        String uploadDir = request.getSession().getServletContext().getRealPath("/");
        System.out.println("二维码的信息为："+qrstring);
        if(qrstring != null){
            try {
                new QRCodeUtil().createQRCode(qrstring, 300, uploadDir+qrstring+".jpg");
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
            System.out.println("success");
        }else{
            System.out.println("error");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
