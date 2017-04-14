package com.hwyhard.www.fushihui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwyhard.www.fushihui.R;

public class ItemActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        imageView = (ImageView) findViewById(R.id.item_activity_pic);
        Glide.with(this).load(R.drawable.test_pic4).into(imageView);
    }
}
