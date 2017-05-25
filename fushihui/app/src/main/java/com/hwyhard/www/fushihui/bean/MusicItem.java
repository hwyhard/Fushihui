package com.hwyhard.www.fushihui.bean;

/**
 */

public class MusicItem {
    private String musicUrl;//歌曲链接
    private String musicPic;//图片链接

    public String getMusicLrc() {
        return musicLrc;
    }

    public void setMusicLrc(String musicLrc) {
        this.musicLrc = musicLrc;
    }

    private String musicLrc;//歌词
    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicPic() {
        return musicPic;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic;
    }


}
