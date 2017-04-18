package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.ItemActivity;
import com.hwyhard.www.fushihui.adapter.MessageAdapter;
import com.hwyhard.www.fushihui.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 主要的fragment页面
 */

public class MainFragment extends Fragment{
    RecyclerView messageRv;
    //创建测试用的url的数组
    String testUrl[] = new String[13];
    LinearLayoutManager linearLayoutManager;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        messageRv = (RecyclerView) view.findViewById(R.id.main_rv_list);
        mContext = getActivity();//获得上下文
        //初始化布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext);
        initUrl();//初始化数据
        messageRv.setHasFixedSize(true);
        //将布局管理器绑定到视图
        messageRv.setLayoutManager(linearLayoutManager);
        MessageAdapter messageAdapter = new MessageAdapter(createBeanList(100));
        // 为item设置点击事件
        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"click----->"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ItemActivity.class);

                startActivity(intent);
            }
        });
        //为视图绑定数据监听器
        messageRv.setAdapter(messageAdapter);
        return view;

    }
    private List<MessageBean> createBeanList(int num) {
        List<MessageBean> list = new ArrayList<>();
        for(int i=0; i<num; i++) {
            if(i<13) {
                MessageBean messageBean = new MessageBean(testUrl[i], "爱要怎么说出口" + i, "你劝我灭了心中的火" + i, "April 7" + i);
                list.add(messageBean);
            }else {
                int index = i % 13;
                MessageBean messageBean = new MessageBean(testUrl[index],"你一直在往我的剑上撞"+i,"死亡如风，常伴吾身"+i,"April 8"+i);
                list.add(messageBean);
            }
            Log.d("MainActivityUrl",list.get(i).getItemPic());

        }
        return list;
    }
    //初始化供测试的url
    private void initUrl(){
        for(int i=0;i<13;i++) {
            int index = i+12;
            testUrl[i] = "http://img.ivsky.com/img/bizhi/pre/201406/19/iron_man-0" + index + ".jpg";
        }
    }


}
