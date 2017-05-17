package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.ItemActivity;
import com.hwyhard.www.fushihui.adapter.MessageAdapter;
import com.hwyhard.www.fushihui.bean.MessageBean;
import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.MenuItemView;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by hwyhard on 17/4/18.
 * 主要的fragment页面
 */

public class MainFragment extends Fragment {
    //fab菜单按钮点击监听
    View.OnClickListener fabItemClickListener;
    //上下文
    Context mContext;
    //二级菜单
    private SpringFloatingActionMenu springFloatingActionMenu;
    //主列表视图
    PullLoadMoreRecyclerView messageRv;
    LinearLayoutManager linearLayoutManager;
    Handler handler;
    //主消息列表
    public List<MessageBean> messageBeanList;
    //消息适配器
    MessageAdapter messageAdapter;
    int number = 0;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        mContext = getContext();//获得上下文
        //实例化视图
        messageRv = (PullLoadMoreRecyclerView) view.findViewById(R.id.main_rv_list);
        //初始化messageList
        messageBeanList = new ArrayList<>();
        messageRv.setLinearLayout();
        handler = new Handler();
        //开启网络请求线程
        Thread requestMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                requestMessage();
            }
        });
        requestMessageThread.start();
        //等待网络线程请求完毕
        try {
            requestMessageThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("MsList6", messageBeanList.size() + "");
        //创建FabMenu里Item的监听器
        fabItemClickListener = createFabListener();
        initFab();
        initRcyandPull();
        return view;
    }


    //打乱列表生成随机列表
    public void randomList() {
        Collections.shuffle(messageBeanList);
    }

    //item点击事件
    public void onItemClick() {
        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "click----->" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                startActivity(intent);
            }
        });
    }

    //创建fabItem点击事件
    public View.OnClickListener createFabListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuItemView menuItem = (MenuItemView) view;
                String tag = (String) menuItem.getLabelTextView().getText();
                switch (tag) {
                    case "随便看看": {

                        randomList();
                        messageAdapter = new MessageAdapter(messageBeanList);
                        onItemClick();
                        messageRv.setAdapter(messageAdapter);
                        springFloatingActionMenu.hideMenu();
                        break;
                    }
                    case "影视": {
                        BmobQuery<MessageBean> query = new BmobQuery<>();
                        query.count(MessageBean.class, new CountListener() {
                            @Override
                            public void done(Integer integer, BmobException e) {
                                Log.d("queryCount", "一共有：" + integer + "条数据");
                                springFloatingActionMenu.hideMenu();
                            }
                        });
                        break;
                    }
                    case "音乐":
                        break;
                    case "阅读":
                        break;
                    case "生活":
                        break;
                    case "历史":
                        break;
                    default:
                        break;
                }
            }
        };
    }

    //初始化fab
    public void initFab() {
        //手动创建fab
        final FloatingActionButton fab = new FloatingActionButton(mContext);
        //菜单button大小
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        // button icon
        fab.setImageResource(R.mipmap.button_category);
        fab.setColorNormalResId(R.color.colorPrimaryGreen);
        fab.setColorPressedResId(R.color.colorGrayGreen);
        fab.setColorRippleResId(R.color.colorRippleGreen);
        fab.setShadow(true);
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(mContext)
                .fab(fab)
                //添加菜单按钮参数依次是背景颜色，图标，标签，标签的颜色，点击事件
                .addMenuItem(R.color.colorRandom, R.mipmap.button_random, "随便看看", R.color.colorRandom, fabItemClickListener)
                .addMenuItem(R.color.colorMovie, R.mipmap.button_movie, "影视", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorMusic, R.mipmap.button_music, "音乐", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorBook, R.mipmap.button_book, "阅读", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorLife, R.mipmap.button_life, "生活", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorHistory, R.mipmap.button_history, "历史", R.color.colorLable, fabItemClickListener)
                //设置菜单展开后的动画
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                .revealColor(R.color.colorPrimaryGreen)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageResource(R.mipmap.button_cancel);
                        fab.setColorNormalResId(R.color.colorFabBack);
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageResource(R.mipmap.button_category);
                        fab.setColorNormalResId(R.color.colorPrimaryGreen);
                    }
                }).build();
    }

    //网络请求消息队列
    public void requestMessage() {
        //查询数据库中所有数据
        BmobQuery<MessageBean> query = new BmobQuery<MessageBean>();
        query.setLimit(20)
                .findObjects(new FindListener<MessageBean>() {
                    @Override
                    public void done(List<MessageBean> list, BmobException e) {
                        if (e == null) {
                            messageBeanList = list;
//                                messageBeanList.add(new MessageBean("http://post.smzdm.com/p/420473/pic_2/","柯基","test","5/17"));
                            //向handler发送请求
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //更新ui内容
                                    messageAdapter = new MessageAdapter(messageBeanList);
                                    onItemClick();
                                    messageRv.setAdapter(messageAdapter);
                                }
                            });
                        } else {
                            Log.d("queryError", "错误：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });

    }

    public void initRcyandPull() {
        messageRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            //设置下拉刷新
            public void onRefresh() {
                Toast.makeText(mContext, "正在刷新", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestMessage();
                    }
                }).start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        messageRv.setPullLoadMoreCompleted();
                    }
                }, 1500);
                Toast.makeText(mContext, "刷新完毕", Toast.LENGTH_SHORT).show();
            }

            //设置加载更多
            @Override
            public void onLoadMore() {
                messageRv.setPullLoadMoreCompleted();
            }
        });
    }
}
