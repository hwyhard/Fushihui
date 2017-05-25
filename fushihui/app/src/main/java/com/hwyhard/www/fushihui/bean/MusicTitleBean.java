package com.hwyhard.www.fushihui.bean;

/**
 * Created by hwyhard on 2017/5/3.
 */

public class MusicTitleBean {
    private String musicTitleImage;
    private String musicTitleName;
    private String musicTitleSinger;
    private String musicItemId;
    public String getMusicItemId() {
        return musicItemId;
    }

    public void setMusicItemId(String musicItemId) {
        this.musicItemId = musicItemId;
    }

    public MusicTitleBean(String url, String name, String singer){
        this.musicTitleImage = url;
        this.musicTitleName = name;
        this.musicTitleSinger = singer;
    }
    public String getMusicTitleImage() {
        return musicTitleImage;
    }

    public void setMusicTitleImage(String musicTitleImage) {
        this.musicTitleImage = musicTitleImage;
    }

    public String getMusicTitleName() {
        return musicTitleName;
    }

    public void setMusicTitleName(String musicTitleName) {
        this.musicTitleName = musicTitleName;
    }

    public String getMusicTitleSinger() {
        return musicTitleSinger;
    }

    public void setMusicTitleSinger(String musicTitleSinger) {
        this.musicTitleSinger = musicTitleSinger;
    }
}
