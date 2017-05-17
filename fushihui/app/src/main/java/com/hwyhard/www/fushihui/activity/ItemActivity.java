package com.hwyhard.www.fushihui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.bean.MessageItemBean;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class ItemActivity extends AppCompatActivity {
    TextView mscTagTv;
    TextView mscTitleTv;
    ImageView mscPicIv;
    TextView mscContentTv;
    String mscTag;
    String mscId;
    final static String MSC_ID = "mscID";
    final static String MSC_TAG = "mscTag";
    Handler handler;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemActivity.this.setTheme(R.style.MainItemTheme);
        AssetManager mgr = getAssets();
        Typeface tf = Typeface.createFromAsset(mgr,"fonts/FZYingXueJW.TTF");
        setContentView(R.layout.activity_item);
        mscTagTv = (TextView) findViewById(R.id.main_item_tag);
        mscTitleTv = (TextView) findViewById(R.id.main_item_title);
        mscPicIv = (ImageView) findViewById(R.id.item_activity_pic);
        mscContentTv = (TextView) findViewById(R.id.main_item_text);
        mscContentTv.setTypeface(tf);
        mContext = getApplicationContext();
        handler = new Handler();
        Bundle bundle = getIntent().getExtras();
        //标签
        mscTag = bundle.getString(MSC_TAG);
        //id
        mscId = bundle.getString(MSC_ID);
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<MessageItemBean> query = new BmobQuery<>();
                query.getObject(mscId, new QueryListener<MessageItemBean>() {
                    @Override
                    public void done(MessageItemBean messageItemBean, BmobException e) {
                        if (e == null) {
                            final String mscTitle = messageItemBean.getMesTitle();
                            final String mscImg = messageItemBean.getMesPic();
                            final String mscContent = messageItemBean.getMesContent();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mscTagTv.setText(mscTag);
                                    mscTitleTv.setText(mscTitle);
                                    Glide.with(ItemActivity.this)
                                            .load(mscImg)
                                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                            .into(mscPicIv);
                                    mscContentTv.setText(mscContent);
                                }
                            });
                        } else {
                            Log.d("MessageItemError", "错误：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        }).start();
        setTagBg();
    }

    @Override
    //创建菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //创建菜单上的点击效果
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_share_menu:
                share();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //分享功能
    public void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "This is a test of the function from fushuihui.");
        startActivity(Intent.createChooser(intent, "分享到"));

    }
    //设置变迁背景颜色
    public void setTagBg(){
        switch (mscTag){
            case "光影":{
//                mscTagTv.setBackgroundColor(getResources().getColor(R.color.colorItemTagMovie));
                mscTagTv.setBackground(getResources().getDrawable(R.drawable.tag_movie_shape));
                break;
            }
            case "沧海":{
//                mscTagTv.setBackgroundColor(getResources().getColor(R.color.colorItemTagHistory));
                mscTagTv.setBackground(getResources().getDrawable(R.drawable.tag_history_shape));
                break;
            }
            case "好物":{
//                mscTagTv.setBackgroundColor(getResources().getColor(R.color.colorItemTagLife));
                mscTagTv.setBackground(getResources().getDrawable(R.drawable.tag_life_shape));
                break;
            }
            case "致知":{
//                mscTagTv.setBackgroundColor(getResources().getColor(R.color.colorItemTagRead));
                mscTagTv.setBackground(getResources().getDrawable(R.drawable.tag_read_shape));
                break;
            }
        }

    }

}
