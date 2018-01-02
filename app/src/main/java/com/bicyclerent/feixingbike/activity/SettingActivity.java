package com.bicyclerent.feixingbike.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bicyclerent.feixingbike.R;
import com.bicyclerent.feixingbike.adapter.DividerItemDecoration;
import com.bicyclerent.feixingbike.adapter.UniversalAdapter;
import com.bicyclerent.feixingbike.adapter.UniversalViewHolder;
import com.bicyclerent.feixingbike.utils.ActionBarUtil;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rvSetting;
    private Button btLogOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBarUtil.getInstance(getSupportActionBar(),"设置");
        initView();
        setAdapter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initView(){
        rvSetting = (RecyclerView)findViewById(R.id.setting_recycle);
        btLogOff = (Button)findViewById(R.id.log_off_button);
        btLogOff.setOnClickListener(this);
    }

    private void setAdapter(){
        final ArrayList<String> data = new ArrayList<>();
        data.add("清除缓存");
        data.add("检查新版本");
        data.add("关于我们");
        UniversalAdapter<String> universalAdapter = new UniversalAdapter<String>(this,data,R.layout.list_user_info) {
            @Override
            public void bindDate(UniversalViewHolder holder, String item, int position) {
                holder.getTextView(R.id.mine_title).setText(data.get(position));
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSetting.setLayoutManager(linearLayoutManager);
        rvSetting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rvSetting.setAdapter(universalAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.log_off_button){
            Intent intent = new Intent(this,LoginActivity.class);
            setDialog(intent);
        }
    }

    public void setDialog(final Intent intent){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("退出当前账号不会删除任何历史数据,下次登录依然可以使用本账号");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
