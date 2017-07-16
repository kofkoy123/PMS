package com.lzr.pms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzr.pms.R;
import com.lzr.pms.bean.Employee;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> employees;
    private LayoutInflater inflater;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_emp, null);
            holder = new ViewHolder();
            holder.mAvatar = view.findViewById(R.id.item_avatar);
            holder.mName = view.findViewById(R.id.item_name);
            holder.mGender = view.findViewById(R.id.item_gender);
            holder.mAge = view.findViewById(R.id.item_age);
            holder.mDepartment = view.findViewById(R.id.item_department);
            holder.mMobile = view.findViewById(R.id.item_mobile);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Employee emp = employees.get(i);
//        holder.mAvatar.setImageBitmap();
        holder.mName.setText("姓名:" + emp.getName());
        if (emp.getGender() == 0) {
            holder.mGender.setText("性别:" + "男");
        } else {
            holder.mGender.setText("性别:" + "女");
        }
        if (emp.getAge() == 0) {
            holder.mAge.setText("年龄:未填写");
        } else {
            holder.mAge.setText("年龄:" + emp.getAge());
        }

        holder.mDepartment.setText("部门:" + emp.getDepartment());
        if (emp.getMobile() == 0) {
            holder.mMobile.setText("手机:未填写");
        } else {
            holder.mMobile.setText("手机:" + emp.getMobile());
        }

        return view;
    }

    class ViewHolder {
        ImageView mAvatar;
        TextView mName, mGender, mAge, mDepartment, mMobile;
    }
}
