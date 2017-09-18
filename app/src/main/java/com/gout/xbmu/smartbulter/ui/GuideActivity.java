package com.gout.xbmu.smartbulter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gout.xbmu.smartbulter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.ui
 * 文件名: GuideActivity
 * Created by LanQinHui on 2017/8/14.
 * 描述： 引导页
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    //容器
    private List<View>mList = new ArrayList<>();
    private View view1,view2,view3;
    //小圆点
    private ImageView point1,point2,point3;
    //跳过
    private ImageView iv_back;
    //开始按钮
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_guide);

        initView();
    }

    //初始化布局
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        //设置默认图片
        setPointImg(true,false,false);

        view1 = View.inflate(this,R.layout.pager_item_one,null);
        view2 = View.inflate(this,R.layout.pager_item_two,null);
        view3 = View.inflate(this,R.layout.pager_item_three,null);

        //找到第三页的开始按钮
        view3.findViewById(R.id.btn_start).setOnClickListener(this);

        //装载
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //设置ViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //Pager切换
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setPointImg(true,false,false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false,true,false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        setPointImg(false,false,true);
                        iv_back.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //点击按钮跳转到主页面
    @Override
    public void onClick(View v) {
       switch (v.getId()){
            case R.id.btn_start:
            case R.id.iv_back:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
       }

    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mList.get(position));
            //super.destroyItem(container, position, object);
        }
    }

    //设置小圆点选中的效果
    public void setPointImg(boolean isCheck1,boolean isCheck2,boolean isCheck3){
        if(isCheck1){
            point1.setBackgroundResource(R.drawable.point_on);
        }else {
            point1.setBackgroundResource(R.drawable.point_off);
        }
        if(isCheck2){
            point2.setBackgroundResource(R.drawable.point_on);
        }else {
            point2.setBackgroundResource(R.drawable.point_off);
        }
        if(isCheck3){
            point3.setBackgroundResource(R.drawable.point_on);
        }else {
            point3.setBackgroundResource(R.drawable.point_off);
        }
    }
}
