package com.hwyhard.www.fushihui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwyhard.www.fushihui.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.splash_image);
        Glide.with(SplashActivity.this)
                .load(R.drawable.app_cover_last)
                .into(imageView);
        handler = new Handler();
        //设置欢迎页时间
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                //第一次进入后欢迎页Activity关闭
                SplashActivity.this.finish();
            }
        },2000);//2000ms

    }
}
