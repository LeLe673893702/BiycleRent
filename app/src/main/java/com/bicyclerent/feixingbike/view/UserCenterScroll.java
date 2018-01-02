package com.bicyclerent.feixingbike.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 刺雒 on 2017/1/17.
 */
public class UserCenterScroll extends ScrollView{

    private ScrollViewListener mScrollViewListener = null;
    public UserCenterScroll(Context context) {

        super(context);
    }

    public UserCenterScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserCenterScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    /*第一个参数为变化后的X轴位置
      *第二个参数为变化后的Y轴的位置
      *第三个参数为原先的X轴的位置
      *第四个参数为原先的Y轴的位置
      * */
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mScrollViewListener != null){
            mScrollViewListener.onScrollChanged(this,l, t, oldl, oldt);
        }
    }
    public interface ScrollViewListener{
        void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy);
    }

    public void setScrollViewListener(ScrollViewListener listener){
        this.mScrollViewListener = listener;
    }
}
