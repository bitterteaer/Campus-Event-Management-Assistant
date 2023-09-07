package com.example.task.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.task.R;

public class UserAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;
    public UserAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.activity_user_item, null);
        setChildView(view, cursor);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        setChildView(view, cursor);
    }

    @SuppressLint("Range")
    public void setChildView(View view, Cursor cursor){

        TextView username = view.findViewById(R.id.username);
        TextView password = view.findViewById(R.id.password);
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        TextView telephone = view.findViewById(R.id.telephone);
        TextView birthday = view.findViewById(R.id.birthday);
        TextView sex = view.findViewById(R.id.sex);
        TextView initdate = view.findViewById(R.id.initdate);
        TextView classRoom = view.findViewById(R.id.classRoom);
        TextView faculty = view.findViewById(R.id.faculty);
        TextView imgpath = view.findViewById(R.id.imgpath);

        username.setText(cursor.getString(cursor.getColumnIndex("username")));
        password.setText(cursor.getString(cursor.getColumnIndex("password")));
        name.setText(cursor.getString(cursor.getColumnIndex("name")));
        email.setText(cursor.getString(cursor.getColumnIndex("email")));
        telephone.setText(cursor.getString(cursor.getColumnIndex("telephone")));
        birthday.setText(cursor.getString(cursor.getColumnIndex("birthday")));
        sex.setText(cursor.getString(cursor.getColumnIndex("sex")));
        initdate.setText(cursor.getString(cursor.getColumnIndex("initdate")));
        classRoom.setText(cursor.getString(cursor.getColumnIndex("classRoom")));
        faculty.setText(cursor.getString(cursor.getColumnIndex("faculty")));
        imgpath.setText(cursor.getString(cursor.getColumnIndex("imgpath")));
    }
}
