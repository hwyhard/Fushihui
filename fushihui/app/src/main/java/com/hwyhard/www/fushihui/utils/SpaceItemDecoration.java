package com.hwyhard.www.fushihui.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hwyhard on 2017/5/3.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int space;
    public SpaceItemDecoration(int space){
        this.space = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        outRect.top = 0;
//        if(parent.getChildAdapterPosition(view)==0 ){
//            outRect.top=space;
//        }
        if(parent.getChildAdapterPosition(view)%2 == 0){
            outRect.right = 0;
        }
    }
}
