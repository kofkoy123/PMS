package com.lzr.pms.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/16.
 */

public class Employee implements Serializable {
    private int id;
    private long accountId;//标明不同用户的员工登录资料,避免所有用户登录获取的信息是一样
    private String name;
    private int gender;//0男 1女
    private int age;
    private byte[] avatar;//员工头像
    private String department;
    private int mobile;

    public Employee() {
    }

    public Employee(int id, long accountId, String name, int gender, int age, byte[] avatar, String department, int mobile) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.avatar = avatar;
        this.department = department;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}
