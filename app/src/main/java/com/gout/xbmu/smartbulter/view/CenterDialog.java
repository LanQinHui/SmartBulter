package com.gout.xbmu.smartbulter.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gout.xbmu.smartbulter.R;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.view
 * 文件名: CenterDialog
 * Created by LanQinHui on 2017/8/28.
 * 描述： 自定义Dialog
 */

public class CenterDialog extends Dialog implements View.OnClickListener {

    public Context context;      // 上下文
    public int layoutResID;      // 布局文件id
    public int[] listenedItems;  // 要监听的控件id

    public OnCenterItemClickListener listener;

    public CenterDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*4/5; // 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失
        //遍历控件id,添加点击事件
        for (int id : listenedItems) {
            findViewById(id).setOnClickListener((View.OnClickListener) this);
        }

    }


    public interface OnCenterItemClickListener {
        void OnCenterItemClick(CenterDialog dialog, View view);
    }
    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {

        dismiss();//注意：我在这里加了这句话，表示只要按任何一个控件的id,弹窗都会消失，不管是确定还是取消。
        listener.OnCenterItemClick(this, view);
    }
}
