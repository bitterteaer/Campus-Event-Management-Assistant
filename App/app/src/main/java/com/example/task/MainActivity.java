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

import com.example.task.bean.User;
import com.example.task.sqlite.dao.AutoLoginDao;
import com.example.task.ui.AllActivity_new;
import com.example.task.ui.UserActivity;
import com.example.task.util.SlidingMenu;
import com.example.task.ui.moreActivity;
import com.example.task.ui.AllActivityActivity;
import com.example.task.ui.FeedbackActivity;
import com.example.task.ui.MyActivityActivity;
import com.example.task.ui.NoticesActivity;
import com.example.task.ui.QrCode.QrCodeActivity;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.task.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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
                .from(this).inflate(R.layout.activity_main, null), LayoutInflater
                .from(this).inflate(R.layout.activity_menu, null));
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
        User user = (User)bundle.getSerializable("User");
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

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity_new.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivityActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
//                startActivityForResult(intent,3);
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
                ImageView imageView = new ImageView(MainActivity.this);
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
                Intent intent1 = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(intent1);
            }
        });

        Button button1 = findViewById(R.id.Button6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, NoticesActivity.class);
                startActivity(intent1);
            }
        });

        ImageView imageView = findViewById(R.id.ImageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, UserActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        Button button5 = findViewById(R.id.Button7);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, moreActivity.class);
                startActivity(intent1);
            }
        });

        Button loginOut = findViewById(R.id.loginOut);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AutoLoginDao(MainActivity.this).delete();
                finish();
            }
        });
    }

}