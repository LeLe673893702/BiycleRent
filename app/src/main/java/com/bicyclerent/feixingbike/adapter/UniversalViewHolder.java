package com.bicyclerent.feixingbike.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 刺雒 on 2017/1/19.
 */
public class UniversalViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewMap = new SparseArray<>();
    private View mView;

    public UniversalViewHolder(View itemView) {
        super(itemView);
        viewMap = new SparseArray<>();
    }

    private <T extends View>  T findView(int viewId){
        View view;
        view = viewMap.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            viewMap.put(viewId,view);
        }
        return (T)view;
    }

    public TextView getTextView(int viewId){
        return (TextView)findView(viewId);
    }

    public ImageView getImageView(int viewId){
        return (ImageView)findView(viewId);
    }

    public LinearLayout getLinearLayout(int viewId){
        return (LinearLayout)findView(viewId);
    }
}
