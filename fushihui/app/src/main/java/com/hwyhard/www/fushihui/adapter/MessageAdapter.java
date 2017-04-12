package com.hwyhard.www.fushihui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    //构造方法
    public MessageAdapter(List<MessageBean> list){
        messageBeanList = list;
    }
    @Override
    //创建每个item时会用到，返回MessageViewHolder
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_list_item,parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return  messageViewHolder;
    }
    // 将视图与实体类中的数据相结合
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageBean messageBean = messageBeanList.get(position);
        holder.itemPicIv.setImageResource(messageBean.getItemPic());
        holder.itemSubTv.setText(messageBean.getItemSubtitle());
        holder.itemPrimatyTv.setText(messageBean.getItemPrimaryTitle());
        holder.itemDateTv.setText(messageBean.getItemDate());
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }
}
