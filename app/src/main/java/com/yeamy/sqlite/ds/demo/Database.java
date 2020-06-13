package com.yeamy.sqlite.ds.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.yeamy.sqlite.ds.DsReader;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "man_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table `man` (`name` varchar(20), `age` int, `money` varchar(10));");
        db.execSQL("insert into `man` VALUES('man1', 21, '100.00');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public String get() {
        String sql = "select * from `man`";

//        String name = DsReader.getString(getReadableDatabase(), sql, null);
//        System.out.println("name ===> " + name);

        try {
            Man man = DsReader.read(getReadableDatabase(), sql, Man.class);
            return man.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
