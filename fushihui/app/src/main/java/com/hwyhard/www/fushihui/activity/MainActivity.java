package com.hwyhard.www.fushihui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hwyhard.www.fushihui.adapter.MessageAdapter;
import com.hwyhard.www.fushihui.bean.MessageBean;
import com.hwyhard.www.fushihui.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView messageRv;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageRv = (RecyclerView) findViewById(R.id.main_rv_list);
        linearLayoutManager = new LinearLayoutManager(this);
        messageRv.setHasFixedSize(true);
        messageRv.setLayoutManager(linearLayoutManager);
        MessageAdapter messageAdapter = new MessageAdapter(createBeanList(100));
        messageRv.setAdapter(messageAdapter);
    }
    private List<MessageBean> createBeanList(int num) {
        List<MessageBean> list = new ArrayList<>();
        for(int i=0; i<num; i++) {

            MessageBean messageBean = new MessageBean();
//        messageBean1.setItemPic(R.drawable.test_pic1);
            messageBean.setItemPrimaryTitle("理想还能走多远？"+i);
            messageBean.setItemSubtitle("你劝我灭了心中的火。"+i);
            messageBean.setItemDate("April 7"+i);
            list.add(messageBean);
        }
        return list;
    }
//        MessageBean messageBean2 = new MessageBean();
////        messageBean2.setItemPic(R.drawable.test_pic5);
//        messageBean2.setItemPrimaryTitle("你一直在往我的剑上撞");
//        messageBean2.setItemSubtitle("死亡如风，常伴吾身。");
//        messageBean2.setItemDate("April 8");
//        list.add(messageBean2);
//        MessageBean messageBean3 = new MessageBean();
////        messageBean3.setItemPic(R.drawable.test_pic3);
//        messageBean3.setItemPrimaryTitle("感受原力吧");
//        messageBean3.setItemSubtitle("Do or not do, there is no try.");
//        messageBean3.setItemDate("April 9");
//        list.add(messageBean3);
//        MessageBean messageBean4 = new MessageBean();
////        messageBean4.setItemPic(R.drawable.test_pic4);
//        messageBean4.setItemPrimaryTitle("永远的海森堡。");
//        messageBean4.setItemSubtitle("我是个好人，就是没什么好报。");
//        messageBean4.setItemDate("April 10");
//        list.add(messageBean4);
//        return list;

}