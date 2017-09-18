package com.gout.xbmu.smartbulter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.utils
 * 文件名: ShareUtils
 * Created by LanQinHui on 2017/8/14.
 * 描述： SharedPreferences封装
 */

public class ShareUtils {

//    private void test(Context mContent) {
//        SharedPreferences sp = mContent.getSharedPreferences("config",Context.MODE_PRIVATE);
//        sp.getString("key","未获取到");
//
//        SharedPreferences.Editor editor = sp.edit();
//
//        editor.putString("key","value");
//
//        editor.commit();
//    }

    public static final String NAME = "config";

    //键 值
    public static void putString(Context mContent,String key,String value){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    //键 默认值
    public static String getString(Context mContent,String key,String defValue){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    //键 值
    public static void putInt(Context mContent,String key,int value){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    //键 默认值
    public static int getInt(Context mContent,String key,int defValue){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }

    //键 值
    public static void putBoolean(Context mContent,String key,boolean value){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    //键 默认值
    public static boolean getBoolean(Context mContent,String key,boolean defValue){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    //删除单个
    public static void deleShare(Context mContent,String key){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除全部
    public static void deleAll(Context mContent){
        SharedPreferences sp = mContent.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
