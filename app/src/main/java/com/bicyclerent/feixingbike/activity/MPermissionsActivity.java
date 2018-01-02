package com.bicyclerent.feixingbike.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bicyclerent.feixingbike.R;

import java.util.ArrayList;
import java.util.List;

public class MPermissionsActivity extends AppCompatActivity {
    private int REQUEST_CODE_PERMISSION = 0x00099;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *检测所有的权限是否都已经授权
     *@param permissions 申请的所有权限列表
     */
    private boolean checkPermissions(String[] permissions){
        //如果当前sdk小于android6.0
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }

        for(String permission:permissions){
            //判断这个权限是否已经给了设备
            if(ContextCompat.checkSelfPermission(this,permission) !=
                    PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     *获取权限集中需要申请的权限
     *@param permissions 需要申请的权限列表
     */
    private List<String> getDeniedPermissions(String[] permissions){
        List<String> needRequestPermissionsList = new ArrayList<>();
        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                //判断是否需要 向用户解释，为什么要申请该权限
                needRequestPermissionsList.add(permission);
            }
        }
        return needRequestPermissionsList;
    }

    /**
     *请求权限
     *@param requestCode 请求返回码
     */
    public void requestPermission(String[] permissions,int requestCode){
        this.REQUEST_CODE_PERMISSION = requestCode;
        //如果权限申请成功
        if(checkPermissions(permissions)){
            permissionSuccess(requestCode);
        }else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            //申请权限
            ActivityCompat.requestPermissions(this,needPermissions.toArray(new String[needPermissions.size()]),RESULT_CANCELED);
        }
    }

    /**
     *处理权限请求回调
     *@param
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_PERMISSION){
            //如果所有权限都开启
            if(verifyPermissions(grantResults)){
                permissionSuccess(REQUEST_CODE_PERMISSION);
                startActivity(new Intent(this,MainActivity.class));
                //如果有权限没有被授权
            }else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        Log.d("fsafs", "获取权限成功=" + requestCode);

    }

    /**
     * 权限获取失败
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        Log.d("fsafs", "获取权限失败=" + requestCode);
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}
