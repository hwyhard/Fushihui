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
import com.hwyhard.www.fushihui.bean.MessageBean;
import com.hwyhard.www.fushihui.viewholder.MessageViewHolder;

import java.util.List;

/**
 * Created by hwyhard on 17/4/7.
 * 为MainActivity的RecycleView创建的消息适配器
 * 完成创建条目时视图与数据的填充适配
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    //实体类的消息列表
    private List<MessageBean> messageBeanList;
    Context mContext ;
    //新建一个保存用户设置的监听器及其set方法
    private OnItemClickListener onItemClickListener;
    //构造方法
    public MessageAdapter(List<MessageBean> list){
        messageBeanList = list;
    }
    @Override
    //创建每个item时会用到，返回MessageViewHolder
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.main_list_item,parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return  messageViewHolder;
    }
    // 将视图与实体类中的数据相结合
    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        MessageBean messageBean = messageBeanList.get(position);
        //获取图片的url
        final String url = messageBean.getItemPic();
        //多使用Log工具进行局部测试和追踪
        Log.d("bindUrl","============>BindViewHolder"+url);
        //设置图片的加载方式和缓存策略
        Glide.with(mContext)
                .load(url)
//                .placeholder(R.drawable.test_pic1)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.itemPicIv);
        holder.itemSubTv.setText(messageBean.getItemSubtitle());
        holder.itemPrimatyTv.setText(messageBean.getItemPrimaryTitle());
        holder.itemDateTv.setText(messageBean.getItemDate());
        //实现点击方法的回调
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener .onItemClick(holder.itemView,position);
                }
            });
        }
    }
    //返回列表条目的数量
    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }
    //新建一个内部接口处理点击事件
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    //set用户设置的监听器
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


}
