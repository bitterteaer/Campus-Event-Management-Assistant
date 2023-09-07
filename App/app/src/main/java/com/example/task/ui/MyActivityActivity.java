package com.example.task.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.task.bean.Lecture;
import com.example.task.bean.User;
import com.example.task.R;
import com.example.task.delegate.SimpleDelegateAdapter;
import com.example.task.http.Threads.MyActThread;
import com.example.task.http.Threads.SignUpBackNumByActIdThread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class MyActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("User");

        SimpleDelegateAdapter<Lecture> myActAdapter;

        SmartRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyActivityActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("username",user.getUsername());
        List<Lecture> lectureList = getdata(builder);
        myActAdapter= new SimpleDelegateAdapter<Lecture>(R.layout.adapter_news_xuilayout_list_item, new LinearLayoutHelper(),lectureList) {

            @Override
            protected void bindData(@NonNull RecyclerViewHolder holder, int position, Lecture lecture) {
                if (lecture != null) {
                    holder.image(R.id.iv_avatar, R.drawable.img_wyu);
                    holder.image(R.id.iv_praise, R.drawable.ic_z);
                    holder.image(R.id.iv_comment, R.drawable.ic_get);
                    holder.image(R.id.iv_time,R.drawable.ic_time);
                    holder.text(R.id.tv_user_name, lecture.getType().replace("\"", ""));
                    holder.text(R.id.tv_tag, lecture.getTheme().replace("\"", ""));
                    holder.text(R.id.tv_title, lecture.getActivityName().replace("\"", ""));
                    holder.text(R.id.tv_summary, lecture.getSummery().replace("\"", ""));
                    holder.text(R.id.tv_praise,  "总 " +lecture.getMenberTotal().replace("\"", ""));
                    holder.text(R.id.tv_comment, "已报名 " + lecture.getHavaSignUp());
                    holder.text(R.id.tv_read, lecture.getInitDate().substring(6,11));
                    holder.click(R.id.card_view, new View.OnClickListener() {
                        @SingleClick
                        @Override
                        public void onClick(View v) {
                            Bundle bundle1 = new Bundle();
                            Intent intent = new Intent(MyActivityActivity.this, EvaluateActivity.class);
                            bundle1.putSerializable("Lecture", lecture);
                            bundle1.putSerializable("User", user);
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        recyclerView.setAdapter(myActAdapter);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> refreshLayout1.getLayout().postDelayed(() -> {
            List<Lecture> lectureList1 = getdata(builder);
            myActAdapter.refresh(lectureList1);
            refreshLayout1.finishRefresh();
        }, 1000));
        //上拉加载
//        //上拉加载
//        refreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
//            mNewsAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
//            refreshLayout.finishLoadMore();
//        }, 1000));
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果

    }
    private List<Lecture> getdata(FormBody.Builder builder){
        MyActThread thread = new MyActThread(builder);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.value);
        JsonArray obj = new Gson().fromJson(thread.value, JsonArray.class);


        List<Lecture> lectureList = new ArrayList<>();
        for (JsonElement jsonElement : obj) {
            String startTime = jsonElement.getAsJsonObject().get("signUpTime").toString();
            String endTime = jsonElement.getAsJsonObject().get("deadlineForSign").toString();
            String activityId = jsonElement.getAsJsonObject().get("activityId").toString();//活动id
            String inChargeID = jsonElement.getAsJsonObject().get("inChargeID").toString();//负责人工号
            String inChargeNB = jsonElement.getAsJsonObject().get("inChargeNB").toString();//负责人手机号
            String menberTotal = jsonElement.getAsJsonObject().get("menberTotal").toString();//活动人数
//            String havaSignUp = jsonElement.getAsJsonObject().get("havaSignUp").toString();//活动已报名人数
            String summery = jsonElement.getAsJsonObject().get("summery").toString();//活动简介
            String imgSrc = jsonElement.getAsJsonObject().get("imgSrc").toString();//活动图片路径
            String activityName = jsonElement.getAsJsonObject().get("activityName").toString();//活动名
            String ativityPlace = jsonElement.getAsJsonObject().get("ativityPlace").toString();//活动地点
            String initDate = jsonElement.getAsJsonObject().get("initDate").toString();//具体日期 yyyy-mm-dd
            String ativityTime = jsonElement.getAsJsonObject().get("ativityTime").toString();//活动开始时间 hh-mm
            String deadlineForAtivity = jsonElement.getAsJsonObject().get("deadlineForAtivity").toString();//活动截至时间 hh-mm
            String remark = jsonElement.getAsJsonObject().get("remark").toString();//活动备注
            String type = jsonElement.getAsJsonObject().get("type").toString();//活动类型
            String theme = jsonElement.getAsJsonObject().get("theme").toString();//活动主题

            FormBody.Builder builder1 = new FormBody.Builder();
            builder1.add("actID", activityId.replace("\"",""));

            SignUpBackNumByActIdThread thread1 = new SignUpBackNumByActIdThread(builder1);
            thread1.start();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread1.value);
            JsonObject obj1 = new Gson().fromJson(thread1.value, JsonObject.class);
            String havaSignUp = obj1.get("Num").toString().replace("\"","");//活动已报名人数
            Lecture lecture = new Lecture(activityId, inChargeID, inChargeNB, menberTotal, havaSignUp, summery, imgSrc, activityName, ativityPlace, initDate, startTime, endTime, ativityTime, deadlineForAtivity, remark, type, theme);
            lectureList.add(lecture);
        }
        return lectureList;
    }
}