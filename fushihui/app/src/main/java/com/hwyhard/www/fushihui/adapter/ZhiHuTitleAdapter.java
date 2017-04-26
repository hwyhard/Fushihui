package com.hwyhard.www.fushihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.bean.ZhiHuBean;
import com.hwyhard.www.fushihui.viewholder.ZhiHuStoryViewHolder;

import java.util.List;

/**
 * Created by hwyhard on 17/4/26.
 * 知乎列表的适配器
 */

public class ZhiHuTitleAdapter extends RecyclerView.Adapter<ZhiHuStoryViewHolder>{
    //知乎缩略消息列表
    private List<ZhiHuBean.ZhiHuStory> zhiHuStoryList;
    OnItemClickListener onItemClickListener;
    Context mContext;
    //构造方法
    public ZhiHuTitleAdapter(List<ZhiHuBean.ZhiHuStory> zhiHuStoryList){
        this.zhiHuStoryList = zhiHuStoryList;
    }


    @Override
    public ZhiHuStoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.zhihustory_item,parent,false);
        ZhiHuStoryViewHolder zhiHuStoryViewHolder = new ZhiHuStoryViewHolder(view);
        return zhiHuStoryViewHolder;
    }

    @Override
    public void onBindViewHolder(final ZhiHuStoryViewHolder holder, int position) {
        ZhiHuBean.ZhiHuStory zhiHuStory = zhiHuStoryList.get(position);
        //获取图片
        final String images = zhiHuStory.getImages().get(0);
        Log.d("ZhiHuAdapter",images);
        Glide.with(mContext)
                .load(images)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);
        holder.textView.setText(zhiHuStory.getTitle());
        //实现点击方法的回调
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return zhiHuStoryList.size();
    }
    //处理点击事件的接口
    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    public void setOnItemCliclListener(OnItemClickListener onItemCliclListener){
        this.onItemClickListener = onItemCliclListener;
    }
}
