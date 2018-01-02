package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.baoyachi.stepview.HorizontalStepView;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;
import com.bicyclerent.feixingbike.view.StepView;

import java.util.HashMap;

public class ForegiftActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btChargeMoney;
    private RadioButton rbWeixin,rbZhifubao;
    private HorizontalStepView hsvForegift;
    private StepView mStepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foregift);
        ActionBarUtil.getInstance(getSupportActionBar(),"押金充值");
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView(){
        btChargeMoney = (Button)findViewById(R.id.charge_money_button);
        hsvForegift = (HorizontalStepView)findViewById(R.id.foregift_step_view);
        rbWeixin = (RadioButton)findViewById(R.id.weixin_radio);
        rbZhifubao = (RadioButton)findViewById(R.id.zhifubao_radio);
        btChargeMoney.setOnClickListener(this);
        rbWeixin.setOnClickListener(this);
        rbZhifubao.setOnClickListener(this);
        mStepView = new StepView(hsvForegift,this);
        mStepView.setCompletedStepNum(0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weixin_radio:
//                if(rbWeixin.isChecked())
//                rbWeixin.setClickable(true);
//                rbZhifubao.setClickable(false);
                break;

            case R.id.zhifubao_radio:
//                rbWeixin.setClickable(false);
//                rbZhifubao.setClickable(true);
                break;

            case R.id.charge_money_button:
                setBtChargeMoneyClick();
                updateUserInfo();
                startActivity(new Intent(ForegiftActivity.this,UserCenterActivity.class));
                break;
        }
    }

    public void setBtChargeMoneyClick(){
        Intent intent = new Intent();
        intent.setAction("stepView");
        intent.putExtra("completedStepNum",1);
        sendBroadcast(intent);
    }

    public void updateUserInfo(){
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(this,"userInfo");
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("userType","已交押金用户");
        sharedPreferencesUtil.setData(hashMap);
    }
}
