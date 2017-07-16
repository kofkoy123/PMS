package com.lzr.pms.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzr.pms.App;
import com.lzr.pms.R;
import com.lzr.pms.Uitls.Constant;
import com.lzr.pms.bean.User;
import com.lzr.pms.db.DbManger;
import com.lzr.pms.db.MySqliteHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private TextInputLayout mUsernameLayout;
    private EditText mUsernameView;
    private TextInputLayout mPasswordLayout;
    private EditText mPasswordView;
    private Button mLoginView;
    private Button mRegisterView;
    private Object userInfo;
    private List<User> users;
    private MySqliteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initViews();
        getUserInfo();
        setListener();
    }

    private void setListener() {
        mLoginView.setOnClickListener(this);
        mRegisterView.setOnClickListener(this);
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
        getUserList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_view:
                login();
                break;
            case R.id.login_register_view:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, Constant.ACTIVITY_REGISTER);
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String userName = mUsernameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "账户名不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("account_id", user.getAccountDataFlag());
                startActivity(intent);
                finish();
                return;
            }
        }
        Toast.makeText(this, "账号或者密码不正确!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ACTIVITY_REGISTER) {
            if (resultCode == Activity.RESULT_OK) {
                String userName = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                mUsernameView.setText(userName);
                mPasswordView.setText(password);
                //数据已经增加了 重新获取最新数据集合
                getUserList();
            }
        }
    }

    public void getUserList() {
        SQLiteDatabase db = helper.getWritableDatabase();
        users = DbManger.getUserList(db, Constant.TABLE_NAME_USER);
        Log.e("lzr", "users.size==" + users.size());
        App.getIntance().saveUserList(users);
        db.close();
    }
}
