package com.hwyhard.www.fushihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.bean.MusicTitleBean;
import com.hwyhard.www.fushihui.viewholder.MusicTitleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwyhard on 2017/5/3.
 * 音乐列表的适配器
 */

public class MusicTitleAdapter extends RecyclerView.Adapter<MusicTitleViewHolder> {
    //音乐列表
    List<MusicTitleBean> musicTitleBeanList;
    Context mContext;
    OnItemClickListener onItemClickListener;
    private List<Integer> heights;
    public MusicTitleAdapter(List<MusicTitleBean> musicTitleBeanList) {
        this.musicTitleBeanList = musicTitleBeanList;
        makeRandomHeight(this.musicTitleBeanList);
    }

    @Override
    public MusicTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.music_fragment_item, parent, false);
        MusicTitleViewHolder musicTitleViewHolder = new MusicTitleViewHolder(view);
        return musicTitleViewHolder;
    }

    @Override
    public void onBindViewHolder(final MusicTitleViewHolder holder, int position) {
        //获得item的Layout布局参数
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        //将随机的高度赋予item布局
        params.height = heights.get(position);
        //将布局赋予给item
        holder.itemView.setLayoutParams(params);
        MusicTitleBean musicTitleBean = musicTitleBeanList.get(position);
        String imageUrl = musicTitleBean.getMusicTitleImage();
        Glide.with(mContext)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);
        holder.textViewName.setText(musicTitleBean.getMusicTitleName());
        holder.textViewSinger.setText(musicTitleBean.getMusicTitleSinger());
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return musicTitleBeanList.size();
    }
    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
   // 生成随机高度
    private void makeRandomHeight(List<MusicTitleBean> list){
        heights = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            heights.add((int)(300 + Math.random()*250));
        }

    }

}
