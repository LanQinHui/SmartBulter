package com.gout.xbmu.smartbulter.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.adapter.CourierAdapter;
import com.gout.xbmu.smartbulter.entity.CourierData;
import com.gout.xbmu.smartbulter.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: CourierActivity
 * Created by LanQinHui on 2017/8/17.
 * 描述： TODO
 */

public class CourierActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_number;
    private Button btn_get_courier;
    private ListView mListView;

    private List<CourierData>mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initView();
    }

    //初始化View
    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        btn_get_courier = (Button) findViewById(R.id.btn_get_courier);
        mListView = (ListView) findViewById(R.id.mListView);
        btn_get_courier.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_courier:

                //5、listview适配器
                //6、实体类(item)
                //7、设置数据、显示效果
                //1、获取输入框的内容
                String name = et_name.getText().toString().trim();
                String number = et_number.getText().toString().trim();

                //拼接url
                String url = "http://v.juhe.cn/exp/index?key="+ StaticClass.COURIER_KET+"&com="+name+"&no="+number;

                //2、判断是否为空
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)){
                    //3、拿到数据请求json数据
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            //4、解析json
                            pasingJson(t);
                        }

                        @Override
                        public void onFailure(VolleyError error) {
                            super.onFailure(error);
                        }
                    });
                }else {
                    Toast.makeText(this,"请完善信息，谢谢！",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //解析数据
    private void pasingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject json = (JSONObject) jsonArray.get(i);
                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setZone(json.getString("zone"));
                data.setDatetime(json.getString("datetime"));
                mList.add(data);
            }
            //倒序处理
            Collections.reverse(mList);
            CourierAdapter adapter = new CourierAdapter(this,mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
