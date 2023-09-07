package com.xxx.servlet.SetData;

import com.xxx.bean.User;
import com.xxx.service.serviceUser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author xzy
 * @create 2022/3/30 21:11
 */
@WebServlet("/UpLoadImg")
public class UpLoadImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userID = request.getParameter("userID");
//        System.out.println(userID);

        //设置编码为utf-8
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            writer.write("用户未登录");
            writer.flush();
            writer.close();
        }else{
            FileItemFactory factory = new DiskFileItemFactory();

            // 文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);

            // 解析请求信息
            List items = null;
            try {
                items = upload.parseRequest(request);
            }
            catch (FileUploadException e) {
                e.printStackTrace();
            }

//        String path;
            // 对请求信息进行判断
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // 信息为普通的格式
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    request.setAttribute(fieldName, value);
                }
                // 信息为文件格式
                else {
                    Date now = new Date();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_hh_ss_mm");
                    String pubtime = sf.format(now);
                    String fileName = pubtime+item.getName();

//                String fileName = item.getName();//获得上传图片的名称
                    int index = fileName.lastIndexOf("\\");
                    fileName = fileName.substring(index + 1);
                    request.setAttribute("realFileName", fileName);
                    String basePath =  request.getSession().getServletContext().getRealPath("/images");

                    String path = basePath+fileName;
                    System.out.println(path);//打印当前位置

//                    User user = new User();
//                    user.setUsername(userID);
                    user.setImgpath(path);

                    File file = new File(path);
                    try {
                        item.write(file);

//                        HttpSession session = request.getSession();
//                        User u = (User) session.getAttribute("user");
                        serviceUser.SetImgPath(user);

                        response.setContentType("html/text");
                        response.setCharacterEncoding("utf-8");
                        PrintWriter out = response.getWriter();
                        out.write("success");
                        out.flush();
                        out.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


//        String last = StringUtils.substringAfterLast(fileItem.getName(), ".");
//        String fileName = UUID.randomUUID().toString() + "." + last;
//        fileItem.write(new File(req.getServletContext().getRealPath("upload"),fileName));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
