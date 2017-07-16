package com.lzr.pms;

import android.app.Application;

import com.lzr.pms.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class App extends Application {

    private static App app = null;
    private List<User> users;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

    }

    public static App getIntance() {
        return app;
    }

    public void saveUserList(List<User> lists) {
        this.users = lists;
    }

    public List<User> getUserList() {
        return this.users;
    }
}
