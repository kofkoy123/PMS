package com.lzr.pms.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lzr.pms.R;
import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.adapter.EmployeeAdapter;
import com.lzr.pms.bean.Employee;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mAddEmpView;
    private ListView mEmployeesView;

    //根据注册id找到数据他自己的数据
    private long accountId;
    private List<Employee> employees = new ArrayList<>();
    private MySqliteHelper helper;
    private EmployeeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initViews();
        initData();
        setListener();
    }

    private void setListener() {
        mAddEmpView.setOnClickListener(this);
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
        List<Employee> results = DbManger.getEmpListByAccountId(db, Constant.TABLE_NAME_EMPLOYEE,
                accountId);
        employees.addAll(results);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_add_emp_view:
                Intent intent = new Intent(this, AddEmpActivity.class);
                intent.putExtra("account_id", accountId);
                startActivityForResult(intent, Constant.ACTIVITY_ADD_EMP);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ACTIVITY_ADD_EMP) {
            if (resultCode == Activity.RESULT_OK) {
                SQLiteDatabase db = helper.getWritableDatabase();
                List<Employee> results = DbManger.getEmpListByAccountId(db, Constant.TABLE_NAME_EMPLOYEE,
                        accountId);
                employees.clear();
                employees.addAll(results);
                db.close();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
