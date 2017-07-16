package com.lzr.pms.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/16.
 */

public class User implements Serializable {

    private String userName;
    private String password;
    private long accountId;

    public User() {
    }

    public User(String userName, String password, long accountId) {
        this.userName = userName;
        this.password = password;
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountDataFlag() {
        return accountId;
    }

    public void setAccountDataFlag(long accountId) {
        this.accountId = accountId;
    }
}
