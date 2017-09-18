package com.gout.xbmu.smartbulter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: BaseActivity
 * Created by LanQinHui on 2017/8/13.
 * 描述： Activity的基类
 */

/**
 * 主要做的事情：
 * 1、统一的属性
 * 2、统一的接口
 * 3、统一的方法
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Dialog通知对话框
    private static BaseActivity instance = null;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        instance = this;
    }
    public static BaseActivity getInstance() {
        return instance;
    }

}
