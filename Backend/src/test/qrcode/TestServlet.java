package test.qrcode;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {

    public TestServlet() {
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
        System.out.println("进入post方法");
        String qrstring =request.getParameter("qrstring");
        String uploadDir = request.getSession().getServletContext().getRealPath("/");
        System.out.println(qrstring);
        if(qrstring != null){
            try {
                new QRCodeUtil().createQRCode(qrstring, 200, uploadDir+qrstring+".jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(uploadDir);
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
        }else{
            System.out.println("失败了");
        }
        System.out.println("是打发斯蒂芬");
    }

    public void destroy()
    {
        // 什么也不做
    }
}
