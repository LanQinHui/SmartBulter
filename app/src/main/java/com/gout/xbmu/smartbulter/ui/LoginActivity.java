package com.gout.xbmu.smartbulter.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.MainActivity;
import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.MyUser;
import com.gout.xbmu.smartbulter.utils.ShareUtils;
import com.gout.xbmu.smartbulter.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: LoginActivity
 * Created by LanQinHui on 2017/8/14.
 * 描述： TODO
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_registered;
    private EditText et_name_login;
    private EditText et_password_login;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;
    private Dialog dialog;

    private int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_login);

        initView();


    }

    private void initView() {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        et_name_login = (EditText) findViewById(R.id.et_name_login);
        et_password_login = (EditText) findViewById(R.id.et_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forget = (TextView) findViewById(R.id.tv_forget);

        //初始化dialog
        dialog = new CustomDialog(this,100,100,R.layout.dialog_loding,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

        tv_forget.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btn_registered.setOnClickListener(this);

        //设置选中的
        boolean isCheck = ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isCheck);
        if(isCheck){
            //设置密码
            et_name_login.setText(ShareUtils.getString(this,"name",""));
            et_password_login.setText(ShareUtils.getString(this,"password",""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //注册页面
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            //忘记、修改密码页面
            case R.id.tv_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            //登录页面
            case R.id.btn_login:
                //1、获取输入框的值
                String name = et_name_login.getText().toString().trim();
                String password = et_password_login.getText().toString().trim();
                //2、判断是否为空
                if(!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)){
                    //dialog.show();
                    showmydialog();
                    //3、进行登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //dialog.dismiss();
                            if(e==null){
                                //判断用户验证邮箱
                                if(user.getEmailVerified()){
                                    //跳转主页面

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    Toast.makeText(LoginActivity.this,"亲爱的 "+user.getUsername()+" ，欢迎你！",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(LoginActivity.this,"请验证邮箱，谢谢！",Toast.LENGTH_SHORT).show();
                                }
                                //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                                //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                            }else{
//                                loge(e);
                                Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入的账户或密码密码不能为空，请重新输入！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());  //可以写入工具类ShareUtils，但是为了更直观，就不写了、、、

        //是否记住密码
        if(keep_password.isChecked()){
            //记住用户名和密码
            ShareUtils.putString(this,"name",et_name_login.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password_login.getText().toString().trim());
        }else {
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }

    //登陆进度条
    protected void showmydialog(){
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在登陆...");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(5000 * 7, 5000) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i){
                    case 0:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                }
            }

            public void onFinish() {
                i = -1;
                pDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }.start();
    }
}
