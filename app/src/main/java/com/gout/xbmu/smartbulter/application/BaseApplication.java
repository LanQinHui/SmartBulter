package com.gout.xbmu.smartbulter.application;

import android.app.Application;

import com.gout.xbmu.smartbulter.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Created by LanQinHui on 2017/8/13.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.Bugly_APP_ID, false);
        //初始化Bomb
        //第一：默认初始化
        Bmob.initialize(this, StaticClass.BOMB_APP_ID);
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");
    }
}
