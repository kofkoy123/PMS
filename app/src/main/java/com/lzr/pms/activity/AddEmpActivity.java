package com.lzr.pms.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lzr.pms.R;
import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.bean.Employee;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

public class AddEmpActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBackView, mAvatarView;
    private EditText mNameView, mAgeView, mDepartmentView, mMobileView;
    private RadioGroup mGenderView;
    private Button mSubmitView;

    private long accountId;
    private Employee emp;
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp_layout);
        initViews();
        initData();
        setListener();
    }

    private void setListener() {
        mBackView.setOnClickListener(this);
        mSubmitView.setOnClickListener(this);
        mGenderView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.addemp_female_view) {
                    emp.setGender(1);
                    Log.e("lzr", "女");
                } else {
                    emp.setGender(0);
                    Log.e("lzr", "男");
                }
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        accountId = intent.getLongExtra("account_id", 0);
        emp = new Employee();
        helper = DbManger.getIntance(this);
    }

    private void initViews() {
        mBackView = (ImageView) findViewById(R.id.addemp_back_view);
        mAvatarView = (ImageView) findViewById(R.id.addemp_avatar_view);
        mNameView = (EditText) findViewById(R.id.addemp_name_view);
        mAgeView = (EditText) findViewById(R.id.addemp_age_view);
        mDepartmentView = (EditText) findViewById(R.id.addemp_department_view);
        mMobileView = (EditText) findViewById(R.id.addemp_mobile_view);
        mGenderView = (RadioGroup) findViewById(R.id.addemp_gender_view);
        mSubmitView = (Button) findViewById(R.id.addemp_submit);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addemp_back_view:
                finish();
                break;
            case R.id.addemp_submit:
                submitEmpInfo();
                break;
        }
    }

    /**
     * 添加员工资料
     */
    private void submitEmpInfo() {
        String name = mNameView.getText().toString().trim();
        String strAge = mAgeView.getText().toString().trim();
        String department = mDepartmentView.getText().toString().trim();
        String strMobile = mMobileView.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入员工姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strAge)) {
            strAge = "0";
        }
        int age = Integer.parseInt(strAge);
        if (TextUtils.isEmpty(strMobile)) {
            strMobile = "0";
        }
        int mobile = Integer.parseInt(strMobile);
        if (TextUtils.isEmpty(department)) {
            department = "未填写";
        }
        emp.setAge(age);
        emp.setDepartment(department);
        emp.setName(name);
        emp.setMobile(mobile);
        emp.setAccountId(accountId);
        SQLiteDatabase db = helper.getWritableDatabase();

        long result = DbManger.addEmpToSql(db, Constant.TABLE_NAME_EMPLOYEE, emp);
        db.close();
        if (result > 0) {
            Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败!", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
