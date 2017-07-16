package com.lzr.pms.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lzr.pms.R;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout mUsernameLayout;
    private EditText mUsernameView;
    private TextInputLayout mPasswordLayout;
    private EditText mPasswordView;
    private Button mLoginView;
    private Button mRegisterView;
    private Object userInfo;

    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initViews();
        getUserInfo();

    }

    private void initViews() {
        mUsernameLayout = (TextInputLayout) findViewById(R.id.login_username_layout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.login_password_layout);
        mUsernameView = (EditText) findViewById(R.id.login_username_view);
        mPasswordView = (EditText) findViewById(R.id.login_password_view);
        mLoginView = (Button) findViewById(R.id.login_login_view);
        mRegisterView = (Button) findViewById(R.id.login_register_view);

    }

    /**
     * 创建或者打开数据库
     */
    public void getUserInfo() {
        helper = DbManger.getIntance(this);
        helper.getWritableDatabase();
    }
}
