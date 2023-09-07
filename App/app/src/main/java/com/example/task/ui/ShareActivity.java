package com.example.task.ui;

import static com.example.task.util.WeChatShareUtils.weChatShareUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.bean.Lecture;
import com.example.task.bean.User;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import wxapi.AndroidShare;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_wechat);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("User");
        Lecture lecture = (Lecture) bundle.getSerializable("Lecture");
        String shareData = user.getName()+"给你分享了一个来自活动小助手的校内活动："+lecture.toString();

        View bottom_share_wechat = findViewById(R.id.bottom_share_wechat);
        bottom_share_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidShare androidShare = new AndroidShare(ShareActivity.this);
                androidShare.shareWeChatFriend("hello~", shareData, AndroidShare.TEXT, null);            }
        });
        View bottom_share_qq = findViewById(R.id.bottom_share_qq);
        bottom_share_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidShare androidShare = new AndroidShare(ShareActivity.this);
                androidShare.shareQQFriend("hello~", shareData, AndroidShare.TEXT, null);
            }
        });


    }

    //share demo
    public void shareQQ(View view) {
        AndroidShare androidShare = new AndroidShare(this);
        androidShare.shareQQFriend("这是标题", "这是内容", AndroidShare.TEXT, null);
    }
    public void shareWechat(View view) {
        AndroidShare androidShare = new AndroidShare(this);
        androidShare.shareWeChatFriend("这是标题", "这是内容", AndroidShare.TEXT, null);
    }

}