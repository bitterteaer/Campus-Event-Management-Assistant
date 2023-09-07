package com.example.task.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task.R;
import com.example.task.adapter.UserAdapter;
import com.example.task.bean.Teacher;
import com.example.task.bean.User;
import com.example.task.sqlite.dao.TeacherDao;
import com.example.task.sqlite.dao.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActivityTeacher extends AppCompatActivity {

    TeacherDao td;
    ListView list;

    public UserActivityTeacher() {
        td = new TeacherDao(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        list = findViewById(R.id.ListView1);

        Button button = findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                td.delete();
                list.setAdapter(null);
            }
        });
//        TextView textView = findViewById(R.id.TextView4);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        Teacher teacher = (Teacher) bundle.getSerializable("Teacher");

        //请求后端用户数据
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("username",user.getUsername());
//        UserThread thread = new UserThread(builder);
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(thread.value);
//        textView.setText(thread.value);
        //数据库操作
//        User obj = new Gson().fromJson(thread.value, User.class);
//        System.out.println(obj);
//        ud.add(obj);

        List<Teacher> cursor = td.query();
        Log.i("cursor",cursor.toString());
        List listmap = new ArrayList();
        for (Teacher teacher:cursor) {
            Map<String,Object> map =new HashMap<>();
            map.put("username",teacher.getUsername());
            map.put("password",teacher.getPassword());
            map.put("full_name",teacher.getFullName());
            map.put("in_charge_nb",teacher.getInChargeNB());

            listmap.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listmap,R.layout.activity_user_item_teacher,
                new String[]{"username","password","full_name","in_charge_nb"},
                new int[]{R.id.username,R.id.password,R.id.fullname,R.id.inchargenb});
        list.setAdapter(adapter);

//        for(JsonElement jsonElement:obj){
//            try{
//                String iduser = jsonElement.getAsJsonObject().get("iduser").toString();
//                String username = jsonElement.getAsJsonObject().get("username").toString();
//                String password = jsonElement.getAsJsonObject().get("password").toString();
//                String name = jsonElement.getAsJsonObject().get("name").toString();
//                String email = jsonElement.getAsJsonObject().get("email").toString();
//                String telephone = jsonElement.getAsJsonObject().get("telephone").toString();
//                String birthday = jsonElement.getAsJsonObject().get("birthday").toString();
//                String sex = jsonElement.getAsJsonObject().get("sex").toString();
//                String initdate = jsonElement.getAsJsonObject().get("initdate").toString();
//                String classRoom = jsonElement.getAsJsonObject().get("classRoom").toString();
//                String faculty = jsonElement.getAsJsonObject().get("faculty").toString();
//                String imgpath = jsonElement.getAsJsonObject().get("imgpath").toString();
//
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }

    }
}