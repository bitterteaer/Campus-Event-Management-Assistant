package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.http.Threads.ListMyActivityThread;
import com.example.task.http.Threads.ListStudentInfoThread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

public class ListMyActivityPutActivity extends AppCompatActivity {

    private List<Map<String,Object>> lists;
    private SimpleAdapter adapter;
    private ListView listView;

    private int imageViews = R.mipmap.activitypic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_avtivity);

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username","101");
        ListMyActivityThread thread = new ListMyActivityThread(builder);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.value);
//        TextView textView = findViewById(R.id.TextView1);
//        textView.setText(thread.value);

        JsonArray obj = new Gson().fromJson(thread.value, JsonArray.class);

        lists = new ArrayList<>();
        for(JsonElement jsonElement:obj){
            String activityId = jsonElement.getAsJsonObject().get("activityId").toString();//活动id
            String inChargeID = jsonElement.getAsJsonObject().get("inChargeID").toString();//负责人工号
            String inChargeNB = jsonElement.getAsJsonObject().get("inChargeNB").toString();//负责人手机号
            String menberTotal = jsonElement.getAsJsonObject().get("menberTotal").toString();//活动人数
            String havaSignUp = jsonElement.getAsJsonObject().get("havaSignUp").toString();//活动已报名人数
            String summery = jsonElement.getAsJsonObject().get("summery").toString();//活动简介
            String imgSrc = jsonElement.getAsJsonObject().get("imgSrc").toString();//活动图片路径
            String activityName = jsonElement.getAsJsonObject().get("activityName").toString();//活动名
            String ativityPlace = jsonElement.getAsJsonObject().get("ativityPlace").toString();//活动地点
            String initDate = jsonElement.getAsJsonObject().get("initDate").toString();//具体日期 yyyy-mm-dd
            String signUpTime = jsonElement.getAsJsonObject().get("signUpTime").toString();//报名开始时间 yyyy-mm-dd
            String deadlineForSign = jsonElement.getAsJsonObject().get("deadlineForSign").toString();//报名截至时间 yyyy-mm-dd
            String ativityTime = jsonElement.getAsJsonObject().get("ativityTime").toString();//活动开始时间 hh-mm
            String deadlineForAtivity = jsonElement.getAsJsonObject().get("deadlineForAtivity").toString();//活动截至时间 hh-mm
            String remark = jsonElement.getAsJsonObject().get("remark").toString();//活动备注
            String type = jsonElement.getAsJsonObject().get("type").toString();//活动类型
            String theme = jsonElement.getAsJsonObject().get("theme").toString();//活动主题

            Map<String,Object> map =new HashMap<>();
            map.put("image",imageViews);
            map.put("content",activityName);
            map.put("activityId",activityId);
            map.put("inChargeID",inChargeID);
            map.put("inChargeNB",inChargeNB);
            map.put("menberTotal",menberTotal);
            map.put("havaSignUp",havaSignUp);
            map.put("summery",summery);
            map.put("imgSrc",imgSrc);
            map.put("activityName",activityName);
            map.put("ativityPlace",ativityPlace);
            map.put("initDate",initDate);
            map.put("signUpTime",signUpTime);
            map.put("deadlineForSign",deadlineForSign);
            map.put("ativityTime",ativityTime);
            map.put("deadlineForAtivity",deadlineForAtivity);
            map.put("remark",remark);
            map.put("type",type);
            map.put("theme",theme);

            lists.add(map);
        }
        System.out.println(thread.value);

        listView = (ListView) findViewById(R.id.ListView1);
        adapter = new SimpleAdapter(this,lists,R.layout.activity_allactivity_item
                ,new String[]{"image","theme","content"}
                ,new int[]{R.id.image1,R.id.text1,R.id.text2});
        listView.setAdapter(adapter);
    }
}