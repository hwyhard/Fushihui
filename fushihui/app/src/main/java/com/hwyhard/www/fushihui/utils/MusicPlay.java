package com.hwyhard.www.fushihui.utils;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.SeekBar;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 音乐在线播放的工具类
 */

public class MusicPlay {
    private static final String TAG = "hwy";
    private static Player player;
    private static MusicPlay musicPlay;
    private String URLMUSIC = "http://bmob-cdn-11536.b0.upaiyun.com/2017/05/23/a36b475540c018fc80c35d18912f052f.mp3";
    //播放状态：0：不播放 1：开始播放 2：暂停播放 3：播放完毕
    private Context context;
    private static PlayStatus isPlayStatic = PlayStatus.NOPLAY;
    private static SimpleDateFormat format;

    public enum PlayStatus {
        NOPLAY(0),//0不播放
        PLAYING(1),
        PAUSE(2),
        FINISH(3);
        private int status;

        PlayStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public MusicPlay(Context context) {
        this.context = context;
    }

    public static void setPlayStatus(PlayStatus isPlayStatic) {
        MusicPlay.isPlayStatic = isPlayStatic;
    }

    public PlayStatus getPlayStatus() {
        return this.isPlayStatic;
    }

    public static MusicPlay getInstance(Context context) {
        if (musicPlay == null) {
            musicPlay = new MusicPlay(context);
            format = new SimpleDateFormat("mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        }
        return musicPlay;
    }

    public MusicPlay setMySeekListener(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(seekBar);
        initSetMediaPlayListener();
        return musicPlay;
    }
    private static MySeekProgressListener mySeekProgressListener1;
    public MusicPlay setMySeekProgressListener(MySeekProgressListener mySeekProgressListener){
        mySeekProgressListener1 = mySeekProgressListener;
        return musicPlay;
    }

    public interface MySeekProgressListener {
        void onMySeekProgressListener(String totalTime, String currentTime, int finishiStatus);
    }

    //是否在播放
    public boolean isPlaying() {
        return player.mediaPlayer.isPlaying();
    }

    //释放资源
    public void release() {
        player.mediaPlayer.release();
    }

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (player.mediaPlayer.isPlaying() || getPlayStatus() == PlayStatus.PLAYING) {
                    player.pause();
                    setPlayStatus(PlayStatus.PAUSE);
                } else if (getPlayStatus() == PlayStatus.NOPLAY || getPlayStatus() == PlayStatus.FINISH) {
                    if (getPlayStatus() == PlayStatus.FINISH) {
                        player.mediaPlayer.reset();//重置MediaPlayer
                        player.mediaPlayer.seekTo(0);//播放时间设置到0，下次重新播放
                    }
                    player.playUrl(URLMUSIC);
                    setPlayStatus(PlayStatus.PLAYING);
                }else if(getPlayStatus() == PlayStatus.PAUSE){
                    player.start();
                    setPlayStatus(PlayStatus.PLAYING);
                }
            }
        }).start();
    }

    //开始播放
    public void startPlay() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    //停止播放
    public void stopPlay() {

    }

    //继续播放
    public void continuePlay() {

    }

    private static void initSetMediaPlayListener() {
        player.mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
                Long totalLong = new Long((long) player.mediaPlayer.getDuration());
                Long currentLong = new Long((long) player.mediaPlayer.getCurrentPosition());
                String totalTime = format.format(mediaPlayer.getDuration());
                String currentTime = format.format((mediaPlayer.getCurrentPosition()));
                if (mediaPlayer.isPlaying()) {
                    mySeekProgressListener1.onMySeekProgressListener(totalTime, currentTime, 0);
                    if (totalTime.equals(currentTime)) {
                        setPlayStatus(PlayStatus.FINISH);
                        player.mediaPlayer.pause();
                    }
                }
            }
        });
    }

    public class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.d(TAG, "total:" + player.mediaPlayer.getDuration());
            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.mediaPlayer.seekTo(progress);
            if (getPlayStatus() == PlayStatus.FINISH) {
                play();
            }
        }
    }

    //销毁
    public void onDestroy() {
        if (player != null && player.mediaPlayer.isPlaying()) {
            player.stop();
            player = null;
        }
        setPlayStatus(PlayStatus.NOPLAY);
    }

    public void playByMobileSevice() {
        Intent intent = new Intent();
        Uri uri = Uri.parse(URLMUSIC);
        intent.setDataAndType(uri, "audio/mp3");
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }
    public void setMusicUrl(String musicUrl){
        URLMUSIC = musicUrl;
    }


}
