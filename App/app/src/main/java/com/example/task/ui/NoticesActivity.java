package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.http.Threads.NoticeThread;

import okhttp3.FormBody;

public class NoticesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key","data");
        NoticeThread thread = new NoticeThread(builder);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.value);
        TextView textView = findViewById(R.id.TextView2);
        textView.setText(thread.value);
    }
}