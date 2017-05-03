package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 主要的fragment页面
 */

public class MainFragment extends Fragment {
    RecyclerView messageRv;
    //创建测试用的url的数组
    String testUrl[] = new String[13];
    LinearLayoutManager linearLayoutManager;
    List<MessageBean> list;
    MessageAdapter messageAdapter;
    View.OnClickListener fabItemClickListener;
    Context mContext;
    private SpringFloatingActionMenu springFloatingActionMenu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        messageRv = (RecyclerView) view.findViewById(R.id.main_rv_list);
        mContext = getActivity();//获得上下文
        //初始化布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext);
        list = new ArrayList<>();
        initUrl();//初始化数据
        //创建FabMenu里Item的监听器
          fabItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MenuItemView menuItem = (MenuItemView) view;
//                Toast.makeText(mContext,menuItem.getLabelTextView().getText(),Toast.LENGTH_SHORT);
                startActivity(new Intent(mContext,ItemActivity.class));

            }
        };
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
        messageAdapter = new MessageAdapter(createBeanList(100));
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(mContext)
                .fab(fab)
                //添加菜单按钮参数依次是背景颜色，图标，标签，标签的颜色，点击事件
                .addMenuItem(R.color.colorRandom, R.mipmap.button_random, "随便看看", R.color.colorRandom, new View.OnClickListener() {
                    @Override
                    //点击后生成随机列表
                    public void onClick(View view) {
                        randomList();
//                        messageAdapter.notifyDataSetChanged();
                        messageAdapter = new MessageAdapter(list);
                        onItemClick();
                        messageRv.setAdapter(messageAdapter);
                        springFloatingActionMenu.hideMenu();
                    }
                })
                .addMenuItem(R.color.colorMovie,R.mipmap.button_movie,"影视",R.color.colorLable,fabItemClickListener)
                .addMenuItem(R.color.colorMusic,R.mipmap.button_music,"音乐",R.color.colorLable,fabItemClickListener)
                .addMenuItem(R.color.colorBook,R.mipmap.button_book,"阅读",R.color.colorLable,fabItemClickListener)
                .addMenuItem(R.color.colorLife,R.mipmap.button_life,"生活",R.color.colorLable,fabItemClickListener)
                .addMenuItem(R.color.colorHistory,R.mipmap.button_history,"历史",R.color.colorLable,fabItemClickListener)
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
//        fab.attachToRecyclerView(messageRv);
        messageRv.setHasFixedSize(true);
        //将布局管理器绑定到视图
        messageRv.setLayoutManager(linearLayoutManager);
        // 为item设置点击事件
        onItemClick();
        //为视图绑定数据监听器
        messageRv.setAdapter(messageAdapter);
        return view;

    }

    private List<MessageBean> createBeanList(int num) {
        for (int i = 0; i < num; i++) {
            if (i < 13) {
                MessageBean messageBean = new MessageBean(testUrl[i], "爱要怎么说出口" + i, "你劝我灭了心中的火" + i, "April 7" + i);
                list.add(messageBean);
            } else {
                int index = i % 13;
                MessageBean messageBean = new MessageBean(testUrl[index], "你一直在往我的剑上撞" + i, "死亡如风，常伴吾身" + i, "April 8" + i);
                list.add(messageBean);
            }
            Log.d("MainActivityUrl", list.get(i).getItemPic());

        }
        return list;
    }

    //初始化供测试的url
    private void initUrl() {
        for (int i = 0; i < 13; i++) {
            int index = i + 12;
            testUrl[i] = "http://img.ivsky.com/img/bizhi/pre/201406/19/iron_man-0" + index + ".jpg";
        }
    }
    //打乱列表生成随机列表
    public void randomList(){
        Collections.shuffle(list);
    }
    //item点击事件
    public void onItemClick(){
        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "click----->" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                startActivity(intent);
            }
        });
    }

}
