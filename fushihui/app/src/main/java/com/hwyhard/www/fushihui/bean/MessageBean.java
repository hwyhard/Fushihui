package com.hwyhard.www.fushihui.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by hwyhard on 17/4/7.
 * MessageBean是消息的实体类，对应每条消息（条目）的各个部分
 */

public class MessageBean extends BmobObject{
    private String itemPic;//消息的图片
    private String itemPrimaryTitle;//主标题
    private String itemSubTitle;//副标题
    private String itemDate;//日期
    private String itemTag;//标签

    public String getItemTag() {
        return itemTag;
    }

    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }


    public MessageBean(String itemPic,String itemPrimaryTitle,String itemSubTitle, String itemDate) {
        this.itemPic = itemPic;
        this.itemPrimaryTitle = itemPrimaryTitle;
        this.itemSubTitle = itemSubTitle;
        this.itemDate = itemDate;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPrimaryTitle(String itemPrimaryTitle) {
        this.itemPrimaryTitle = itemPrimaryTitle;
    }

    public String getItemPrimaryTitle() {
        return itemPrimaryTitle;
    }

    public void setItemSubTitle(String itemSubTitle) {
        this.itemSubTitle = itemSubTitle;
    }

    public String getItemSubTitle() {
        return itemSubTitle;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemDate() {
        return itemDate;
    }
}
