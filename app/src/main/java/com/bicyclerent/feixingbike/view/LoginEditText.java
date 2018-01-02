package com.bicyclerent.feixingbike.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.utils.CompressBitmapUtil;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

/**
 * Created by 刺雒 on 2017/1/25.
 */
public class LoginEditText extends EditText {
    private float width = 0,height = 0,padding;
    private int iconID;
    private Context mContext = null;

    public LoginEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoginEditText);
        iconID = a.getResourceId(R.styleable.LoginEditText_icon, 10);
        width = a.getDimensionPixelSize(R.styleable.LoginEditText_width, 20);
        height = a.getDimensionPixelSize(R.styleable.LoginEditText_height, 20);
        padding =  a.getDimension(R.styleable.LoginEditText_padding,10);
        setIcon();
        a.recycle();

    }

    private void setIcon(){
        Drawable icon = mContext.getResources().getDrawable(iconID);
        Log.e("fsaf",width+"fasf"+height+"sfa"+iconID);
        icon.setBounds(0,0, ScreenSizeUtil.dip2px(mContext,width),ScreenSizeUtil.dip2px(mContext,height));
        CompressBitmapUtil.decodeSampledBitmapFromResource(mContext.getResources(), iconID, ScreenSizeUtil.dip2px(mContext, width), ScreenSizeUtil.dip2px(mContext, height));
        setCompoundDrawables(icon, null, null, null);
        setCompoundDrawablePadding(ScreenSizeUtil.dip2px(mContext,padding));
    }
}
