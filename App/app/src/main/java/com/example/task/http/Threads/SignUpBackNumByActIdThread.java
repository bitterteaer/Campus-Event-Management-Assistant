package com.example.task.http.Threads;

import android.util.Log;

import com.example.task.http.GetIp;
import com.example.task.http.HttpPost;

import java.io.IOException;

import okhttp3.FormBody;

public class SignUpBackNumByActIdThread extends Thread{
    private FormBody.Builder builder = null;
    public String value = null;
    public SignUpBackNumByActIdThread(FormBody.Builder builder){
        this.builder = builder;
    }
    @Override
    public void run() {
        String result = null;
        try {
            result = new HttpPost().post(GetIp.getIp()+"/SignUpNumByActID", builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("result",result);
        value = result;
    }
}
