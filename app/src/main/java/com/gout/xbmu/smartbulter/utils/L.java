package com.gout.xbmu.smartbulter.utils;

import android.util.Log;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.utils
 * 文件名: L
 * Created by LanQinHui on 2017/8/14.
 * 描述： Log封装类
 */

public class L {

    //开关
    public static final boolean DEBUG = true; //默认开启
    //TAG
    public static final String TAG = "Smartbulter";
    //五个等级 D、I、W、E、F(不封装)
    public static void d(String text){
        if (DEBUG){
            Log.d(TAG,text);
        }

    }

    public static void i(String text){
        if (DEBUG){
            Log.i(TAG,text);
        }

    }
    public static void w(String text){
        if (DEBUG){
            Log.w(TAG,text);
        }

    }
    public static void e(String text){
        if (DEBUG){
            Log.e(TAG,text);
        }

    }

}
