package com.gout.xbmu.smartbulter.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.interfaces.DialogButtonListener;
import com.gout.xbmu.smartbulter.ui.BaseActivity;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.utils
 * 文件名: DialogUtil
 * Created by LanQinHui on 2017/8/15.
 * 描述： 对话通知框
 */

public class DialogUtil {
    private AlertDialog dlg;
    private ImageView ivIcon;
    private TextView tvText;
    private Button btnCancel,btnSure;

    private Context context;
    private int imgResId = 0;
    private String text;
    private DialogButtonListener listener;

    public void show(String text, final DialogButtonListener listener) {
        this.context = BaseActivity.getInstance();
        this.text = text;
        this.listener = listener;
        createDialog();
        setValue();
    }

    public void show( int imgResId, String text, final DialogButtonListener listener) {
        this.context = BaseActivity.getInstance();
        this.text = text;
        this.listener = listener;
        this.imgResId = imgResId;
        createDialog();
        setValue();
    }

    public void show() {
        this.context = context;
        this.text = text;
        this.listener = listener;
        createDialog();
        setValue();
    }

    public void show(Context context, int imgResId, String text, final DialogButtonListener listener) {
        this.context = context;
        this.text = text;
        this.listener = listener;
        this.imgResId = imgResId;
        createDialog();
        setValue();
    }

    //创建Dialog、初始化控件
    private void createDialog() {
        dlg = new AlertDialog.Builder(context).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.activity_dialog);
        window.setGravity(Gravity.CENTER);//居中
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//背景透明
        ivIcon = (ImageView) window.findViewById(R.id.ivIcon);
        tvText = (TextView) window.findViewById(R.id.tvText);
        btnCancel = (Button) window.findViewById(R.id.btnCancel);
        btnSure = (Button) window.findViewById(R.id.btnSure);
    }

    //设置控件值
    private void setValue() {
        if (imgResId != 0) {
            ivIcon.setImageResource(imgResId);
        } else {
            ivIcon.setVisibility(View.GONE);
        }
        tvText.setText(text);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
                dlg.dismiss();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sure();
                dlg.dismiss();
            }
        });
    }



}
