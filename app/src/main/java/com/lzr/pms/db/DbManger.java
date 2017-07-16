package com.lzr.pms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.bean.Employee;
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

    /**
     * 注册账户
     *
     * @param db        数据库对象
     * @param tableName 表名
     * @param accountId 账户id
     * @param username  账户名
     * @param pwd       账户密码
     * @return 返回结果 >0 表示成功
     */
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

    /**
     * 根据账户唯一的accountid 查找相应的员工数据
     *
     * @param db                数据库对象
     * @param tableNameEmployee 表名
     * @param accountId         账户唯一的accountid
     * @return 返回员工资料集合
     */
    public static List<Employee> getEmpListByAccountId(SQLiteDatabase db, String tableNameEmployee,
                                                       long accountId) {
        List<Employee> employees = new ArrayList<>();
        if (db != null) {
            if (tableNameEmployee != null && !"".equals(tableNameEmployee)) {
                Cursor cursor = db.query(tableNameEmployee, null, Constant.EMP_ACCOUNT_ID + "=?",
                        new String[]{accountId + ""}, null, null, null);
                while (cursor.moveToNext()) {
                    long empAccountId = cursor.getLong(cursor.getColumnIndex(Constant.EMP_ACCOUNT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(Constant.EMP_NAME));
                    int gender = cursor.getInt(cursor.getColumnIndex(Constant.EMP_GENDER));
                    int age = cursor.getInt(cursor.getColumnIndex(Constant.EMP_AGE));
                    byte[] avatar = cursor.getBlob(cursor.getColumnIndex(Constant.EMP_AVATAR));
                    String department = cursor.getString(cursor.getColumnIndex(Constant.EMP_DEPARTMENT));
                    int mobile = cursor.getInt(cursor.getColumnIndex(Constant.EMP_MOBILE));
                    Employee emp = new Employee(empAccountId, name, gender, age, avatar, department,mobile);
                    employees.add(emp);
                }
            }
        }
        return employees;
    }

    /**
     * 添加员工信息
     * @param db 数据库对象
     * @param tableNameEmployee 表名
     * @param emp 员工信息对象
     * @return 返回0 失败 大于0成功
     */
    public static long addEmpToSql(SQLiteDatabase db, String tableNameEmployee, Employee emp) {
        long result = 0;
        if (db != null){
            if (tableNameEmployee != null && !"".equals(tableNameEmployee)) {
                ContentValues values = new ContentValues();
                values.put(Constant.EMP_ACCOUNT_ID,emp.getAccountId());
                values.put(Constant.EMP_NAME,emp.getName());
                values.put(Constant.EMP_GENDER,emp.getGender());
                values.put(Constant.EMP_AGE,emp.getAge());
                values.put(Constant.EMP_DEPARTMENT,emp.getDepartment());
                values.put(Constant.EMP_MOBILE,emp.getMobile());
                //添加到数据库
                result = db.insert(tableNameEmployee,null,values);
            }
        }

        return result;
    }
}
