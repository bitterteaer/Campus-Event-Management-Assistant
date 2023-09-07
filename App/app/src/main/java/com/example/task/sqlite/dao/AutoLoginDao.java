package com.example.task.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.task.bean.User;
import com.example.task.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class AutoLoginDao {
    private DBHelper dbHelper;
    public AutoLoginDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void add(String flag){
        ContentValues contentValues = new ContentValues();
        contentValues.put("flag", flag);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("autologin", null, contentValues);
    }
    public void delete(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM autologin");
    }
    public String query(){
        String sql = "select idautologin as _id, flag from autologin";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        String flag = null;
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int flagColumn = cursor.getColumnIndex("flag");
            flag = cursor.getString(flagColumn);
        }

//        Log.i("flag",flag);
        return flag;
    }
}
