package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task.bean.Teacher;
import com.example.task.ui.FeedbackActivity;
import com.example.task.ui.ListMyActivityPutActivity;
import com.example.task.ui.ListStudentInfoActivity;
import com.example.task.ui.NoticesActivity;
import com.example.task.ui.PutActivityActivity;
import com.example.task.ui.UserActivityTeacher;
import com.example.task.ui.moreActivity;
import com.example.task.util.SlidingMenu;

//import com.example.task.databinding.ActivityMainBinding;

public class MainActivityTeacher extends AppCompatActivity {

    private SlidingMenu mSlidingMenu;
    private AdapterViewFlipper flipper ;
    int[] imageIds = new int[]{
            R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4
    };
//    AutoLoginDao ald;
//
//    MainActivity(){
//        ald = new AutoLoginDao(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
        mSlidingMenu = new SlidingMenu(this, LayoutInflater
                .from(this).inflate(R.layout.activity_main_teacher, null), LayoutInflater
                .from(this).inflate(R.layout.activity_menu_teacher, null));
        setContentView(mSlidingMenu);//注意setContentView需要换为我们的SlidingMenu
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        //从Intent中取出Bundle
        Bundle bundle = intent.getExtras();
        //获取FunPerson里的数据数据
        assert bundle != null;
        Teacher user = (Teacher)bundle.getSerializable("Teacher");
        assert user != null;

        View toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.open();
            }
        });

        TextView textView = findViewById(R.id.TextView1);
        textView.setText(user.getUsername());

        //发布活动
        Button putActivity = findViewById(R.id.putActivity);
        putActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityTeacher.this, PutActivityActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //查看我的学生信息
        Button listStudentInfo = findViewById(R.id.listStudentInfo);
        listStudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityTeacher.this, ListStudentInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //查看我发布的活动
        Button listMyActivityPut = findViewById(R.id.listMyActivityPut);
        listMyActivityPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityTeacher.this, ListMyActivityPutActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
        flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }
            @Override
            public Object getItem(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //创建一个ImageView
                ImageView imageView = new ImageView(MainActivityTeacher.this);
                imageView.setImageResource(imageIds[position]);
                //设置ImageView的缩放类型
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //为ImageView设置布局参数
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
        flipper.startFlipping();


        Button button4 = findViewById(R.id.Button5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityTeacher.this, FeedbackActivity.class);
                startActivity(intent1);
            }
        });

        Button button1 = findViewById(R.id.Button6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityTeacher.this, NoticesActivity.class);
                startActivity(intent1);
            }
        });

        ImageView imageView = findViewById(R.id.ImageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityTeacher.this, UserActivityTeacher.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        Button button5 = findViewById(R.id.Button7);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityTeacher.this, moreActivity.class);
                startActivity(intent1);
            }
        });

//        Button loginOut = findViewById(R.id.loginOut);
//        loginOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AutoLoginDao(MainActivityTeacher.this).delete();
//                finish();
//            }
//        });
    }

}