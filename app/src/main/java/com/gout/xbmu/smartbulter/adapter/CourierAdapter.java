package com.gout.xbmu.smartbulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gout.xbmu.smartbulter.R;
import com.gout.xbmu.smartbulter.entity.CourierData;

import java.util.List;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.adapter
 * 文件名: CourierAdapter
 * Created by LanQinHui on 2017/8/17.
 * 描述： 微信精选
 */

public class CourierAdapter extends BaseAdapter{

    //上下文
    private Context mContext;
    //传入创建好的实体类
    private List<CourierData> mList;
    //布局加载器
    private LayoutInflater inflater;
    private CourierData data;

    public CourierAdapter(Context mContext,List<CourierData> mList){
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
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
        //第一次加载
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_conrier_ietm,null);
            viewHolder.tv_remark = (TextView) convertView.findViewById(R.id.tv_remark);
            viewHolder.tv_zone = (TextView) convertView.findViewById(R.id.tv_zone);
            viewHolder.tv_datatime = (TextView) convertView.findViewById(R.id.tv_datatime);
        //设置缓存、
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        //设置数据
        data = mList.get(position);

        viewHolder.tv_remark.setText(data.getRemark());
        viewHolder.tv_zone.setText(data.getZone());
        viewHolder.tv_datatime.setText(data.getDatetime());

        return convertView;
    }
    class ViewHolder{
        private TextView tv_remark;
        private TextView tv_zone;
        private TextView tv_datatime;

    }
}
