package com.lzr.pms.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lzr.pms.R;
import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.adapter.EmployeeAdapter;
import com.lzr.pms.bean.Employee;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ImageView mAddEmpView;
    private ListView mEmployeesView;

    //根据注册id找到数据他自己的数据
    private long accountId;
    private List<Employee> employees;
    private MySqliteHelper helper;
    private EmployeeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initViews();
        initData();
    }

    private void initData() {
        helper = DbManger.getIntance(this);
        Intent intent = getIntent();
        accountId = intent.getLongExtra("account_id", 0);
        getEmpInfoByAccountId();
    }

    private void getEmpInfoByAccountId() {
        if (accountId == 0) {
            Toast.makeText(this, "数据读取异常!", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        employees = DbManger.getEmpListByAccountId(db, Constant.TABLE_NAME_EMPLOYEE,
                accountId);
        db.close();

        setAdapter();
    }

    private void setAdapter() {
        adapter = new EmployeeAdapter(this, employees);
        mEmployeesView.setAdapter(adapter);
    }

    private void initViews() {
        mAddEmpView = (ImageView) findViewById(R.id.main_add_emp_view);
        mEmployeesView = (ListView) findViewById(R.id.main_emp_view);
    }
}
