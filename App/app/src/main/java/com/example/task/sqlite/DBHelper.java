package com.example.task.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task.sqlite.dao.AutoLoginDao;

public class DBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "school_activity.db";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE [user]("
                +"[iduser] INTEGER NOT NULL,"
                +"[username] TEXT,"
                +"[password] TEXT,"
                +"[name] TEXT,"
                +"[email] TEXT,"
                +"[telephone] TEXT,"
                +"[birthday] TEXT,"
                +"[sex] TEXT,"
                +"[initdate] TEXT,"
                +"[classRoom] TEXT,"
                +"[faculty] TEXT,"
                +"[imgpath] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity3_1] PRIMARY KEY([iduser]))";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE [autologin]("
                +"[idautologin] INTEGER NOT NULL,"
                +"[flag] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity33_1] PRIMARY KEY([idautologin]))";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE [teacher]("
                +"[idteacher] INTEGER NOT NULL,"
                +"[username] TEXT,"
                +"[password] TEXT,"
                +"[full_name] TEXT,"
                +"[in_charge_nb] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity333_1] PRIMARY KEY([idteacher]))";
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql01 = "drop table if exists [user]";
        String sql02 = "drop table if exists [autologin]";
        String sql03 = "drop table if exists [teacher]";
        db.execSQL(sql01);
        db.execSQL(sql02);
        db.execSQL(sql03);

        String sql = "CREATE TABLE [user]("
                +"[iduser] INTEGER NOT NULL,"
                +"[username] TEXT,"
                +"[password] TEXT,"
                +"[name] TEXT,"
                +"[email] TEXT,"
                +"[telephone] TEXT,"
                +"[birthday] TEXT,"
                +"[sex] TEXT,"
                +"[initdate] TEXT,"
                +"[classRoom] TEXT,"
                +"[faculty] TEXT,"
                +"[imgpath] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity3_1] PRIMARY KEY([iduser]))";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE [autologin]("
                +"[idautologin] INTEGER NOT NULL,"
                +"[flag] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity33_1] PRIMARY KEY([idautologin]))";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE [teacher]("
                +"[idteacher] INTEGER NOT NULL,"
                +"[username] TEXT,"
                +"[password] TEXT,"
                +"[full_name] TEXT,"
                +"[in_charge_nb] TEXT,"
                +"CONSTRAINT [sqlite_autoindex_t_school_activity333_1] PRIMARY KEY([idteacher]))";
        db.execSQL(sql3);
    }
}
