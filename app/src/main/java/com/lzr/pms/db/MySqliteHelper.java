package com.lzr.pms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lzr.pms.Uitls.Constant;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String userSql = "create table " + Constant.TABLE_NAME_USER + " (" + Constant.ID + " " +
                "Integer primary key," + Constant.USER_ACCOUNT_ID + " Integer," +
                "" + Constant.USER_NAME + " varchar(10)," + Constant.USER_PASSWORD + " varchar(20))";
        sqLiteDatabase.execSQL(userSql);
        String empSql = "create table " + Constant.TABLE_NAME_EMPLOYEE + "(" + Constant.EMP_ACCOUNT_ID + " " +
                "Integer primary key," + Constant.EMP_ACCOUNT_ID + " Integer," + Constant.EMP_NAME + " " +
                "varchar(10)," + Constant.EMP_GENDER + " Integer," + Constant.EMP_AGE + " Integer," +
                "" + Constant.EMP_AVATAR + " BLOB," + Constant.EMP_DEPARTMENT + " varchar)";
        sqLiteDatabase.execSQL(empSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.e("lzr", "onUpgrade11,oldVersion==" + oldVersion + ",newVersion" + newVersion);
        if (newVersion == Constant.ADD_ZIDUAN_VERSION) {
            String updataSql = "alter table " + Constant.TABLE_NAME_EMPLOYEE + " add " +
                    "" + Constant.EMP_MOBILE + " Integer";
            sqLiteDatabase.execSQL(updataSql);
            Log.e("lzr", "onUpgrade222");
        }
    }
}
