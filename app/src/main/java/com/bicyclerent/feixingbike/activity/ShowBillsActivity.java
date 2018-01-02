package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.adapter.UniversalAdapter;
import com.bicyclerent.feixingbike.adapter.UniversalViewHolder;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowBillsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvBills = null;
    private UniversalAdapter<String> mAdapter = null;
    private ArrayList<String> dates = null;
    private ArrayList<String> bikeIDs = null;
    private ArrayList<String> costs = null;
    private ArrayList<BillsBean> mBillsBeans = null;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bills);
        ActionBarUtil.getInstance(getSupportActionBar(), "全部订单");
        initView();
        setAdapter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /*初始化控件*/
    private void initView() {
        mBillsBeans = (ArrayList<BillsBean>) getIntent().getSerializableExtra("bills");
        rvBills = (RecyclerView) findViewById(R.id.bills_recycle);
    }

    /*下拉加载*/
    private void initLoadMoreListener() {
        rvBills.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecycleView处于空闲并且是最后一个可见
                if (newState == RecyclerView.SCROLL_STATE_IDLE && UniversalAdapter.IS_LOAD_MORE == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /*设置数据*/
    private void setData() {
        dates = new ArrayList<>();
        bikeIDs = new ArrayList<>();
        costs = new ArrayList<>();
        for (BillsBean billsBean : mBillsBeans) {
            dates.add(billsBean.getEtime());
            bikeIDs.add(billsBean.getBillid());
            costs.add(String.valueOf(billsBean.getCost().setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue()));
            i++;
        }
    }


    /*设置适配器*/
    private void setAdapter() {
        setData();
        mAdapter = new UniversalAdapter<String>(this, dates, R.layout.list_bills_item) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                holder.getTextView(R.id.bills_bike_id).setText(bikeIDs.get(position));
                holder.getTextView(R.id.bills_cost).setText(costs.get(position));
                holder.getTextView(R.id.bills_time).setText(dates.get(position));
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBills.setLayoutManager(linearLayoutManager);
        rvBills.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new UniversalAdapter.onClickListener() {
            @Override
            public void onClickListener(View view, int pos) {
                Intent intent = new Intent(ShowBillsActivity.this, SingleBillActivity.class);
                intent.putExtra("billsBean", mBillsBeans.get(pos));
                startActivity(intent);
            }
        });
    }

    /*上拉加载*/
    @Override
    public void onRefresh() {

    }

    public void setToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
