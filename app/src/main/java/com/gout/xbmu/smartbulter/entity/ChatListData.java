package com.gout.xbmu.smartbulter.entity;

/**
 * 项目名: SmartBulter
 * 包名:   com.gout.xbmu.smartbulter.entity
 * 文件名: ChatListData
 * Created by LanQinHui on 2017/9/17.
 * 描述： 对话
 */

public class ChatListData {
    //type
    private int type;
    //文本
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
