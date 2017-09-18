package com.gout.xbmu.smartbulter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.adapter.ChatListAdapter;
import com.gout.xbmu.smartbulter.entity.ChatListData;
import com.gout.xbmu.smartbulter.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.fragment
 * 文件名: ButlerFragment
 * Created by LanQinHui on 2017/8/13.
 * 描述： TODO
 */

public class ButlerFragment extends Fragment implements View.OnClickListener {

    private ListView mChatListView;

    private List<ChatListData> mList = new ArrayList<>();
    private ChatListAdapter adapter;

    //输入框
    private EditText et_text;
    private Button btn_send;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler,null);
        findView(view);
        return view;
    }

    //初始化View
    private void findView(View view) {
        mChatListView = (ListView) view.findViewById(R.id.mChatListview);
        et_text = (EditText) view.findViewById(R.id.et_text);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        //设置适配器
        adapter = new ChatListAdapter(getActivity(), mList);
        mChatListView.setAdapter(adapter);

        addLeftItem("你好，我是小管家!");
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                /**
                 * 逻辑
                 * 1、获取输入框的内容
                 * 2、判断是否为空
                 * 3、长度不能大于30
                 * 4、清空当前的输入框
                 * 5、添加你的输入内容到right item
                 * 6、发送给机器人请求返回内容
                 * 7、拿到机器人的返回值之后添加到left item
                 */
                //1、获取输入框的内容
                String text = et_text.getText().toString();
                //2、判断是否为空
                if(!TextUtils.isEmpty(text)){
                    //3、长度不能大于30
                    if(text.length() > 30){
                        Toast.makeText(getActivity(), "输入长度超过限制！", Toast.LENGTH_SHORT).show();
                    }else {
                        //4、清空当前的输入框
                        et_text.setText("");
                        //5、添加你的输入内容到right item
                        addRightItem(text);
                        //6、发送给机器人请求返回内容
                        String url = "http://op.juhe.cn/robot/index?info="+text+"&key="+ StaticClass.CHAT_LIST_KEY;
                        RxVolley.get(url, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                //Toast.makeText(getActivity(), "Json" + t, Toast.LENGTH_SHORT).show();
                                //(json数据解析)
                                parsingJson(t);
                            }
                        });
                    }
                }else {
                    Toast.makeText(getActivity(), "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonresult = jsonObject.getJSONObject("result");
            //拿到返回值
            String text = jsonresult.getString("text");
            //7、拿到机器人的返回值之后添加到left item
            addLeftItem(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //添加左边文本
    private void addLeftItem(String text){
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_LEFT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }

    //添加右边文本
    private void addRightItem(String text){
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_RIGHT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }
}
