package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.ZhiHuActivity;
import com.hwyhard.www.fushihui.adapter.ZhiHuTitleAdapter;
import com.hwyhard.www.fushihui.bean.ZhiHuBean;
import com.hwyhard.www.fushihui.utils.NetUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 知乎的fragment页面
 */

public class ZhiHuFragment extends Fragment{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Context mContext;
    String jsonText;//返回的字符串形式的json内容
    String url;//需要请求的url
    NetUtil netUtil;
    Handler handler;
    List<ZhiHuBean.ZhiHuStory> zhiHuStoryList;
    ZhiHuTitleAdapter zhiHuTitleAdapter;
    final static String TITLE_ID = "titleId";
//    Dog dog;
//    ArrayList<String> color;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu,null);
        mContext = getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.zhihuRv);
        linearLayoutManager = new LinearLayoutManager(mContext);
        url = "http://news-at.zhihu.com/api/4/news/latest";
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        netUtil = new NetUtil();
        handler = new Handler();
//        final Type listType = new TypeToken<ArrayList<ZhiHuBean.ZhiHuStory>>(){}.getType();
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //进行网络请求
                    jsonText = netUtil.run(url);
                    Log.d("ZhiHuApi1"," "+jsonText);
                    Gson gson = new Gson();
                    ZhiHuBean zhiHuBean = gson.fromJson(jsonText,ZhiHuBean.class);
                    zhiHuStoryList = zhiHuBean.stories;
                    Log.d("ZhiHuStory",zhiHuStoryList.get(0).getImages()+"");

                    //向handler发送处理请求操作
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //更新ui内容
                            Log.d("ZhiHuApi2"," "+jsonText);
                            zhiHuTitleAdapter = new ZhiHuTitleAdapter(zhiHuStoryList);
                            zhiHuTitleAdapter.setOnItemCliclListener(new ZhiHuTitleAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {
                                    //获取该条title的id
                                    int titleId = zhiHuStoryList.get(position).getId();
                                    Log.d("ZhiHuId",titleId+"");
                                    Bundle bundle = new Bundle();
                                    //用bundle传出去
                                    bundle.putInt(TITLE_ID,titleId);
                                    Intent intent = new Intent(mContext, ZhiHuActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(zhiHuTitleAdapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.d("ZhiHuApi3"," "+jsonText);

//        dog = new Dog();
//        color = new ArrayList<>();
//        dog.id = 001;
//        dog.name = "tony";
//        dog.age = 2;
//        color.add("brown");
//        color.add("white");
//        dog.color = color;
//        dog.cat = new Cat();
//        dog.cat.weight = 11;
//        dog.cat.volume = "high";
//        dog.cat.usedNames = new String[]{"pipi","crik"};
//        Gson gson = new Gson();
//        //Java对象转换成json字符串
//        jsonText = gson.toJson(dog);
//        Dog newDog = new Dog();
//        //字符串解析成Java对象
//        newDog = gson.fromJson(jsonText,Dog.class);
//        textView.setText("name:"+newDog.name+" age:"+newDog.age +"color:"+newDog.color.get(0) +"catWeight:"+newDog.cat.weight+"catUsedNames:"+dog.cat.usedNames[1]);
        return view;
    }
//    public class Dog{
//        public int id;
//        public String name;
//        public int age;
//        public ArrayList<String> color;
//        Cat cat;
//
//    }
//    public class Cat{
//        int weight;
//        String volume;
//        String usedNames[];
//    }



}
