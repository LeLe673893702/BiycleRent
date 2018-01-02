package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.IRegisterActivity;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.presenter.RegisterPresenter;
import com.bicyclerent.feixingbike.presenterinterface.IRegisterPresenter;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.bicyclerent.feixingbike.utils.CompressBitmapUtil;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterActivity,View.OnClickListener{
    /*
    * 手机号
    * 密码
    * 验证码
    * */
    @BindViews({R.id.register_user_edit,R.id.register_password_edit,R.id.register_code_edit})
    public List<EditText> mEditTexts;

    @BindView(R.id.register_background_image)
    public ImageView ivRegister;

    @BindViews({R.id.register_get_code_button,R.id.register_sure_button})
    public List<Button> mButtons;

    private IRegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBarUtil.getInstance(getSupportActionBar(),"用户注册");
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /*初始化控件*/
    private void initView(){
        ButterKnife.bind(this);
        ivRegister.setImageBitmap(CompressBitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.login_background,
                ScreenSizeUtil.getWidth(this), ScreenSizeUtil.getHeight(this)));
        mPresenter = new RegisterPresenter(this,this);
        mButtons.get(0).setOnClickListener(this);
        mButtons.get(1).setOnClickListener(this);
    }


    /*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_get_code_button:
                        mPresenter.setGetButtonClick(mEditTexts.get(0).getText().toString());
                break;
            case R.id.register_sure_button:
                try {
                    mPresenter.setSureButtonClick(mEditTexts.get(2).getText().toString(),mEditTexts.get(1).getText().toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /*显示吐司信息*/
    @Override
    public void setToastInfo(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /*设置button信息*/
    @Override
    public void setCodeButtonText(String text) {
        mButtons.get(0).setText(text);
    }

    /*设置button是否可以点击*/
    @Override
    public void setCodeButtonClickable(boolean isClickable) {
        mButtons.get(0).setClickable(isClickable);
    }

    @Override
    public void setIntent(ArrayList<RentpointsBean> rentpointsBeans) {
        Intent intent = new Intent(this,MainActivity.class);
        Application.rentpointsBeans = rentpointsBeans;
        startActivity(intent);
    }
}
