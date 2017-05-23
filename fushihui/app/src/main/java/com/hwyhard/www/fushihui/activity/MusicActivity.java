package com.hwyhard.www.fushihui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.bean.MusicItem;
import com.hwyhard.www.fushihui.utils.MusicPlay;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView musicPicIw;
    ImageView playBtn;
    TextView startTv;
    TextView endTv;
    SeekBar seekBar;
    private final int MUSIC_FINISH = 1000;
    private MusicPlay musicPlay;
    private SimpleDateFormat format;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MUSIC_FINISH:
                    startTv.setText("0:00");//播放完毕后重置
                    seekBar.setProgress(0);
                    playBtn.setBackgroundResource(R.drawable.play);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT > 21){
            View decorView = getWindow().getDecorView();
            //让应用主题占据状态栏的空间
            int open = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(open);
            //设置状态栏颜色透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_music);
        musicPicIw = (ImageView) findViewById(R.id.music_pic);
        playBtn = (ImageView) findViewById(R.id.music_play_btn);
        startTv = (TextView) findViewById(R.id.music_start);
        endTv = (TextView) findViewById(R.id.music_end);
        seekBar = (SeekBar) findViewById(R.id.music_bar);
        initListener();
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final BmobQuery<MusicItem> query = new BmobQuery<>();
                query.getObject("rYHs888B", new QueryListener<MusicItem>() {
                    @Override
                    public void done(MusicItem musicItem, BmobException e) {
                        if(e == null){
                            final String musicPic = musicItem.getMusicPic();
                            final String musicUrl = musicItem.getMusicUrl();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(MusicActivity.this)
                                            .load(musicPic)
                                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                            .into(musicPicIw);
                                    musicPlay.setMusicUrl(musicUrl);
                                }
                            });
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.music_play_btn:
                Integer tag = (Integer) playBtn.getTag();
                if (musicPlay.isPlaying()) {
                    playBtn.setBackgroundResource(R.drawable.play);
                } else {
                    playBtn.setBackgroundResource(R.drawable.stop);
                }
                musicPlay.play();
                break;
        }

    }

    private void initView() {
        format = new SimpleDateFormat("mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        musicPlay = MusicPlay.getInstance(this).setMySeekListener(seekBar)
                .setMySeekProgressListener(new MusicPlay.MySeekProgressListener() {
                    @Override
                    public void onMySeekProgressListener(String totalTime, String currentTime, int finishiStatus) {
                        if (finishiStatus == 100) {
                            Log.d("player", "播放完毕！");
                        } else {
                            endTv.setText(totalTime);
                            if (totalTime.equals(currentTime)) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Message msg = handler.obtainMessage();
                                        msg.what = MUSIC_FINISH;
                                        handler.sendMessage(msg);
                                    }
                                }, 800);
                            } else {
                                endTv.setText(totalTime);
                                startTv.setText(currentTime);
                            }
                        }
                    }
                });
        playBtn.setBackgroundResource(R.drawable.play);
    }

    private void initListener() {
        playBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicPlay != null) {
            musicPlay.onDestroy();
        }
    }

}