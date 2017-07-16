package com.lzr.pms.Uitls;

/**
 * Created by Administrator on 2017/7/16.
 */

public class Constant {

    public static final String DATABASE_NAME = "PMS.db";
    public static final String TABLE_NAME_USER = "user"; //user表
    public static final String TABLE_NAME_EMPLOYEE = "employee"; //employee表
    public static final int DATABASE_VERSION = 7;//改动版本号会触发Sqlite更新事件
    public static final int ADD_ZIDUAN_VERSION = 7;//给数据库中表增加字段的版本升级号
    public static final String ID = "_id";
    //User表
    public static final String USER_ACCOUNT_ID ="account_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "password";
    //employee表
    public static final String EMP_ACCOUNT_ID = "account_id";
    public static final String EMP_NAME = "name";
    public static final String EMP_GENDER = "gender";
    public static final String EMP_AGE = "age";
    public static final String EMP_AVATAR = "avatar";
    public static final String EMP_DEPARTMENT = "department";
    public static final String EMP_MOBILE = "mobile";

    public static final int ACTIVITY_REGISTER = 1;
    public static final int ACTIVITY_ADD_EMP = 2;
    public static final int ACITVITY_UPDATA_DEL = 3;

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;
}
