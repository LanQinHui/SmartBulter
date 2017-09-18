package com.gout.xbmu.smartbulter.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: ForgetPasswordActivity
 * Created by LanQinHui on 2017/8/14.
 * 描述： TODO
 */

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_email_forget;
    private Button btn_forget_password;
    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;
    private Button btn_new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initView();
    }

    private void initView() {
        et_email_forget = (EditText) findViewById(R.id.et_email_forget);
        btn_forget_password = (Button) findViewById(R.id.btn_forget_password);
        et_now = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        btn_new_password = (Button) findViewById(R.id.btn_new_password);
        btn_new_password.setOnClickListener(this);
        btn_forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forget_password:
                //1、拿到输入框的内容判断是否为空
                String email = et_email_forget.getText().toString().trim();
                //2、判断是否为空
                if(!TextUtils.isEmpty(email)){
                    //3、TODO还可以优化的
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(ForgetPasswordActivity.this,"重置密码请求成功，请到你的邮箱进行密码重置操作",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(ForgetPasswordActivity.this,"重置密码失败！"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入的邮箱不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_new_password:
                //1、获取输入框的值
                String now = et_now.getText().toString().trim();
                String news = et_new.getText().toString().trim();
                String new_password = et_new_password.getText().toString().trim();
                //2、判断是否为空
                if(!TextUtils.isEmpty(now)&!TextUtils.isEmpty(news)&!TextUtils.isEmpty(new_password)){
                    //3、判断新密码是否一致
                    if(news.equals(new_password)){
                        //4、
                        MyUser.updateCurrentUserPassword(now, new_password, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e == null){
                                    Toast.makeText(ForgetPasswordActivity.this,"更改密码成功，可以使用新密码登录了！",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ForgetPasswordActivity.this,"更改密码失败！",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
