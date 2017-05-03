package com.hwyhard.www.fushihui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwyhard.www.fushihui.R;

/**
 * Created by hwyhard on 2017/5/3.
 */

public class MusicTitleViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textViewName;
    public TextView textViewSinger;
    public MusicTitleViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.music_title_image);
        textViewName = (TextView) itemView.findViewById(R.id.music_title_name);
        textViewSinger = (TextView) itemView.findViewById(R.id.music_title_singer);
    }
}
