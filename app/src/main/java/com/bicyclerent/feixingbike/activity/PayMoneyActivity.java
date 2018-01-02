package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.Nohttp.BaseNohttp;
import com.bicyclerent.feixingbike.Nohttp.IBasePresenter;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;

import java.math.BigDecimal;

public class PayMoneyActivity extends AppCompatActivity implements View.OnClickListener,IBasePresenter{
    private Button btChargeMoney;
    private RadioButton rbWeixin,rbZhifubao;
    private TextView tvPayMoneyDetails,tvPayMoneyTitle;
    private String payMoneyUrl = Application.HTTP_URL  + "/BillAPIHandler.ashx?type=PayMent";
    private String cost = null,billID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_money);
        cost = getIntent().getStringExtra("cost");
        billID = getIntent().getStringExtra("billID");
        ActionBarUtil.getInstance(getSupportActionBar(),"支付租金");
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView(){
        btChargeMoney = (Button)findViewById(R.id.charge_money_button);
        rbWeixin = (RadioButton)findViewById(R.id.weixin_radio);
        rbZhifubao = (RadioButton)findViewById(R.id.zhifubao_radio);
        tvPayMoneyDetails = (TextView)findViewById(R.id.pay_money_details_text);
        tvPayMoneyTitle = (TextView)findViewById(R.id.pay_money_title_text);
        btChargeMoney.setOnClickListener(this);
        rbWeixin.setOnClickListener(this);
        rbZhifubao.setOnClickListener(this);
        tvPayMoneyDetails.setText("￥" + cost);
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
                try {
                    setPayMoneyRequest();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    /*发送付款的请求*/
    private void setPayMoneyRequest() throws ClassNotFoundException {
        BaseNohttp baseNohttp = new BaseNohttp(this);
        baseNohttp.setRequestUrl(payMoneyUrl);
        baseNohttp.add("BillID", billID);
        baseNohttp.createNohttp(1);
    }

    @Override
    public void onSucceed(int what, String text)  {
        Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, UserCenterActivity.class));
    }

    @Override
    public void onFailed(int what, String toast) {
        Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show();
    }
}
