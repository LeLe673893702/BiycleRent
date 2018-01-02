package com.bicyclerent.feixingbike.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

/**
 * Created by 刺雒 on 2017/2/5.
 */
public class CyclingTextView extends TextView {
    private Paint mInnerPaint,mOutPaint;
    private Context mContext;
    private int innerRadius,outRadius;
    public CyclingTextView(Context context) {
        super(context);
    }

    public CyclingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        int outStokeWidth = 8;
        int innerStokeWidth = 5;
        outRadius = ScreenSizeUtil.dip2px(mContext,80);
        innerRadius = ScreenSizeUtil.dip2px(mContext,80) - ScreenSizeUtil.dip2px(context,innerStokeWidth);
        mInnerPaint = new Paint();
        mOutPaint = new Paint();

        mInnerPaint.setStrokeWidth(ScreenSizeUtil.dip2px(context,innerStokeWidth));
        mInnerPaint.setARGB(233,237, 177, 167);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setAntiAlias(true);

        mOutPaint.setAntiAlias(true);
        mOutPaint.setARGB(233,236, 73, 84);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeWidth(ScreenSizeUtil.dip2px(context,outStokeWidth));
    }

    public CyclingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, outRadius,mOutPaint );
        canvas.drawCircle(getWidth()/2,getHeight()/2,innerRadius,mInnerPaint);
        super.onDraw(canvas);
    }
}
