package com.example.task.ui;

import static com.example.task.util.WeChatShareUtils.weChatShareUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.task.bean.Lecture;
import com.example.task.bean.User;
import com.example.task.R;
import com.example.task.http.Threads.AllActThreads;
import com.example.task.http.Threads.SignUpBackNumThread;
import com.example.task.http.Threads.SignUpThread;
import com.example.task.http.Threads.UnSignUpThread;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import okhttp3.FormBody;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("User");
        Lecture lecture = (Lecture) bundle.getSerializable("Lecture");
        System.out.println("123"+lecture);
//        assert user != null;

        Button button = findViewById(R.id.Button8);//报名
        Button button1 = findViewById(R.id.Button9);//取消报名
        Button share = findViewById(R.id.share);//share
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,ShareActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FormBody.Builder builder = new FormBody.Builder();
                builder.add("username", user.getUsername());
                builder.add("activityID","10003");

                SignUpBackNumThread thread = new SignUpBackNumThread(builder);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.value);
                Toast.makeText(SignUpActivity.this, "报名成功", Toast.LENGTH_LONG).show();
//                SignUpThread thread = new SignUpThread(builder);
//                thread.start();
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(thread.value);
//                Toast.makeText(SignUpActivity.this, "报名成功", Toast.LENGTH_LONG).show();
//                editText.setText("");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FormBody.Builder builder = new FormBody.Builder();
                builder.add("activityID","10001");
                builder.add("username", user.getUsername());
                UnSignUpThread thread = new UnSignUpThread(builder);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.value);
                Toast.makeText(SignUpActivity.this, "取消报名", Toast.LENGTH_LONG).show();
            }
        });
    }


}