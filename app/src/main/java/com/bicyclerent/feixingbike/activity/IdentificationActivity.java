package com.bicyclerent.feixingbike.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.activityinterface.IIdentificationActivity;
import com.bicyclerent.feixingbike.presenter.IdentificationPresenter;
import com.bicyclerent.feixingbike.presenterinterface.IIdentificationPresenter;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;
import com.bicyclerent.feixingbike.utils.CompressBitmapUtil;
import com.bicyclerent.feixingbike.utils.ScreenSizeUtil;
import com.bicyclerent.feixingbike.view.IdentificationImageButton;
import com.bicyclerent.feixingbike.view.StepView;

import java.io.FileNotFoundException;

public class IdentificationActivity extends AppCompatActivity implements IIdentificationActivity, View.OnClickListener {
    private IdentificationImageButton ibIdentification;
    private Button btSummit;
    private IIdentificationPresenter mPresenter;
    private Bitmap bitmap;
    private HorizontalStepView hsvIdentification;
    private StepView mStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        ActionBarUtil.getInstance(getSupportActionBar(), "实名认证");
        initView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView() {
        ibIdentification = (IdentificationImageButton) findViewById(R.id.identification_image_button);
        btSummit = (Button) findViewById(R.id.identification_summit_button);
        hsvIdentification = (HorizontalStepView) findViewById(R.id.identification_step_view);
        ibIdentification.setText("上传学生证或身份证正面照片");
        mPresenter = new IdentificationPresenter(this, this);

        mStepView = new StepView(hsvIdentification, this);
        mStepView.setCompletedStepNum(1);
        btSummit.setOnClickListener(this);
        ibIdentification.setOnClickListener(this);
    }

    @Override
    public void setToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIntent() {
        startActivity(new Intent(IdentificationActivity.this,UserCenterActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.identification_image_button:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.identification_summit_button:

                if (bitmap != null) {
                    mPresenter.setSummitClick(bitmap);
                } else {
                    setToast("上传图片不能为空");
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                ContentResolver contentResolver = this.getContentResolver();
                bitmap = CompressBitmapUtil.decodeStreamBitmap(contentResolver.openInputStream(uri),
                        20, ScreenSizeUtil.dip2px(this, 80));
                ibIdentification.setImageBitmap(bitmap);
                ibIdentification.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
