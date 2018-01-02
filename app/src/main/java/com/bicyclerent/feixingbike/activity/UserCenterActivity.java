package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.IUserCenterActivity;
import com.bicyclerent.feixingbike.adapter.DividerItemDecoration;
import com.bicyclerent.feixingbike.adapter.UniversalAdapter;
import com.bicyclerent.feixingbike.adapter.UniversalViewHolder;
import com.bicyclerent.feixingbike.broadcast.UpdateStepBroadCast;
import com.bicyclerent.feixingbike.broadcast.UpdateUIListener;
import com.bicyclerent.feixingbike.javabean.BillsBean;
import com.bicyclerent.feixingbike.presenter.UserCenterPresenter;
import com.bicyclerent.feixingbike.presenterinterface.IUserCenterPresenter;
import com.bicyclerent.feixingbike.utils.SharedPreferencesUtil;
import com.bicyclerent.feixingbike.view.CircleImageView;
import com.bicyclerent.feixingbike.view.StepView;
import com.bicyclerent.feixingbike.view.UserCenterScroll;
import com.bicyclerent.feixingbike.utils.CompressBitmapUtil;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

import java.util.ArrayList;


public class UserCenterActivity extends AppCompatActivity implements IUserCenterActivity{
    private UserCenterScroll mScroll  = null;
    private int imageHeight;
    private TextView tvTitle,tvCompleted,tvUserName,tvUserType;
    private ImageView ivBackGround;
    private AppCompatImageView aivBack;
    private CircleImageView civHead;
    private HorizontalStepView hsvUser ;
    private RecyclerView rvUserInfo,rvMine,rvSetting;
    private ArrayList<String> infoNames ;
    private ArrayList<String>infoDetails;
    private StepView stepView;
    private IUserCenterPresenter mPresenter;
    private boolean first = true;
    private SharedPreferencesUtil mSharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        findById();
        initView();
        setIvBackGround();
        setSettingRVAdapter();
        setMineRVAdapter();
    }

    /*设置骑行统计适配器*/
    private void setCyclingRVAdapter(){
        /*设置显示信息*/
        if(first) {
            infoNames.add("累计骑行(分钟)");
            infoNames.add("节约碳排量(千克)");
            infoNames.add("运动成就(大卡)");
            first = false;
        }
        //隐藏下面的步骤条
        hsvUser.setVisibility(View.INVISIBLE);
        tvCompleted.setVisibility(View.INVISIBLE);
        //设置RecycleView
        UniversalAdapter<String> universalAdapter = new UniversalAdapter<String>(this,infoNames,R.layout.list_cycling_total) {
            @Override
            public void bindDate(UniversalViewHolder holder, String dates, int position) {

                TextView tvDetails = holder.getTextView(R.id.user_info_details_text);
                TextView tvName = holder.getTextView(R.id.user_info_name_text);
                tvDetails.setText(infoDetails.get(position));
                tvName.setText(infoNames.get(position));
            }
        };
        setRvOrientation(rvUserInfo, LinearLayoutManager.HORIZONTAL);
        rvUserInfo.setAdapter(universalAdapter);
    }

    /*设置适配器*/
    private void setMineRVAdapter(){
        final ArrayList<String> mines =new ArrayList<>();
        mines.add("我的钱包");
        mines.add("我的优惠");
        mines.add("我的行程");
        mines.add("我的订单");
        UniversalAdapter<String> mineRVAdapter = new UniversalAdapter<String>(this,mines,R.layout.list_user_info) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                TextView tvMineTitle = holder.getTextView(R.id.mine_title);
                tvMineTitle.setText(mines.get(position));
            }
        };
        setRvOrientation(rvMine, LinearLayoutManager.VERTICAL);
        rvMine.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rvMine.setAdapter(mineRVAdapter);
        mineRVAdapter.setOnClickListener(new UniversalAdapter.onClickListener() {
            @Override
            public void onClickListener(View view, int pos) throws ClassNotFoundException {
                switch (pos) {
                    //我的钱包
                    case 0:
                        break;
                    //我的优惠
                    case 1:
                        break;
                    //我的行程
                    case 2:
                        mPresenter.getUnAccount();
                        break;
                    //我的订单
                    case 3:
                        mPresenter.getBills(false);
                        break;
                }
            }
        });
    }

    /*设置适配器*/
    private void setSettingRVAdapter(){
        final ArrayList<String> settings = new ArrayList<>();
        settings.add("实名认证");
        settings.add("用户指南");
        settings.add("设置");
        UniversalAdapter<String> settingRVAdapter = new UniversalAdapter<String>(this,settings,R.layout.list_user_info) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                TextView tvMineTitle = holder.getTextView(R.id.mine_title);
                tvMineTitle.setText(settings.get(position));
            }
        };
        setRvOrientation(rvSetting, LinearLayoutManager.VERTICAL);
        rvSetting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rvSetting.setAdapter(settingRVAdapter);
        settingRVAdapter.setOnClickListener(new UniversalAdapter.onClickListener() {
            @Override
            public void onClickListener(View view, int pos) {
                switch (pos) {
                    //实名认证
                    case 0:
                        if (mSharedPreferencesUtil.getStringData("userType").equals("已注册用户")) {
                            startActivity(new Intent(UserCenterActivity.this, ForegiftActivity.class));
                        } else if (mSharedPreferencesUtil.getStringData("userType").equals("已认证用户")) {
                            Toast.makeText(UserCenterActivity.this, "您已经实名认证，无需再次验证", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(UserCenterActivity.this, IdentificationActivity.class));
                        }
                        break;
                    //用户指南
                    case 1:
                        break;
                    //设置
                    case 2:
                        startActivity(new Intent(UserCenterActivity.this, SettingActivity.class));
                        break;
                }
            }
        });
    }

    /*设置item是横向还是竖向*/
    private void setRvOrientation(RecyclerView recyclerView,int orientation){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(layoutManager);
    }

    /*初始化控件*/
    private void initView(){
        mSharedPreferencesUtil = new SharedPreferencesUtil(this,"userInfo");
        mScroll.setScrollViewListener(new UserCenterScroll.ScrollViewListener() {
            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {
                hideTitle(y, oldy);
            }
        });
        mPresenter = new UserCenterPresenter(this,this);
        tvUserName.setText(mSharedPreferencesUtil.getStringData("userID"));
        infoDetails = new ArrayList<>();
        infoNames = new ArrayList<>();
    }

    /*获取控件对象*/
    private void findById(){
        aivBack = (AppCompatImageView)findViewById(R.id.back_image);
        aivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCenterActivity.this,MainActivity.class));
            }
        });
        tvUserName = (TextView)findViewById(R.id.user_name_text);
        tvUserType = (TextView)findViewById(R.id.user_type_text);
        tvTitle = (TextView)findViewById(R.id.user_center_title);
        tvCompleted = (TextView)findViewById(R.id.user_comleted_text);
        mScroll = (UserCenterScroll)findViewById(R.id.user_center_scroll);
        ivBackGround = (ImageView)findViewById(R.id.user_center_background_image);
        hsvUser = (HorizontalStepView)findViewById(R.id.user_step_view);
        rvUserInfo = (RecyclerView)findViewById(R.id.user_info_recycle);
        rvMine = (RecyclerView)findViewById(R.id.user_mine_recycle);
        rvSetting = (RecyclerView)findViewById(R.id.user_setting_recycle);
        civHead = (CircleImageView)findViewById(R.id.user_center_circle_image);
        stepView = new StepView(hsvUser,this);
    }

    /*设置图片大小*/
    private void setIvBackGround(){
        imageHeight = new Double(ScreenSizeUtil.getHeight(this) * 0.45).intValue();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)ivBackGround.getLayoutParams();
        layoutParams.height = imageHeight;
        ivBackGround.setLayoutParams(layoutParams);
        ivBackGround.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.customer_service));
        ivBackGround.setImageBitmap(CompressBitmapUtil.decodeSampledBitmapFromResource(
                getResources(), R.drawable.user_center_background, ScreenSizeUtil.getWidth(this), imageHeight));
    }

    /*
    * 根据滑动隐藏actionbar
    * */
    private void hideTitle(float y,float oldy){
        float alpha1 = 1-y/(imageHeight/2);
        float alpha2 = y/(imageHeight);
        //上滑隐藏标题非行单车，显示标题个人中心
        if(y < imageHeight) {
            if (y <= imageHeight / 2) {
                tvTitle.setText("非行单车");
                tvTitle.setAlpha(alpha1);
            } else {
                tvTitle.setText("个人中心");
                tvTitle.setAlpha(alpha2);
            }
        }
    }

    /*
    * 切换回来界面调用
    * */
    @Override
    protected void onResume() {
        super.onResume();
        tvUserType.setText(mSharedPreferencesUtil.getStringData("userType"));
        //如果完成认证
        if(mSharedPreferencesUtil.getStringData("userType").equals("已认证用户")){
            //隐藏下面的步骤条
            hsvUser.setVisibility(View.INVISIBLE);
            tvCompleted.setVisibility(View.INVISIBLE);
            rvUserInfo.setVisibility(View.VISIBLE);
            mPresenter.getBills(true);
        }else {
            //显示下面的步骤条
            hsvUser.setVisibility(View.VISIBLE);
            tvCompleted.setVisibility(View.VISIBLE);
            rvUserInfo.setVisibility(View.INVISIBLE);
        }
        if(mSharedPreferencesUtil.getStringData("userType").equals("已交押金用户")){
            stepView.setCompletedStepNum(1);
        }else if(mSharedPreferencesUtil.getStringData("userType").equals("已注册用户")){
            stepView.setCompletedStepNum(0);
        }
    }

    //设置骑行详情
    @Override
    public void setCyclingInfo(ArrayList<String> cyclingDetails) {
        infoDetails = cyclingDetails;
        Log.e("infoDetails",infoDetails.get(0));
        setCyclingRVAdapter();
    }

    /*跳转还车界面*/
    @Override
    public void setReturnBikeIntent(ArrayList<BillsBean> billsBean) {
        Intent returnBikeIntent = new Intent(this,ReturnBikeActivity.class);
        if(billsBean.size() != 0) {
            returnBikeIntent.putExtra("billsBean", billsBean.get(0));
        }
        startActivity(returnBikeIntent);
    }

    /*跳转展示订单界面*/
    @Override
    public void setShowBillsIntent(ArrayList<BillsBean> billsBeanArrayList) {
        Intent showBillsIntent = new Intent(this,ShowBillsActivity.class);
        showBillsIntent.putExtra("bills",billsBeanArrayList);
        startActivity(showBillsIntent);
    }

    @Override
    public void setToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
