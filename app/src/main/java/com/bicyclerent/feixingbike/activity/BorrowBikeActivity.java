package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.IBorrowBikeActivity;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.presenter.BorrowPresenter;
import com.bicyclerent.feixingbike.presenterinterface.IBorrowPresenter;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.xys.libzxing.zxing.activity.CaptureActivity;

public class BorrowBikeActivity extends AppCompatActivity implements View.OnClickListener,IBorrowBikeActivity{

    private Button btStartBike,btScanBike;
    private EditText etBikeNum;
    private String bikeID;
    private IBorrowPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_bike);
        ActionBarUtil.getInstance(getSupportActionBar(),"非行单车");
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView(){
        btScanBike = (Button)findViewById(R.id.borrow_scan_bike_button);
        btStartBike = (Button)findViewById(R.id.borrow_start_bike_button);
        etBikeNum = (EditText)findViewById(R.id.borrow_bike_num_edit);
        btStartBike.setOnClickListener(this);
        btScanBike.setOnClickListener(this);
        mPresenter = new BorrowPresenter(this,this);
        btScanBike.setOnClickListener(this);
        btStartBike.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //扫描车牌
            case R.id.borrow_scan_bike_button:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
            //开始骑行
            case R.id.borrow_start_bike_button:
                try {
                    mPresenter.setBtStartClick(etBikeNum.getText().toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        if(resultCode == RESULT_OK){
            bikeID = bundle.getString("result");
            etBikeNum.setText(bikeID);
        }
    }

    @Override
    public void setToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIntent() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
