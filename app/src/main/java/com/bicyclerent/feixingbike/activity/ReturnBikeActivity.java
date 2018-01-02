package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.IReturnBikeActivity;
import com.bicyclerent.feixingbike.adapter.DividerItemDecoration;
import com.bicyclerent.feixingbike.adapter.UniversalAdapter;
import com.bicyclerent.feixingbike.adapter.UniversalViewHolder;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.presenter.ReturnBikePresenter;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReturnBikeActivity extends AppCompatActivity implements View.OnClickListener,IReturnBikeActivity {
    private RecyclerView rvCyclingInfo = null;
    private ArrayList<String> cyclingDetails = null;
    private ArrayList<String> cyclingTitles = null;
    private LinearLayout llCyclingInfo = null;
    private LinearLayout llEmptyCycling = null;
    private Button btFinishCycling = null;
    private ReturnBikePresenter mPresenter = null;
    private String billID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_bike);
        ActionBarUtil.getInstance(getSupportActionBar(), "非行单车");
        initView();
        setBillBean((BillsBean) getIntent().getSerializableExtra("billsBean"));
        setAdapter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /*初始化控件*/
    private void initView(){
        mPresenter = new ReturnBikePresenter(this,this);
        rvCyclingInfo = (RecyclerView)findViewById(R.id.cycling_info);
        cyclingDetails = new ArrayList<>();
        cyclingTitles = new ArrayList<>();
        llCyclingInfo = (LinearLayout)findViewById(R.id.cycling_info_layout);
        llEmptyCycling = (LinearLayout)findViewById(R.id.empty_cycling_layout);
        btFinishCycling = (Button)findViewById(R.id.finish_cycling_button);
        btFinishCycling.setOnClickListener(this);
    }

    /*设置item是横向还是竖向*/
    private void setRvOrientation(RecyclerView recyclerView,int orientation){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(layoutManager);
    }

    /*设置适配器*/
    private void setAdapter(){
        UniversalAdapter<String> universalAdapter = new UniversalAdapter<String>(this,cyclingDetails,R.layout.list_cycling_info) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                holder.getTextView(R.id.cycling_info_title).setText(cyclingTitles.get(position));
                holder.getTextView(R.id.cycling_info_details).setText(cyclingDetails.get(position));
            }
        };
        setRvOrientation(rvCyclingInfo, LinearLayoutManager.VERTICAL);
        rvCyclingInfo.setAdapter(universalAdapter);
        rvCyclingInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    /*设置数据*/
     private void setCyclingInfo(BillsBean billsBean)  {
         billID = billsBean.getBillid();
         cyclingTitles.add("订单编号:");
         cyclingTitles.add("自行车编号:");
         cyclingTitles.add("开始时间:");

         cyclingDetails.add(billsBean.getBillid());
         cyclingDetails.add(billsBean.getBicycleid());
         cyclingDetails.add(billsBean.getBtime());
     }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish_cycling_button:
                try {
                    mPresenter.setBtFinishCyclingClick(billID);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void setToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /*跳转到支付界面*/
    @Override
    public void setIntent(String cost,String billID) {
        Intent intent = new Intent(this,PayMoneyActivity.class);
        intent.putExtra("cost",cost);
        intent.putExtra("billID",billID);
        startActivity(intent);
    }

    /*获取请求成功后返回的 正在使用的情况*/
    public void setBillBean(BillsBean billBean) {
        if(billBean != null){
            setCyclingInfo(billBean);
            llCyclingInfo.setVisibility(View.VISIBLE);
            llEmptyCycling.setVisibility(View.INVISIBLE);
        }else {
            llCyclingInfo.setVisibility(View.INVISIBLE);
            llEmptyCycling.setVisibility(View.VISIBLE);
        }
    }
}
