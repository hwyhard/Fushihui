package com.hwyhard.www.fushihui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hwyhard.www.fushihui.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hwyhard on 17/4/18.
 * 豆瓣的fragment页面
 */

public class DouBanFragment extends Fragment{
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douban,null);
        textView = (TextView) view.findViewById(R.id.douban_item_text);
        String nowTime = new String("20170428");
        //String字符串格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            //字符串转Date格式
            Date nowDate = simpleDateFormat.parse(nowTime);
            //计算日期
            nowDate.setDate(nowDate.getDate()+5);
            //转成字符串用于显示
            textView.setText("5天后"+simpleDateFormat.format(nowDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }



}
