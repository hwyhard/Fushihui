package com.hwyhard.www.fushihui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwyhard.www.fushihui.R;

/**
 * Created by hwyhard on 17/4/7.
 * MessageViewHolder类用于寻找并缓存视图在xml中的引用，在每次RecycleView需要为item创建视图时，
 * MessageAdapter便会创造足够的ViewHolder来减少findViewById的使用，提示效率
 */

public class MessageViewHolder extends RecyclerView.ViewHolder{
    public ImageView itemPicIv;//显示消息图片的视图
    public TextView itemPrimatyTv;//显示主标题的视图
    public TextView itemSubTv;//显示副标题的视图
    public TextView itemDateTv;//显示日期的视图
    public MessageViewHolder(View itemView) {
        super(itemView);
        //寻找各视图的引用
        itemPicIv = (ImageView) itemView.findViewById(R.id.list_item_pic);
        itemPrimatyTv = (TextView) itemView.findViewById(R.id.list_item_primarytitle);
        itemSubTv = (TextView) itemView.findViewById(R.id.list_item_subtitle);
        itemDateTv = (TextView) itemView.findViewById(R.id.list_item_date);
    }
}
