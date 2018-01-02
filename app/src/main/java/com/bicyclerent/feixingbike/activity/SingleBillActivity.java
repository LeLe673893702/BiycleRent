package com.bicyclerent.feixingbike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.adapter.DividerItemDecoration;
import com.bicyclerent.feixingbike.adapter.UniversalAdapter;
import com.bicyclerent.feixingbike.adapter.UniversalViewHolder;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.bicyclerent.feixingbike.view.CyclingTextView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SingleBillActivity extends AppCompatActivity {
    private TextView tvTan,tvKai;
    private CyclingTextView ctvTime;
    private RecyclerView rvBillInfo;
    private BillsBean mBillsBean;
    private ArrayList<String> billInfoTitle,billInfoDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bill);
        mBillsBean = (BillsBean)getIntent().getSerializableExtra("billsBean");
        ActionBarUtil.getInstance(getSupportActionBar(),"订单详情");
        initView();
        try {
            setText();
        } catch (ParseException e) {
            Log.e("ParseException",e.getMessage());
        }
        setAdapter();
    }

    /*初始化控件*/
    private void initView(){
        tvKai = (TextView)findViewById(R.id.single_bill_kai);
        tvTan = (TextView)findViewById(R.id.single_bill_tan);
        ctvTime = (CyclingTextView)findViewById(R.id.single_bill_cycling_time);
        rvBillInfo = (RecyclerView)findViewById(R.id.single_bill_info_recycle);
    }

    /*设置碳排量的值*/
    private void setText() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date BTime = simpleDateFormat.parse(mBillsBean.getBtime());
        Date ETime = simpleDateFormat.parse(mBillsBean.getEtime());
        long min = (ETime.getTime() - BTime.getTime())/(1000*60);
        ctvTime.setText(String.valueOf(min)+"'");
        tvKai.setText(String.valueOf(min*5)+"K");
        tvTan.setText(String.valueOf(10*min)+"g");
    }

    /*设置字形成的参数*/
    private void setData(){
        billInfoTitle = new ArrayList<>();
        billInfoTitle.add("订单编号:");
        billInfoTitle.add("自行车编号:");
        billInfoTitle.add("开始时间:");
        billInfoTitle.add("结束时间");
        billInfoTitle.add("骑行消费:");

        billInfoDetails = new ArrayList<>();
        billInfoDetails.add(mBillsBean.getBillid());
        billInfoDetails.add(mBillsBean.getBicycleid());
        billInfoDetails.add(mBillsBean.getBtime());
        billInfoDetails.add(mBillsBean.getEtime());
        billInfoDetails.add(String.valueOf(mBillsBean.getCost().setScale(0, BigDecimal.ROUND_CEILING))+"元");
    }

    /*设置显示适配器*/
    private void setAdapter(){
        setData();
        UniversalAdapter<String> universalAdapter = new UniversalAdapter<String>(this,billInfoDetails,R.layout.list_cycling_info) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                holder.getTextView(R.id.cycling_info_title).setText(billInfoTitle.get(position));
                holder.getTextView(R.id.cycling_info_details).setText(billInfoDetails.get(position));
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBillInfo.setLayoutManager(linearLayoutManager);
        rvBillInfo.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        rvBillInfo.setAdapter(universalAdapter);
    }
}
