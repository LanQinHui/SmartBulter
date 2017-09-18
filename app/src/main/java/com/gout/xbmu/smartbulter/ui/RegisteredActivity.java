package com.gout.xbmu.smartbulter.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: RegisteredActivity
 * Created by LanQinHui on 2017/8/14.
 * 描述： 注册
 */

public class RegisteredActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;
    //性别
    private boolean isGender = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initView();

    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistered:
                //获取输入的值
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                //判断是否为空
                if(!TextUtils.isEmpty(name)
                        &!TextUtils.isEmpty(age)
                        &!TextUtils.isEmpty(pass)
                        &!TextUtils.isEmpty(password)
                        &!TextUtils.isEmpty(email)){
                    //判断输入的两次密码是否一致
                    if(pass.equals(password)) {
                        //判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                                if(checkedId == R.id.rb_boy){
                                    isGender = true;
                                }else if(checkedId == R.id.rb_gril) {
                                    isGender = false;
                                }
                            }
                        });

                        //判断简介是否为空
                        if (!TextUtils.isEmpty(desc)){
                            desc = "这位大侠来无影去无踪，什么都没有留下...";
                        }

                        //注册
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        //TODO 密码未加密！！！！！  后续请完善
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);
                        user.getDesc();

                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisteredActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(RegisteredActivity.this,"注册失败！"+toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //为空的话
                    Toast.makeText(this,"完善你的信息，谢谢！",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
