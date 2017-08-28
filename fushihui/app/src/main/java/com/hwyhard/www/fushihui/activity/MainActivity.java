package com.hwyhard.www.fushihui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.adapter.MyFragmentAdapter;
import com.hwyhard.www.fushihui.fragment.MusicFragment;
import com.hwyhard.www.fushihui.fragment.MainFragment;
import com.hwyhard.www.fushihui.fragment.ZhiHuFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MainActivity extends FragmentActivity {
    List<Fragment> fragmentList;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为所有的fragment创建列表
        fragmentList = new ArrayList<>();
        //初始化Bmob
        Bmob.initialize(this,"9e09da5d567ea31a5f00de06612a8d43");
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        //实例化所有的fragment
        MainFragment mainFragment = new MainFragment();
        ZhiHuFragment zhiHuFragment = new ZhiHuFragment();
        MusicFragment musicFragment = new MusicFragment();
        fragmentList.add(mainFragment);
        fragmentList.add(zhiHuFragment);
        fragmentList.add(musicFragment);
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragmentList);
        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        //为viewpager绑定自定义的适配器
        viewPager.setAdapter(myFragmentAdapter);
        //绑定TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }
    //fragment 跳转方法
    public void change_fragment(int id){
        viewPager.setCurrentItem(id);
    }

}