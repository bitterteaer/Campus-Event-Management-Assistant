package com.example.task.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.task.bean.Teacher;
import com.example.task.bean.User;
import com.example.task.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    private DBHelper dbHelper;

    public TeacherDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void add(Teacher teacher){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", teacher.getUsername());
        contentValues.put("password", teacher.getPassword());
        contentValues.put("full_name", teacher.getFullName());
        contentValues.put("in_charge_nb", teacher.getInChargeNB());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("teacher", null, contentValues);
    }
    public void delete(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM teacher");
    }
    public List<Teacher> query(){
        String sql = "select * from teacher";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Teacher> list = new ArrayList();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String full_name = cursor.getString(cursor.getColumnIndex("full_name"));
            String in_charge_nb = cursor.getString(cursor.getColumnIndex("in_charge_nb"));

            Teacher teacher = new Teacher();
            teacher.setUsername(username);
            teacher.setPassword(password);
            teacher.setFullName(full_name);
            teacher.setInChargeNB(in_charge_nb);
            list.add(teacher);
        }

        Log.i("query",list.toString());

        return list;
    }
}
