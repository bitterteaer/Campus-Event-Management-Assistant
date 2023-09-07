package com.example.task.ui;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import com.example.task.R;

import com.example.task.bean.Lecture;
import com.example.task.bean.User;
import com.example.task.delegate.SimpleDelegateAdapter;
import com.example.task.http.Threads.AllActThreads;

import com.example.task.http.Threads.SignUpBackNumByActIdThread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class AllActivity_new extends AppCompatActivity {

//    @BindView(R.id.recyclerView)

//    @BindView(R.id.refreshLayout)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //变量声明
        //变量声明
//        SwitchIconView mSwitchIconView;
//        final int[] i = {1};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        SimpleDelegateAdapter<Lecture> mNewsAdapter;
        SmartRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AllActivity_new.this);
        recyclerView.setLayoutManager(layoutManager);
        final Intent[] intent = {getIntent()};
        Bundle bundle = intent[0].getExtras();
        User user = (User) bundle.getSerializable("User");

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "data");
        List<Lecture> lectureList = getdata(builder, user);
        mNewsAdapter = new SimpleDelegateAdapter<Lecture>(R.layout.adapter_news_xuilayout_list_item, new LinearLayoutHelper(),lectureList) {


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
                            Intent intent = new Intent(AllActivity_new.this, SignUpActivity.class);
                            bundle1.putSerializable("Lecture", lecture);
                            bundle1.putSerializable("User", user);
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        recyclerView.setAdapter(mNewsAdapter);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> refreshLayout1.getLayout().postDelayed(() -> {
            List<Lecture> lectureList1 = getdata(builder, user);
            mNewsAdapter.refresh(lectureList1);
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

    protected boolean judgeTime(String startTime, String endTime){
        // 当前日期
        startTime = startTime.replace("\"", "");
        endTime = endTime.replace("\"", "");
        Date date2 = new Date();
        String[] arr1 = startTime.split("/");
        String[] arr3 = endTime.split("/");
        Date date1= new Date(parseInt(arr1[0])-1900,parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
        //报名截止日期
        Date date3=new Date(parseInt(arr3[0])-1900,parseInt(arr3[1])-1,parseInt(arr3[2]),23,0,0);
        return date1.getTime() <= date2.getTime() && date2.getTime() <= date3.getTime();
        // return false;
    }
    private List<Lecture> getdata(FormBody.Builder builder, User user){
        AllActThreads thread = new AllActThreads(builder);
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
            if (judgeTime(startTime, endTime)) {
                String activityId = jsonElement.getAsJsonObject().get("activityId").toString();//活动id
                String inChargeID = jsonElement.getAsJsonObject().get("inChargeID").toString();//负责人工号
                String inChargeNB = jsonElement.getAsJsonObject().get("inChargeNB").toString();//负责人手机号
                String menberTotal = jsonElement.getAsJsonObject().get("menberTotal").toString();//活动人数

                String summery = jsonElement.getAsJsonObject().get("summery").toString();//活动简介
                String imgSrc = jsonElement.getAsJsonObject().get("imgSrc").toString();//活动图片路径
                String activityName = jsonElement.getAsJsonObject().get("activityName").toString();//活动名
                String ativityPlace = jsonElement.getAsJsonObject().get("ativityPlace").toString();//活动地点
                String initDate = jsonElement.getAsJsonObject().get("initDate").toString();//具体日期 yyyy-mm-dd
//                String signUpTime = jsonElement.getAsJsonObject().get("signUpTime").toString();//报名开始时间 yyyy-mm-dd
//                String deadlineForSign = jsonElement.getAsJsonObject().get("deadlineForSign").toString();//报名截至时间 yyyy-mm-dd
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
        }
        return lectureList;
    }
}