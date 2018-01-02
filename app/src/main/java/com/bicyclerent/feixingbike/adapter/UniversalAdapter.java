package com.bicyclerent.feixingbike.adapter;

import android.content.Context;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 刺雒 on 2017/1/19.
 */
public abstract class UniversalAdapter<T> extends RecyclerView.Adapter<UniversalViewHolder> {
    public static int IS_LOAD_MORE = 0;
    private List<T> mDates;
    private View itemView;
    private LayoutInflater mInflater;
    private onClickListener mListener = null ;
    private onLongClickListener mLongListener = null;
    private int layoutId;
    public UniversalAdapter(Context context, List<T> dates,int layoutId){
        this.mDates = dates;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;

    }

    @Override
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(layoutId,parent,false);
        final UniversalViewHolder viewHolder = new UniversalViewHolder(itemView);
        //设置item点击事件
        if(mListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mListener.onClickListener(viewHolder.itemView,viewHolder.getLayoutPosition());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //设置item长按事件
        if(mLongListener != null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongListener.onLongClickListener(viewHolder.itemView,viewHolder.getLayoutPosition());
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
        return viewHolder;
    }

    //设置绑定事件
    @Override
    public void onBindViewHolder(UniversalViewHolder holder, int position) {
        bindDate(holder,mDates.get(position),position);
    }


    @Override
    public int getItemViewType(int position) {
        //如果是最后一条
        if(position+1 == getItemCount()){
            IS_LOAD_MORE = 1;
            return IS_LOAD_MORE;
        }else {
            IS_LOAD_MORE = 0;
            return IS_LOAD_MORE;
        }
    }

    @Override
    public int getItemCount() {
        //最后一条是footView
        return mDates.size();
    }

    public void setOnClickListener(onClickListener onClickListener){
        mListener = onClickListener;
    }
    public void setOnLongClickListener(onLongClickListener onLongClickListener){
        mLongListener = onLongClickListener;
    }


    abstract public  void bindDate(UniversalViewHolder holder,T item,int position);

    public interface onClickListener{
        public void onClickListener(View view,int pos) throws ClassNotFoundException;
    }

    public interface onLongClickListener{
        public void onLongClickListener(View view,int pos);
    }
}
