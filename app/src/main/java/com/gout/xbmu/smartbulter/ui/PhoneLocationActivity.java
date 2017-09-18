package com.gout.xbmu.smartbulter.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gout.xbmu.smartbulter.R;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: PhoneLocationActivity
 * Created by LanQinHui on 2017/8/17.
 * 描述： 归属地查询
 */

public class PhoneLocationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputPhone;
    private Button btn_search;
    private TextView result_phone,result_provice,result_type,result_carrier;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_location);
        init();
    }

    private void init() {
        inputPhone = (EditText) findViewById(R.id.input_phone);
        btn_search = (Button) findViewById(R.id.btn_login);
        result_phone = (TextView) findViewById(R.id.result_phone);
        result_provice = (TextView) findViewById(R.id.result_provice);
        result_type = (TextView) findViewById(R.id.result_type);
        result_carrier = (TextView) findViewById(R.id.result_carrier);

        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
