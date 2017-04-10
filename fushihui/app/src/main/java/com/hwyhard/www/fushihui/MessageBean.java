package com.hwyhard.www.fushihui;

/**
 * Created by hwyhard on 17/4/7.
 * MessageBean是消息的实体类，对应每条消息（条目）的各个部分
 */

public class MessageBean {
    private int itemPic;//消息的图片
    private String itemPrimaryTitle;//主标题
    private String itemSubtitle;//副标题
    private String itemDate;//日期

    public void setItemPic(int itemPic) {
        this.itemPic = itemPic;
    }

    public int getItemPic() {
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
