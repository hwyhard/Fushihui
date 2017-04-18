package com.hwyhard.www.fushihui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwyhard.www.fushihui.R;

/**
 * Created by hwyhard on 17/4/18.
 * 知乎的fragment页面
 */

public class ZhiHuFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu,null);
        return view;
    }
}
