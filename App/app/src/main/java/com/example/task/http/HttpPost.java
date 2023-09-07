package com.example.task.http;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpPost {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String post(String url, FormBody.Builder builder) throws IOException {
//        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String httpPost(String url, FormBody.Builder builder){
        final String[] data = {""};
        new Thread(){
            @Override
            public void run() {
                try{
                    String result = post(url, builder);
                    data[0] = result;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

        return data[0];
    }
}
