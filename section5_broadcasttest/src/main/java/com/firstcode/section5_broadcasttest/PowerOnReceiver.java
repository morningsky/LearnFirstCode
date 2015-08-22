package com.firstcode.section5_broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by sky on 2015/8/22.
 */
public class PowerOnReceiver extends BroadcastReceiver {
    //在androidManifest文件中静态注册广播
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机了",Toast.LENGTH_SHORT).show();
    }
}
