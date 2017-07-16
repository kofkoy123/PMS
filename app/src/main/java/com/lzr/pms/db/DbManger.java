package com.lzr.pms.db;

import android.content.Context;

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

}
