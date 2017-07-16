package com.lzr.pms.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBackView, mAvatarView;
    private EditText mNameView, mAgeView, mDepartmentView, mMobileView;
    private RadioGroup mGenderView;
    private Button mUpdataView, mDelView;

    private Employee emp;
    private MySqliteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_layout);
        initViews();
        initData();
        setListener();
    }

    private void setListener() {
        mBackView.setOnClickListener(this);
        mUpdataView.setOnClickListener(this);
        mAvatarView.setOnClickListener(this);
        mDelView.setOnClickListener(this);
        mGenderView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.modify_male_view:
                        emp.setGender(Constant.GENDER_MALE);
                        break;
                    case R.id.modify_female_view:
                        emp.setGender(Constant.GENDER_FEMALE);
                        break;
                }
            }
        });
    }

    private void initData() {
        helper = DbManger.getIntance(this);
        Intent intent = getIntent();
        emp = (Employee) intent.getSerializableExtra("emp");
        if (emp != null) {
            mNameView.setText(emp.getName());
            if (emp.getGender() == Constant.GENDER_MALE) {
                mGenderView.check(R.id.modify_male_view);
            } else {
                mGenderView.check(R.id.modify_female_view);
            }
            if (emp.getAge() == 0) {
                mAgeView.setText("");
            } else {
                mAgeView.setText(emp.getAge() + "");
            }
            if (emp.getDepartment().equals("") || emp.getDepartment().equals("未填写")) {
                mDepartmentView.setText("");
            } else {
                mDepartmentView.setText(emp.getDepartment());
            }
            if (emp.getMobile() == 0) {
                mMobileView.setText("");
            } else {
                mMobileView.setText(emp.getMobile() + "");
            }
            byte[] bmbyte = emp.getAvatar();
            if (bmbyte != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(emp.getAvatar(), 0, emp.getAvatar().length);
                mAvatarView.setImageBitmap(bitmap);
            }
        }
    }

    private void initViews() {
        mBackView = (ImageView) findViewById(R.id.modify_back_view);
        mAvatarView = (ImageView) findViewById(R.id.modify_avatar_view);
        mNameView = (EditText) findViewById(R.id.modify_name_view);
        mAgeView = (EditText) findViewById(R.id.modify_age_view);
        mDepartmentView = (EditText) findViewById(R.id.modify_department_view);
        mMobileView = (EditText) findViewById(R.id.modify_mobile_view);
        mGenderView = (RadioGroup) findViewById(R.id.modify_gender_view);
        mUpdataView = (Button) findViewById(R.id.modify_updata_view);
        mDelView = (Button) findViewById(R.id.modify_del_view);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify_back_view:
                finish();
                break;
            case R.id.modify_updata_view:
                updataEmp();
                break;
            case R.id.modify_del_view:
                delEmp();
                break;
            case R.id.modify_avatar_view:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Constant.ACITVITY_IMAGE);
                break;
        }
    }

    private void delEmp() {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = db.delete(Constant.TABLE_NAME_EMPLOYEE, Constant.ID + "=?", new String[]{emp.getId() + ""});
        db.close();
        if (result > 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void updataEmp() {
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
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.EMP_NAME, name);
        values.put(Constant.EMP_GENDER, emp.getGender());
        values.put(Constant.EMP_AGE, age);
        values.put(Constant.EMP_DEPARTMENT, department);
        values.put(Constant.EMP_MOBILE, mobile);
        values.put(Constant.EMP_AVATAR, emp.getAvatar());
        int result = db.update(Constant.TABLE_NAME_EMPLOYEE, values,
                Constant.ID + "=?", new String[]{emp.getId() + ""});
        db.close();
        if (result > 0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == Constant.ACITVITY_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath) {
        Log.e("lzr", "imaePath==" + imaePath);
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, os);
        emp.setAvatar(os.toByteArray());
        mAvatarView.setImageBitmap(bm);
    }
}
