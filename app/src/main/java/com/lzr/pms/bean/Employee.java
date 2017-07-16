package com.lzr.pms.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/16.
 */

public class Employee implements Serializable {

    private long accountId;//标明不同用户的员工登录资料,避免所有用户登录获取的信息是一样
    private String name;
    private int gender;//0男 1女
    private int age;
    private Bitmap avatar;//员工头像
    private String department;

    public Employee() {
    }

    public Employee(long accountId, String name, int gender, int age, Bitmap avatar, String department) {
        this.accountId = accountId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.avatar = avatar;
        this.department = department;
    }

    public long getId() {
        return accountId;
    }

    public void setId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
