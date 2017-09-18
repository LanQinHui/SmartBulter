package com.gout.xbmu.smartbulter.entity;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.entity
 * 文件名: WeChatData
 * Created by LanQinHui on 2017/9/17.
 * 描述： 微信精选标题类
 */

public class WeChatData {


    //标题
    private String title;
    //出处
    private String source;
    //图片url
    private String imgUrl;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
