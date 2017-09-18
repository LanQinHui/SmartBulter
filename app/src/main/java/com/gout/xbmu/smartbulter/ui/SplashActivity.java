package com.gout.xbmu.smartbulter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.utils.ShareUtils;
import com.gout.xbmu.smartbulter.utils.StaticClass;
import com.gout.xbmu.smartbulter.utils.UtilTools;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: SplashActivity
 * Created by LanQinHui on 2017/8/14.
 * 描述： 闪屏页
 */

public class SplashActivity extends AppCompatActivity{

    /**
     * 1、延时2000ms
     * 2、判断程序是否第一次运行
     * 3、自定义字体
     * 4、Activity全屏主题
     */
    
    private TextView tv_splash;

    //延时，子线程更新UI
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if(ifFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    finish();
                    break;
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        
    }

    //初始化布局
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = (TextView) findViewById(R.id.tv_splash);

        //设置字体
        UtilTools.setFont(this,tv_splash);
    }

    //判断程序是否第一次运行
    private boolean ifFirst() {
        Boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIORST,true);
        if (isFirst){
            //是第一次运行
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIORST,false);
            return true;
        }else {
            return false;
        }
    }

    //禁止返回键
    public void onBackPressed(){

    }

}
