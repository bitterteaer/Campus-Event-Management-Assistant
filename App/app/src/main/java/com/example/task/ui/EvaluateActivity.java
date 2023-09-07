package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.bean.Lecture;
import com.example.task.bean.User;
import com.example.task.R;
import com.example.task.http.Threads.EvaluateThread;
import com.example.task.http.Threads.GetEvaluateThread;

import okhttp3.FormBody;

public class EvaluateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("User");
        Lecture lecture = (Lecture) bundle.getSerializable("Lecture");
        System.out.println("111111111111111111111111111111"+lecture);
        EditText editText = findViewById(R.id.EditText);
        Button button = findViewById(R.id.Button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("username", user.getUsername());
                builder.add("activityID", "10001");
                builder.add("data", editText.getText().toString());
                EvaluateThread thread = new EvaluateThread(builder);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.value);
                Toast.makeText(EvaluateActivity.this, "评价完成", Toast.LENGTH_LONG).show();
                editText.setText("");
            }
        });

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("actID", "10001");
        GetEvaluateThread thread = new GetEvaluateThread(builder);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.value);
        TextView textView = findViewById(R.id.TextView5);
        textView.setText(thread.value);
    }
}