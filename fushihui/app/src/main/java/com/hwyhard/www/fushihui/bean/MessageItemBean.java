package com.hwyhard.www.fushihui.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by hwyhard on 2017/5/17.
 */

public class MessageItemBean extends BmobObject {
    private String mesTitle;


    public String getMesTitle() {
        return mesTitle;
    }

    public void setMesTitle(String mesTitle) {
        this.mesTitle = mesTitle;
    }

    public String getMesPic() {
        return mesPic;
    }

    public void setMesPic(String mesPic) {
        this.mesPic = mesPic;
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent;
    }

    private String mesPic;
    private String mesContent;
}
