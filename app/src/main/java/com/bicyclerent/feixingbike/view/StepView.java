package com.bicyclerent.feixingbike.view;

import android.content.Context;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

import java.util.ArrayList;

/**
 * Created by 刺雒 on 2017/1/19.
 */
public class StepView {
    private  ArrayList<StepBean> stepBeans ;
    private HorizontalStepView stepView;
    private ArrayList<String> steps ;
    private Context mContext;
    /*设置步骤指示器*/
    public  StepView(HorizontalStepView horizontalStepView,Context context){
        stepBeans = new ArrayList<>();
        stepView = horizontalStepView;
        this.mContext = context;
        setTextView();
        initStepViewText();
        setStepViewStyle();
    }

    private void setTextView(){
        steps = new ArrayList<>();
        steps.add("绑定手机");
        steps.add("充值押金");
        steps.add("校园认证");
        steps.add("立即用车");
    }

    /*初始化步骤条显示文字*/
    private void initStepViewText(){
        for(int i = 0; i < 4; i++) {
            StepBean stepBean = new StepBean();
            stepBean.setState(StepBean.STEP_UNDO);
            stepBean.setName(steps.get(i));
            stepBeans.add(stepBean);
        }
        stepView.setStepViewTexts(stepBeans);
    }

    /*设置步骤*/
    public void setCompletedStepNum(int num){
        for(int i = 0; i <= num; i++){
            stepBeans.get(i).setState(StepBean.STEP_COMPLETED);
        }
        if(num < 3) {
            stepBeans.get(num + 1).setState(StepBean.STEP_CURRENT);
        }
        stepView.setStepViewTexts(stepBeans);
    }

    /*设置步骤条风格*/
    private void setStepViewStyle(){
        stepView.setTextSize(12);
        stepView.setStepViewUnComplectedTextColor(R.color.black);
        stepView.setStepViewComplectedTextColor(R.color.black).setTextViewHeight(ScreenSizeUtil.px2dip(mContext,500));
    }
}
