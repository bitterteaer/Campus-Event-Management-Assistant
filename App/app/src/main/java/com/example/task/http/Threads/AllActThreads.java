package com.example.task.http.Threads;

import android.util.Log;

import com.example.task.http.HttpPost;
import com.example.task.http.GetIp;

import java.io.IOException;

import okhttp3.FormBody;

public class AllActThreads extends Thread{
    private FormBody.Builder builder = null;
    public String value = null;
    public AllActThreads(FormBody.Builder builder){
        this.builder = builder;
    }
    @Override
    public void run() {
        String result = null;
        try {
            result = new HttpPost().post(GetIp.getIp()+"/GetActivity", builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("result",result);
        value = result;
    }

//    public List getRetuenData(){
//        List list = new ArrayList();
//        if(value != null){
//            Gson gson = new Gson();
//            JsonArray obj = gson.fromJson(value, JsonArray.class);
//
//            for(JsonElement jsonElement:obj){
//                Activity data = gson.fromJson(jsonElement.toString(), Activity.class);
//                list.add(data);
//            }
//        }
//
//        return list;
//    }
}
