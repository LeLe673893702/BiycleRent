package com.bicyclerent.feixingbike.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 刺雒 on 2017/2/3.
 */
public class UpdateStepBroadCast extends BroadcastReceiver {
    private UpdateUIListener mUpdateUIListener;
    @Override
    public void onReceive(Context context, Intent intent) {
        int stepNum = intent.getIntExtra("completedStepNum",0);
        mUpdateUIListener.updateView(stepNum);

    }

    public void setUpdateUIListener(UpdateUIListener updateUIListener){
        this.mUpdateUIListener = updateUIListener;
    }


}
