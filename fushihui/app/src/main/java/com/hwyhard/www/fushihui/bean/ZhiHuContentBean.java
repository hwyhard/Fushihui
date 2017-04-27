package com.hwyhard.www.fushihui.bean;

/**
 * Created by hwyhard on 17/4/27.
 * 知乎消息的实体类
 */

public class ZhiHuContentBean {

    //图片的内容提供方
    public String image_source;
    //新闻标题
    public String title;
    //浏览页面大图
    public String image;
    //js 供手机端WebView使用
    public String js[];
    //新闻的类型
    public String type;
    //新闻的id
    public int id;
    //css 供手机端WebView使用
    public String css[];
    //HTML格式的新闻
    public String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setIamge(String iamge) {
        this.image = image;
    }

    public String[] getJs() {
        return js;
    }

    public void setJs(String[] js) {
        this.js = js;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }


}
