package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.http.Threads.FeedbackThread;

import okhttp3.FormBody;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Spinner spinner = findViewById(R.id.spinner1);
        Button button = findViewById(R.id.Button10);
        EditText editText = findViewById(R.id.EditText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String type = null;
//                try {
//                    //这里是为了改编码为utf-8
//                    type = new String(spinner.getSelectedItem().toString().getBytes("utf-8"),"GBK");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
                String type = spinner.getSelectedItem().toString();
//                Log.i("spinner",type);
                String data = editText.getText().toString();
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("type",type);
                builder.add("data",data);
                FeedbackThread thread = new FeedbackThread(builder);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.value);
                Toast.makeText(FeedbackActivity.this, "反馈完成", Toast.LENGTH_LONG).show();
                editText.setText("");
            }
        });
    }
}