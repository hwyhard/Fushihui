package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.ZhiHuActivity;
import com.hwyhard.www.fushihui.adapter.ZhiHuTitleAdapter;
import com.hwyhard.www.fushihui.bean.ZhiHuBean;
import com.hwyhard.www.fushihui.utils.DateUtil;
import com.hwyhard.www.fushihui.utils.NetUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 知乎的fragment页面
 */

public class ZhiHuFragment extends Fragment {
    //    SwipeRefreshLayout swipeRefreshLayout;
//    RecyclerView recyclerView;
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    LinearLayoutManager linearLayoutManager;
    Context mContext;
    String jsonText;//返回的字符串形式的json内容
    String url;//需要请求的url
    String beforeUrlHead;//请求之前消息的url头部
    String beforeUrl;//请求之前消息的url
    NetUtil netUtil;
    Handler handler;
    //当前日期
    String totalDate;
    List<ZhiHuBean.ZhiHuStory> zhiHuStoryList;
    ZhiHuTitleAdapter zhiHuTitleAdapter;
    final static String TITLE_ID = "titleId";

    //    Dog dog;
//    ArrayList<String> color;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, null);
        mContext = getContext();
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.zhihuRv);
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.zhihu_swipe_refresh);
//        linearLayoutManager = new LinearLayoutManager(mContext);
        url = "http://news-at.zhihu.com/api/4/news/latest";
        beforeUrlHead = "http://news-at.zhihu.com/api/4/news/before/";
//        pullLoadMoreRecyclerView.setHasFixedSize(true);
        pullLoadMoreRecyclerView.setLinearLayout();
//        pullLoadMoreRecyclerView.setLayoutManager(linearLayoutManager);
        netUtil = new NetUtil();
        handler = new Handler();
        totalDate = "昨天的日期";
        //开启网络请求的子线程
        Thread netRequsetThread = new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    requestNews();
                }
            }
        });
        netRequsetThread.start();
        //等待网络请求线程执行完毕
        try {
            netRequsetThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("nowDate2",totalDate);
        //下拉刷新
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext,"正在刷新",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestNews();
                    }
                }).start();
                //设置动画时间为2s
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                loadMoreDate();
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//                    }
//                },3000);
            }
        });
        return view;
    }
    //加载更多数据的方法
    public void loadMoreDate(){
        Date date = DateUtil.StringToDate(totalDate);
        date.setDate(date.getDate()-1);
        String newDate = DateUtil.DateToString(date);
        totalDate = newDate;
        Log.d("NewDateTest",newDate+"");
        beforeUrl = beforeUrlHead + newDate;
        Thread beforeRequsetThread = new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        //进行网络请求
                        jsonText = netUtil.run(beforeUrl);
                        Gson gson = new Gson();
                        ZhiHuBean zhiHuBean = gson.fromJson(jsonText, ZhiHuBean.class);
                        //新请求到的消息队列
                        List<ZhiHuBean.ZhiHuStory> addList = zhiHuBean.stories;
                        //将新消息加入原始消息对队列
                        for(ZhiHuBean.ZhiHuStory zhiHuStory:addList){
                            zhiHuStoryList.add(zhiHuStory);
                        }
                        //向handler发送处理请求操作
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //更新ui内容
//                                zhiHuTitleAdapter = new ZhiHuTitleAdapter(zhiHuStoryList);
//                                onItemClick();
//                                pullLoadMoreRecyclerView.setAdapter(zhiHuTitleAdapter);
                                zhiHuTitleAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        beforeRequsetThread.start();

    }
    //事件的点击方法
    public void onItemClick(){
        zhiHuTitleAdapter.setOnItemCliclListener(new ZhiHuTitleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //获取该条title的id
                int titleId = zhiHuStoryList.get(position).getId();
                Log.d("ZhiHuId", titleId + "");
                Bundle bundle = new Bundle();
                //用bundle传出去
                bundle.putInt(TITLE_ID, titleId);
                Intent intent = new Intent(mContext, ZhiHuActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //获得最新网络请求的方法
    public void requestNews(){
        //进行网络请求
        try {
            jsonText = netUtil.run(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ZhiHuBean zhiHuBean = gson.fromJson(jsonText, ZhiHuBean.class);
        zhiHuStoryList = zhiHuBean.stories;
        totalDate = zhiHuBean.getDate();
        Log.d("nowDate1",totalDate+" ");
        Log.d("ZhiHuStory", zhiHuStoryList.get(0).getImages() + "");
        //向handler发送处理请求操作
        handler.post(new Runnable() {
            @Override
            public void run() {
                //更新ui内容
                Log.d("ZhiHuApi2", " " + jsonText);
                zhiHuTitleAdapter = new ZhiHuTitleAdapter(zhiHuStoryList);
                onItemClick();
                pullLoadMoreRecyclerView.setAdapter(zhiHuTitleAdapter);
            }
        });
    }





}
