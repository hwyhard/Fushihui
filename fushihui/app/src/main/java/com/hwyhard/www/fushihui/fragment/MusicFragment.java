package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.MusicActivity;
import com.hwyhard.www.fushihui.adapter.MusicTitleAdapter;
import com.hwyhard.www.fushihui.bean.MusicTitleBean;
import com.hwyhard.www.fushihui.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 歌曲列表的fragment页面
 */

public class MusicFragment extends Fragment{
    @Nullable
    RecyclerView musicRv;
    List<MusicTitleBean> musicTitleBeanList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    MusicTitleAdapter musicTitleAdapter;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music,null);
        mContext = getContext();
        musicRv = (RecyclerView) view.findViewById(R.id.music_fragment_rv);
        //设置间隔
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(16);
        musicRv.addItemDecoration(spaceItemDecoration);
        //设置列数和滚动方向
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        musicRv.setItemAnimator(new DefaultItemAnimator());
        musicTitleBeanList = new ArrayList<>();
        musicRv.setLayoutManager(staggeredGridLayoutManager);
        initSongs(100);
        musicTitleAdapter = new MusicTitleAdapter(musicTitleBeanList);
        musicTitleAdapter.setOnItemClickListener(new MusicTitleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                startActivity(intent);
            }
        });
        musicRv.setAdapter(musicTitleAdapter);

        return view;
    }
    public void initSongs(int num){
        musicTitleBeanList.add(new MusicTitleBean("http://uploads.xuexila.com/allimg/1610/884-161019102254.png","单车","陈奕迅"));
        musicTitleBeanList.add(new MusicTitleBean("http://ntdimg.com/pic/2014/12-25/p5785361a91727456.jpg","可惜没如果","林俊杰"));
        musicTitleBeanList.add(new MusicTitleBean("http://cloudmind.info/wp-content/uploads/2015/04/Love-Story-FanMade-Single-Cover-fearless-taylor-swift-album-14882711-588-588.jpg","Love Story","Taylor Swift"));
        musicTitleBeanList.add(new MusicTitleBean("http://jgospel.net/media/91839/.jpg","浪费","林宥嘉"));
        musicTitleBeanList.add(new MusicTitleBean("http://jgospel.net/media/59315/.jpg","轨迹","周杰伦"));
        musicTitleBeanList.add(new MusicTitleBean("http://www.168kk.com/uploads/allimg/170119/20-1F119164S9.jpg","王子与公主","薛之谦"));
        musicTitleBeanList.add(new MusicTitleBean("http://jgospel.net/media/58473/.75783.bt.jpg","喜帖街","谢安琪"));
        musicTitleBeanList.add(new MusicTitleBean("http://pic.pimg.tw/prescott09/4a7c5e6258c40.jpg","失落沙洲","徐佳莹"));
        musicTitleBeanList.add(new MusicTitleBean("http://i1.sinaimg.cn/ent/2011/0507/S30718T1304769317695.jpg","遇见","孙燕姿"));
        musicTitleBeanList.add(new MusicTitleBean("http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=http://mmbiz.qpic.cn/mmbiz_jpg/RISgJ2lrXzwNugOB0PziaiaKcaz9usvwUOqFTOOnB4hn4UO0rIN1OJJQZaOBiae9u6mtdF6DEZbnWImn476ULQS7w/0?wx_fmt=jpeg","玩笑","刘瑞琦"));
        for(int i=10;i<num;i++){
            musicTitleBeanList.add(musicTitleBeanList.get(i%10));
        }

    }



}
