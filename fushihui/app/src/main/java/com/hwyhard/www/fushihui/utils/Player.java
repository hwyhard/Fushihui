package com.hwyhard.www.fushihui.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static android.media.MediaPlayer.OnBufferingUpdateListener;
import static android.media.MediaPlayer.OnCompletionListener;
import static android.media.MediaPlayer.OnPreparedListener;

/**
 */

public class Player implements OnPreparedListener,OnBufferingUpdateListener,OnCompletionListener{
    public MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer = new Timer();
    public Player(SeekBar seekBar){
        super();
        this.seekBar = seekBar;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        timer.schedule(timerTask,0,1000);
    }
     TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if(mediaPlayer == null)
                return;
            if(mediaPlayer.isPlaying() && seekBar.isPressed() == false){
                handler.sendEmptyMessage(0);
            }
        }
    };
    Handler handler = new Handler(){
        public void handleMessage(Message message){
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration>=0){
                long pos = seekBar.getMax() * position/duration;
                seekBar.setProgress((int)pos);
            }
        }
    };
    public void play(){
        mediaPlayer.start();
    }
    public void playUrl(String url){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e){
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void start(){
        mediaPlayer.start();
    }
    public void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
        seekBar.setSecondaryProgress(percent);
        int currentProgress = seekBar.getMax()*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration();
        Log.d(currentProgress+"%", percent+"buffer");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("mediaPlayer","onCompletion");
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        Log.d("mediaPlayer","onPrepared");
    }
}
