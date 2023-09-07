package com.example.task.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.task.bean.User;
import com.example.task.sqlite.DBHelper;

public class UserDao {
    private DBHelper dbHelper;

    public UserDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void add(User u){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", u.getUsername());
        contentValues.put("password", u.getPassword());
        contentValues.put("name", u.getName());
        contentValues.put("email", u.getEmail());
        contentValues.put("telephone", u.getTelephone());
        contentValues.put("birthday", u.getBirthday());
        contentValues.put("sex", u.getSex());
        contentValues.put("initdate", u.getInitdate());
        contentValues.put("classRoom", u.getClassRoom());
        contentValues.put("faculty", u.getFaculty());
        contentValues.put("imgpath", u.getImgpath());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("user", null, contentValues);
    }
    public void delete(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM user");
    }
    public Cursor query(){
        String sql = "select iduser as _id, username, password, name, email, telephone, birthday,sex,initdate,classRoom,faculty,imgpath from user";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

//        List<User> list = new ArrayList();
//        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
//            int nameColumn = cursor.getColumnIndex(User.name_User);
//            int phoneColumn = cursor.getColumnIndex(User.telephone_User);
//            int emailColumn = cursor.getColumnIndex(User.email_User);
//            String name = cursor.getString(nameColumn);
//            String telephone = cursor.getString(phoneColumn);
//            String email = cursor.getString(emailColumn);
//
//            User user = new User();
//            user.setUsername(name);
//            user.setTelephone(telephone);
//            user.setEmail(email);
//            list.add(user);
//        }
//
//        Log.i("query",list.toString());

        return cursor;
    }
}
