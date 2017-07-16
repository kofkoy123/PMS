package com.lzr.pms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class DbManger {

    private static MySqliteHelper helper;

    public static MySqliteHelper getIntance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据数据库查找到的Cursor对象转换为集合
     *
     * @param db        数据库对象
     * @param tableName 表名
     * @return 返回集合
     */
    public static List<User> getUserList(SQLiteDatabase db, String tableName) {
        List<User> users = new ArrayList<>();
        if (db != null) {
            if (tableName != null && !"".equals(tableName)) {
                Cursor cursor = db.query(Constant.TABLE_NAME_USER, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    long accountId = cursor.getLong(cursor.getColumnIndex(Constant.USER_ACCOUNT_ID));
                    String userName = cursor.getString(cursor.getColumnIndex(Constant.USER_NAME));
                    String password = cursor.getString(cursor.getColumnIndex(Constant.USER_PASSWORD));
                    User user = new User(userName, password, accountId);
                    users.add(user);
                }
            }
        }
        return users;
    }

    public static long insertUser(SQLiteDatabase db, String tableName, long accountId,
                                 String username, String pwd) {
        long result = 0;
        if (db != null) {
            if (tableName != null && !"".equals(tableName)) {
                ContentValues value = new ContentValues();
                value.put(Constant.USER_ACCOUNT_ID, System.currentTimeMillis());
                value.put(Constant.USER_NAME, username);
                value.put(Constant.USER_PASSWORD, pwd);
                result = db.insert(tableName, null, value);
            }
        }
        return result;
    }

}
