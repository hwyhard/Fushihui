package com.hwyhard.www.fushihui.bean;

/**
 * Created by hwyhard on 17/4/7.
 * MessageBean是消息的实体类，对应每条消息（条目）的各个部分
 */

public class MessageBean {
    private String itemPic;//消息的图片
    private String itemPrimaryTitle;//主标题
    private String itemSubtitle;//副标题
    private String itemDate;//日期

    //初始化构造方法
    public MessageBean(String itemPic, String itemPrimaryTitle, String itemSubtitle, String itemDate){
        this.itemPic = itemPic;
        this.itemPrimaryTitle = itemPrimaryTitle;
        this.itemSubtitle = itemSubtitle;
        this.itemDate = itemDate;
    }
    public MessageBean(String itemPic){
        this.itemPic = itemPic;
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

    public void setItemSubtitle(String itemSubtitle) {
        this.itemSubtitle = itemSubtitle;
    }

    public String getItemSubtitle() {
        return itemSubtitle;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemDate() {
        return itemDate;
    }
}
