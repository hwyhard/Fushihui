package com.hwyhard.www.fushihui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.bean.ZhiHuContentBean;
import com.hwyhard.www.fushihui.utils.NetUtil;
import com.hwyhard.www.fushihui.utils.WebUtil;

import java.io.IOException;

public class ZhiHuActivity extends AppCompatActivity {
    TextView textView_title;
    ImageView imageView;
    WebView webView;
    int titleId;
    //id对应的url
    Handler handler;
    NetUtil netUtil;
    String jsonText;
    String url;
    String urlHead;
    Context mContext;
    //根据titleId从bundle中获取id
    private final static String TITLE_ID = "titleId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu);
        Bundle bundle = getIntent().getExtras();
        titleId = bundle.getInt(TITLE_ID);
        netUtil = new NetUtil();
        handler = new Handler();
        //隐藏状态栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = getApplicationContext();
        Log.d("ZhiHuAc",titleId+"");
        urlHead = "http://news-at.zhihu.com/api/4/news/";
        //将id拼接到url头部后面组成url
        url = urlHead + titleId;
        Log.d("ZhiHuAc",url);
        webView = (WebView) findViewById(R.id.zhihu_content_item_webview);
        textView_title = (TextView) findViewById(R.id.zhihu_content_item_title);
        imageView = (ImageView) findViewById(R.id.zhihu_content_item_image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //网络请求
                try {
                    jsonText = netUtil.run(url);
                    Log.d("ZhiHuNet",jsonText);
                    Gson gson = new Gson();
                    final ZhiHuContentBean zhiHuContentBean = gson.fromJson(jsonText,ZhiHuContentBean.class);
                    Log.d("ZhiHuNet",zhiHuContentBean.getType()+"");
                    //向handler发送请求
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //更新ui
                            String contentImage = zhiHuContentBean.getImage();
                            String contentTitle = zhiHuContentBean.getTitle();
                            //获得html内容body
                            String contentBody = zhiHuContentBean.getBody();
                            //获得css样式
                            String contentCss[] = zhiHuContentBean.getCss();
                            //将html与css结合
                            String finalContent = WebUtil.buildHtmlWithCss(contentBody,contentCss,false);
                            Log.d("ZhiHuNet",contentImage);
                            Glide.with(mContext)
                                    .load(contentImage)
                                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                    .into(imageView);
                            textView_title.setText(contentTitle);
                            webView.setWebViewClient(new WebViewClient(){
                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                    view.loadUrl(url);
                                    return true;
                                }
                            });
                            webView.loadData(finalContent,"text/html;charset=UTF-8",null);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

}
