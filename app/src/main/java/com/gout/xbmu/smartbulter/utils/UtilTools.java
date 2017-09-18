package com.gout.xbmu.smartbulter.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.utils
 * 文件名: UtilTools
 * Created by LanQinHui on 2017/8/13.
 * 描述： 工具统一类
 */

public class UtilTools {

    public static void setFont(Context mContent, TextView textView){
        //设置字体
        Typeface fontTyle = Typeface.createFromAsset(mContent.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(fontTyle);
    }
}
