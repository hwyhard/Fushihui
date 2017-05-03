package com.hwyhard.www.fushihui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hwyhard.www.fushihui.R;

public class ItemActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemActivity.this.setTheme(R.style.MainItemTheme);
        setContentView(R.layout.activity_item);
        //隐藏顶部状态栏
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        imageView = (ImageView) findViewById(R.id.item_activity_pic);
        Glide.with(this).load(R.drawable.test_pic4).into(imageView);
    }

    @Override
    //创建菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //创建菜单上的点击效果
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.main_share_menu:
                share();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    //分享功能
    public void share(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        intent.putExtra(Intent.EXTRA_TEXT,"This is a test of the function from fushuihui.");
        startActivity(Intent.createChooser(intent,"分享到"));

    }

}
