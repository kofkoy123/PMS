package com.lzr.pms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lzr.pms.Uitls.Constant;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String userSql = "create table user (_id Integer primary key,account_id Integer,username varchar(10),password varchar(20))";
        sqLiteDatabase.execSQL(userSql);
        String empSql = "create table employee(_id Integer primary key,account_id Integer,name varchar(10)," +
                "gender Integer,age Integer,avatar BLOB,department varchar)";
        sqLiteDatabase.execSQL(empSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
