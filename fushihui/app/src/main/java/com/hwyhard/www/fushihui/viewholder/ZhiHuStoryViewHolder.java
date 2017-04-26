package com.hwyhard.www.fushihui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwyhard.www.fushihui.R;

/**
 * Created by hwyhard on 17/4/26.
 */

public class ZhiHuStoryViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView;
    public ZhiHuStoryViewHolder(View itemView) {
        super(itemView);
        //寻找各图的引用
        imageView = (ImageView) itemView.findViewById(R.id.zhihustory_item_image);
        textView = (TextView) itemView.findViewById(R.id.zhihustory_item_title);
    }
}
