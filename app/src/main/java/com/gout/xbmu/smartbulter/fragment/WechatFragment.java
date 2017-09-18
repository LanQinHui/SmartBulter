package com.gout.xbmu.smartbulter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.adapter.WeChatAdapter;
import com.gout.xbmu.smartbulter.entity.WeChatData;
import com.gout.xbmu.smartbulter.ui.WebViewActivity;
import com.gout.xbmu.smartbulter.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
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

public class WechatFragment extends Fragment {

    private ListView mListView;
    private List<WeChatData>mList = new ArrayList<>();

    //标题
    private List<String>mListTitle = new ArrayList<>();
    //地址
    private List<String>mListUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat,null);
        findView(view);
        return view;
    }

    //初始化view
    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);

        //解析接口 http://v.juhe.cn/weixin/query?key=您申请的KEY
        String url = "http://v.juhe.cn/weixin/query?key="+ StaticClass.WECHAT_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
            }
        });

        //点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                //intent两种方法传值
                intent.putExtra("title",mListTitle.get(position));
                intent.putExtra("url",mListUrl.get(position));
                //点击文章之后跳转打开文章
                startActivity(intent);
            }
        });
    }

    //解析json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonresult =  jsonObject.getJSONObject("result");
            JSONArray jsonList = jsonresult.getJSONArray("list");
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject json = (JSONObject) jsonList.get(i);
                WeChatData data = new WeChatData();
                //data.setTime(json.getInt("id"));

                String title = json.getString("title");
                String url = json.getString("url");

                data.setTitle(title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));
                //data.setNewsUrl(json.getString("url"));

                mList.add(data);

                mListTitle.add(title);
                mListUrl.add(url);
            }
            WeChatAdapter adapter = new WeChatAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
