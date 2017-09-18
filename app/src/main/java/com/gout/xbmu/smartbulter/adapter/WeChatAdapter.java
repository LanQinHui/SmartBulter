package com.gout.xbmu.smartbulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.WeChatData;

import java.util.List;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.adapter
 * 文件名: WeChatAdapter
 * Created by LanQinHui on 2017/9/17.
 * 描述： TODO
 */

public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData>mList;
    private WeChatData data;

    public WeChatAdapter(Context mContext,List<WeChatData>mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.wechat_item,null);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_source = (TextView) convertView.findViewById(R.id.tv_source);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_source.setText(data.getSource());

        return convertView;
    }
    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_title;
        //private TextView tv_time;
        private TextView tv_source;
    }
}
