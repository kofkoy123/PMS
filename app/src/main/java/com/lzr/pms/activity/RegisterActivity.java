package com.lzr.pms.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzr.pms.App;
import com.lzr.pms.R;
import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.bean.User;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBackView;
    private TextInputLayout mUsernameLayout;
    private EditText mUsernameView;
    private TextInputLayout mPasswordLayout;
    private EditText mPasswordView;
    private TextInputLayout mPwdcomfirmLayout;
    private EditText mPwdcomfirmView;
    private Button mRegisterView;
    private List<User> users;
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        initViews();
        initData();
        setListener();

    }

    private void initData() {
        users = App.getIntance().getUserList();
        helper = DbManger.getIntance(this);
    }

    private void setListener() {
        mBackView.setOnClickListener(this);
        mRegisterView.setOnClickListener(this);
    }

    private void initViews() {
        mBackView = (ImageView) findViewById(R.id.register_back_view);
        mUsernameLayout = (TextInputLayout) findViewById(R.id.register_username_layout);
        mUsernameView = (EditText) findViewById(R.id.register_username_view);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.register_password_layout);
        mPasswordView = (EditText) findViewById(R.id.register_password_view);
        mPwdcomfirmLayout = (TextInputLayout) findViewById(R.id.register_password_comfirm_layout);
        mPwdcomfirmView = (EditText) findViewById(R.id.register_password_comfirm_view);
        mRegisterView = (Button) findViewById(R.id.register_register_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back_view:
                finish();
                break;
            case R.id.register_register_view:
                registerUser();
                break;
        }
    }

    /**
     * 注册
     */
    private void registerUser() {
        String userName = mUsernameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        String pwdcomfirm = mPwdcomfirmView.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "账户名不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwdcomfirm)) {
            Toast.makeText(this, "请再输入密码!", Toast.LENGTH_LONG).show();
            return;
        }
        for (User user : users) {
            if (userName.equals(user.getUserName())) {
                Toast.makeText(this, "注册失败!账户名已被使用", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (!password.equals(pwdcomfirm)) {
            Toast.makeText(this, "注册失败!两次输入密码不一致", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        long accountId = System.currentTimeMillis();//利用注册时间作为一个标记
        long result = DbManger.insertUser(db, Constant.TABLE_NAME_USER, accountId, userName, password);
        if (result > 0) {
            Toast.makeText(this, "注册成功!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("username", userName);
            intent.putExtra("password", password);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "注册失败!", Toast.LENGTH_LONG).show();
        }
    }
}
