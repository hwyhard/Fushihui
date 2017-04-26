package com.hwyhard.www.fushihui.bean;

import java.util.List;

/**
 * Created by hwyhard on 17/4/26.
 */
//知乎缩略消息的实体类
public class ZhiHuBean {
    //日期
    public String date;
    //内容列表
    public List<ZhiHuStory> stories;
    public static class ZhiHuStory{
        //新闻标题
        public String title;
        //图像地址
        public List<String> images;
        //url和share_url最后的数字
        public int id;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
