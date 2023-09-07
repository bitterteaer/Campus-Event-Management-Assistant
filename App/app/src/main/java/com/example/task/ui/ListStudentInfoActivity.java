package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.http.Threads.ListStudentInfoThread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

public class ListStudentInfoActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List list1;

    private int imageViews = R.mipmap.img_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student_info);


        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username","101");
        ListStudentInfoThread thread = new ListStudentInfoThread(builder);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.value);

        JsonArray obj = new Gson().fromJson(thread.value, JsonArray.class);
        list1 = new ArrayList<>();
        for(JsonElement jsonElement:obj){
            String username = jsonElement.getAsJsonObject().get("username").toString();//学生id
            String password = jsonElement.getAsJsonObject().get("password").toString();//学生密码
            String name = jsonElement.getAsJsonObject().get("name").toString();//学生名字
            String email = jsonElement.getAsJsonObject().get("email").toString();//学生邮箱
            String telephone = jsonElement.getAsJsonObject().get("telephone").toString();//学生联系方式
            String birthday = jsonElement.getAsJsonObject().get("birthday").toString();//注册时间
            String sex = jsonElement.getAsJsonObject().get("sex").toString();//性别
            String initdate = jsonElement.getAsJsonObject().get("initdate").toString();
            String classRoom = jsonElement.getAsJsonObject().get("classRoom").toString();//学生班别
            String faculty = jsonElement.getAsJsonObject().get("faculty").toString();//学生院系
            String imgpath = jsonElement.getAsJsonObject().get("imgpath").toString();//头像
            String signupID = jsonElement.getAsJsonObject().get("signupID").toString();//报名id
            String activityID = jsonElement.getAsJsonObject().get("activityID").toString();//活动id
            String activityName = jsonElement.getAsJsonObject().get("activityName").toString();//活动名
            String time = jsonElement.getAsJsonObject().get("time").toString();//报名时间
            String qrStates = jsonElement.getAsJsonObject().get("qrStates").toString();//签到状态

            Map<String,Object> map =new HashMap<>();
            map.put("image",imageViews);
            map.put("username",username);
            map.put("password",password);
            map.put("name",name);
            map.put("email",email);
            map.put("telephone",telephone);
            map.put("birthday",birthday);
            map.put("sex",sex);
            map.put("initdate",initdate);
            map.put("classRoom",classRoom);
            map.put("faculty",faculty);
            map.put("imgpath",imgpath);
            map.put("signupID",signupID);
            map.put("activityID",activityID);
            map.put("activityName",activityName);
            map.put("time",time);
            map.put("qrStates",qrStates);

            list1.add(map);
        }

        listView = findViewById(R.id.ListView1);
        adapter = new SimpleAdapter(this,list1,R.layout.activity_allactivity_item
                ,new String[]{"image","name","classRoom"}
                ,new int[]{R.id.image1,R.id.text1,R.id.text2});
        listView.setAdapter(adapter);
    }
}