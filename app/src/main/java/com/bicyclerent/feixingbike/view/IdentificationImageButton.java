package com.bicyclerent.feixingbike.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

/**
 * Created by 刺雒 on 2017/2/9.
 */
public class IdentificationImageButton extends ImageButton {
    private Paint mPaint;
    private String text= "";
    private Context mContext;
    public IdentificationImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(45);

    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom  - fontMetrics.top;
        //计算文字高度
        float textBaseY = getHeight() - (getHeight() - fontHeight)/2 - fontMetrics.bottom;
        canvas.drawText(text,(getWidth()- ScreenSizeUtil.px2dip(mContext,30))/2,textBaseY,mPaint);
    }
}
