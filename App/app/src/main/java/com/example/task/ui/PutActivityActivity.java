package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.http.Threads.PutActivityThread;
import com.example.task.util.strToQrCode;
import com.google.zxing.WriterException;

import okhttp3.FormBody;


public class PutActivityActivity extends AppCompatActivity {

    private String activityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_activity);

        Button next = findViewById(R.id.next);
        Button seeQrCode = findViewById(R.id.seeQrCode);
        TextView textView1 = findViewById(R.id.TextView1);
        TextView textView2 = findViewById(R.id.TextView2);
        ImageView ImageViewQrcode = findViewById(R.id.ImageViewQrcode);
        LinearLayout linearLayout1 = findViewById(R.id.LinearLayout1);
        LinearLayout linearLayout2 = findViewById(R.id.LinearLayout2);

        textView2.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        seeQrCode.setVisibility(View.GONE);
        ImageViewQrcode.setVisibility(View.GONE);

        seeQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = new strToQrCode().CreateQRCode(activityName);
                    textView2.setVisibility(View.GONE);
                    ImageViewQrcode.setVisibility(View.VISIBLE);
                    ImageViewQrcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView1.getText().toString();
                if(text.equals("第二步")){
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    textView2.setVisibility(View.VISIBLE);
                    seeQrCode.setVisibility(View.VISIBLE);
                    textView1.setText("第三步");
                    textView1.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);

                    //数据上传后端
                    EditText editText = findViewById(R.id.inChargeID);
                    String inChargeID = editText.getText().toString();
                    editText = findViewById(R.id.inChargeNB);
                    String inChargeNB = editText.getText().toString();
                    editText = findViewById(R.id.menberTotal);
                    String menberTotal = editText.getText().toString();
                    editText = findViewById(R.id.ativityPlace);
                    String ativityPlace = editText.getText().toString();
                    editText = findViewById(R.id.activityName);
                    activityName = editText.getText().toString();
                    editText = findViewById(R.id.activityTheme);
                    String activityTheme = editText.getText().toString();
                    editText = findViewById(R.id.activityType);
                    String activityType = editText.getText().toString();
                    editText = findViewById(R.id.initDate);
                    String initDate = editText.getText().toString();
                    editText = findViewById(R.id.signUpTime);
                    String signUpTime = editText.getText().toString();
                    editText = findViewById(R.id.deadlineForSign);
                    String deadlineForSign = editText.getText().toString();
                    editText = findViewById(R.id.deadlineForAtivity);
                    String deadlineForAtivity = editText.getText().toString();
                    editText = findViewById(R.id.ativityTime);
                    String ativityTime = editText.getText().toString();
                    editText = findViewById(R.id.summery);
                    String summery = editText.getText().toString();

                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("inChargeID",inChargeID);
                    builder.add("inChargeNB",inChargeNB);
                    builder.add("menberTotal",menberTotal);
                    builder.add("ativityPlace",ativityPlace);
                    builder.add("activityTheme",activityTheme);
                    builder.add("activityType",activityType);
                    builder.add("initDate",initDate);
                    builder.add("signUpTime",signUpTime);
                    builder.add("deadlineForSign",deadlineForSign);
                    builder.add("deadlineForAtivity",deadlineForAtivity);
                    builder.add("ativityTime",ativityTime);
                    builder.add("summery",summery);
                    builder.add("activityName",activityName);

                    PutActivityThread thread = new PutActivityThread(builder);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(thread.value);

                }else if(text.equals("第三步")){
                }else {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    textView1.setText("第二步");
                }
            }
        });
    }
}