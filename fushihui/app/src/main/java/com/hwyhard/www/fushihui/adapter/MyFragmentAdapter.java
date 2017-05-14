package com.hwyhard.www.fushihui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    //初始化fragment列表
    private List<Fragment> fragmentList;
    //初始化tab标签列表
    private String title[] = new String[]{"万象","知乎","天籁"};
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
