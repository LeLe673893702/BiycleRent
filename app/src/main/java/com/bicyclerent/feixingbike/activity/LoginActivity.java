package com.bicyclerent.feixingbike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bicyclerent.feixingbike.Application;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.ILoginActivity;
import com.bicyclerent.feixingbike.javabean.RentpointsBean;
import com.bicyclerent.feixingbike.modelinterface.ILoginModel;
import com.bicyclerent.feixingbike.presenter.LoginPresenter;
import com.bicyclerent.feixingbike.presenterinterface.ILoginPresenter;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.bicyclerent.feixingbike.utils.CompressBitmapUtil;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginActivity{

    private Button btRegister,btLogin;
    private EditText etPhoneNum,etPassword;
    private ImageView ivLogin;
    private ILoginPresenter mPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBarUtil.getInstance(getSupportActionBar(),"用户登录");
        init_view();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /*初始化控件*/
    private void init_view(){
        etPhoneNum = (EditText)findViewById(R.id.login_user_edit);
        etPassword = (EditText)findViewById(R.id.login_password_edit);
        btRegister = (Button)findViewById(R.id.register_button);
        btLogin = (Button)findViewById(R.id.login_button);
        ivLogin = (ImageView)findViewById(R.id.login_background_image);
        ivLogin.setImageBitmap(CompressBitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.login_background,
                ScreenSizeUtil.getWidth(this), ScreenSizeUtil.getHeight(this)));
        btRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        mPresenter = new LoginPresenter(this,this);
    }

    /*按钮点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //注册按钮
            case R.id.register_button:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            //登录按钮
            case R.id.login_button:
                try {
                    mPresenter.setLoginButtonClick(etPhoneNum.getText().toString(),etPassword.getText().toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /*显示toast信息*/
    @Override
    public void setToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /*跳转界面*/
    @Override
    public void setIntent(ArrayList<RentpointsBean> rentpointsBeans) {
        Intent intent = new Intent(this,MainActivity.class);
        Application.rentpointsBeans = rentpointsBeans;
        startActivity(intent);
    }
}
