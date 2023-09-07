package com.example.task.http.Threads;

import android.util.Log;

import com.example.task.http.HttpPost;
import com.example.task.http.GetIp;

import java.io.IOException;

import okhttp3.FormBody;

public class NoticeThread extends Thread{

    private FormBody.Builder builder = null;
    public String value = null;
    public NoticeThread(FormBody.Builder builder){
        this.builder = builder;
    }
    @Override
    public void run() {
        String result = null;
        try {
            result = new HttpPost().post(GetIp.getIp()+"/AmentData", builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("result",result);
        value = result;
    }
}
